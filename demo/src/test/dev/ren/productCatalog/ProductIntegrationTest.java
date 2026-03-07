package dev.ren.productCatalog;


import dev.ren.productCatalog.models.Category;
import dev.ren.productCatalog.models.Product;
import dev.ren.productCatalog.repositories.CategoryRepository;
import dev.ren.productCatalog.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

// MOVED: test.testcontainers.service -> testcontainers.service.connection
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

// MOVED: test.autoconfigure.orm.jpa -> data.jpa.test.autoconfigure
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

// MOVED: test.autoconfigure.jdbc -> jdbc.test.autoconfigure
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

// TESTCONTAINERS 2.0.0 SPECIFIC (Optional, check your IDE)
// Testcontainers 2.0 flattened many container packages
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers // manages the docker lifecycle
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductIntegrationTest {
    @Container  //starts the REAL MySQL 8.0.44
    @ServiceConnection
    static MySQLContainer mysql = new MySQLContainer("mysql:8.0.44");
    @Autowired
    private TestEntityManager testEntityManager;
    // PRO-GRADE: Standard JPA way to ensure thread-safety, @Autowired is spring annotation
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void test_FKConstraint() {
        // 1. Setup Data
        Category cat = new Category("Tech");
        Product product = new Product("Laptop");
        product.setCategory(cat);
        assertThrows(IllegalStateException.class, () -> {
            entityManager.persist(product);
            entityManager.flush();
        });
    }

    @Test
    @DisplayName("Trigger MySQL FK Violation: Insert Product with non-existent Category ID")
    void shouldThrowDataIntegrityViolationWhenFKIsMissingInMySQL() {
        // 1. Arrange
        // We manually create a 'detached/ghost' category with an ID.
        // Hibernate sees an ID and assumes it exists in the DB, so it proceeds to generate SQL.
        UUID nonExistentUuid = UUID.randomUUID();
        //Hibernate uses Reflection and proxying via getReference to object of Category without using its constructor or setUuid()
        Category ghostCategory = entityManager.getReference(Category.class, nonExistentUuid);
        Product product = new Product("Industrial Drill");
        product.setCategory(ghostCategory);

        // 2. Act & Assert
        // DataIntegrityViolationException is Spring's translation of
        // MySQL Error 1452: 'Cannot add or update a child row: a foreign key constraint fails'
        assertThrows(DataIntegrityViolationException.class, () -> {
            productRepository.saveAndFlush(product);
        }, "MySQL rejected this insert because ghostCategory does not exist in Db");
    }

    @Test
    @DisplayName("Trigger MySQL FK Violation: Delete Parent while Children exist")
    void shouldThrowDataIntegrityViolationWhenDeletingParentWithChildren() {
        // 1. Arrange: Setup a valid relationship in the DB
        Category cat = new Category("Tools");
        entityManager.persist(cat);

        Product p = new Product("Hammer");
        p.setCategory(cat);
        entityManager.persist(p);

        entityManager.flush();
        entityManager.clear(); // Ensure we are testing the DB state, not the cache

        // 2. Act & Assert
        // MySQL Error 1451: 'Cannot delete or update a parent row: a foreign key constraint fails'
        assertThrows(ConstraintViolationException.class, () -> {
            Category managedCat = entityManager.find(Category.class, cat.getUuid());
            entityManager.remove(managedCat);
            entityManager.flush(); // Force the DELETE SQL to hit MySQL
        }, "MySQL should block deletion of a Category that still has Products");
    }

    @Test
    //dirty write to db without save or persist
    @Transactional // 1. Transaction Starts
    public void updateProductCategory() {

        Product product = new Product("Television");
        productRepository.saveAndFlush(product);
        // 1. Product is loaded into the Persistence Context (Managed State)
        Product prdFromDb = productRepository.findById(product.getUuid()).get();
        Category category = new Category("Gadgets");
        categoryRepository.saveAndFlush(category);
        // 2. Category is loaded into the Persistence Context (Managed State)
        Category ctgryFromDb = categoryRepository.findByName(category.getName()).get();
        // 4. You only call the setter -- This is the dirty write, No .save() or .persist() is called here!
        prdFromDb.setCategory(ctgryFromDb);
        // 5.  Verification Technique
        entityManager.flush(); // Send to MySQL
        //In your current code, the UPDATE query is generated after the method finishes because of @Transactional. To see the query inside the test, you must force Hibernate to "flush" its buffer to MySQL.
        entityManager.clear(); // Wipe Java memory (L1 Cache)
        // 6. Assert: Pull a fresh copy from MySQL
        Product freshProduct = productRepository.findByName(product.getName()).get();
        // 7. Assert that the product has a category
        assertThat(freshProduct.getCategory()).isNotNull();
        assertThat(freshProduct.getCategory().getName()).isEqualTo("Gadgets");
    }

    @Test
    @Transactional
    public void updateProductWithMerge() {
        // detachedProduct has an ID but is not tracked by the current session.
        Product detachedProduct = new Product("iPhone");
        // merge() looks for the entity in the DB by ID,
        // copies detachedProduct's values into a new managed object,
        // and returns that managed object.
        Product managedProduct = entityManager.merge(detachedProduct);

        // Changes to managedProduct will be synced to MySQL 8.0.44 at the end.
        managedProduct.setPrice(19.99);
        entityManager.flush();
        entityManager.clear();
        assertEquals(managedProduct.toString(), entityManager.find(Product.class, managedProduct.getUuid()).toString());
    }

    @Test
    @Transactional
//how did it work without transactional ???
    void reproduce_cascade_on_persist_with_transient_products_added_to_category() {
        //make sure the CascadeType is CascadeType.PERSIST on Category to pass this test
        //here category has a list of products --- meaning Category is the Parent and Product is the Child

        // 1. Setup Data
        Category cat = new Category("Laptops");

        Product p1 = new Product("DELL Inspiron");
        p1.setPrice(1000);
//        p1.setCategory(cat); -- even without setting the category in products, its reflecting in db, because of the sync helper we have addProduct
        Product p2 = new Product("Lenovo Notebook");
        p2.setPrice(1200);
//        p2.setCategory(cat);
        Product p3 = new Product("Macbook");
        p3.setPrice(2000);
//        p3.setCategory(cat);

        cat.addProduct(p1);
        cat.addProduct(p2);
        cat.addProduct(p3);
        entityManager.persist(cat);
        // Sync state to DB
        entityManager.flush();

        assertTrue(entityManager.contains(p1));
        assertTrue(entityManager.contains(p2));
        assertTrue(entityManager.contains(p3));

        entityManager.clear();

        assertFalse(entityManager.contains(p1));
        assertFalse(entityManager.contains(p2));
        assertFalse(entityManager.contains(p3));

        assertEquals(p1.toString(), entityManager.find(Product.class, p1.getUuid()).toString());
        assertEquals(p2.toString(), entityManager.find(Product.class, p2.getUuid()).toString());
        assertEquals(p3.toString(), entityManager.find(Product.class, p3.getUuid()).toString());

    }

    @Test
    @Transactional
    void reproduce_cascade_on_delete_with_transient_products() {
        //change cascade type to CascadeType.REMOVE on Category
        // 1. Setup Data
        Category cat = new Category("Laptops");
        entityManager.persist(cat);

        Product p1 = new Product("DELL Inspiron");
        p1.setPrice(1000);
        p1.setCategory(cat);
        Product p2 = new Product("Lenovo Notebook");
        p2.setPrice(1200);
        p2.setCategory(cat);
        Product p3 = new Product("Macbook");
        p3.setPrice(2000);
        p3.setCategory(cat);
        entityManager.persist(cat);
        // Sync state to DB
        entityManager.flush();

//        entityManager.persist(p1);
//        entityManager.persist(p2);
//        entityManager.persist(p3);
//
        //manually populating the products in Category
/*        List<Product> products =new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);
        if(null == cat.getProducts()) {
            cat.setProducts(products);
        }else{
            var newProducts = new ArrayList<>(cat.getProducts());
            newProducts.addAll(products);
            cat.setProducts(newProducts);
        }*/
        Category ctgryFromDb = categoryRepository.findById(cat.getUuid()).get();
        cat.addProduct(p1);
        cat.addProduct(p2);
        cat.addProduct(p3);
        // now, check the category_id in each product and see if it has data after persist to db, doing it manually
        //without persist also, after adding prducts to category, if we flush to DB and check the category, it has the products
        // which is Dirty write...
//        entityManager.persist(cat);
        // Sync state to DB
        entityManager.flush();

// check if the PersistenceContext which is the L1 cache has the products before and after clearing
        assertTrue(entityManager.contains(p1));
        assertTrue(entityManager.contains(p2));
        assertTrue(entityManager.contains(p3));

        entityManager.clear();

        assertFalse(entityManager.contains(p1));
        assertFalse(entityManager.contains(p2));
        assertFalse(entityManager.contains(p3));

        ctgryFromDb = categoryRepository.findById(cat.getUuid()).get();
        List<UUID> prod_uuids = new ArrayList<>();
        for (Product p : ctgryFromDb.getProducts()) {
            UUID prod_uuid = p.getUuid();
            prod_uuids.add(prod_uuid);
            Product prdFromDb = productRepository.findById(prod_uuid).get();
            assertEquals(prdFromDb.getCategory().getUuid(), ctgryFromDb.getUuid());
        }
//      Remove the category first
        entityManager.remove(ctgryFromDb);
        entityManager.flush();
//        //NOW after category removed, check what happens to the products
        for (UUID uid : prod_uuids) {
            assertNull(entityManager.find(Product.class, uid));
        }
    }

}
