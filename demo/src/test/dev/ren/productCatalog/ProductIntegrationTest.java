package dev.ren.productCatalog;
// MOVED: test.testcontainers.service -> testcontainers.service.connection

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.ren.productCatalog.models.Category;
import dev.ren.productCatalog.models.Product;
import dev.ren.productCatalog.repositories.CategoryRepository;
import dev.ren.productCatalog.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Testcontainers // manages the docker lifecycle
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductIntegrationTest {
    @Container  //starts the REAL MySQL 8.0.44
    @ServiceConnection
    static MySQLContainer mysql = new MySQLContainer("mysql:8.0.44");
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
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
        }, "MySQL should reject this insert because Category 999 does not exist");
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
    void reproduce_cascade_on_delete_with_transient_category() {
        // 1. Setup Data
        Category cat = new Category("Tech");
        entityManager.persist(cat);
        Product p = new Product("Laptop");
        p.setCategory(cat);

        entityManager.persist(p);
        // Sync state to DB
        entityManager.flush();
        // 2. Attempt illegal delete
        // H2 might delay this check, MySQL will fail here
        assertThrows(IllegalStateException.class, () -> {
            entityManager.remove(cat);
            entityManager.flush();
        });
    }
    @Test
    //dirty write to db without save or persist
    @Transactional // 1. Transaction Starts
    public void updateProductCategory() {

        Product product = new Product("Television");
        productRepository.saveAndFlush(product);
        // 1. Product is loaded into the Persistence Context (Managed State)
        Product prdFromDb = productRepository.findByName(product.getName()).get();
        Category category = new Category("Gadgets");
        categoryRepository.saveAndFlush(category);
        // 2. Category is loaded into the Persistence Context (Managed State)
        Category ctgryFromDb = categoryRepository.findByName(category.getName()).get();
        // 4. You only call the setter -- This is the dirty write, No .save() or .persist() is called here!
        prdFromDb.setCategory(ctgryFromDb);
        // 5.  Verification Technique
        entityManager.flush(); // Send to MySQL
        entityManager.clear(); // Wipe Java memory (L1 Cache)
        // 6. Assert: Pull a fresh copy from MySQL
        Product freshProduct = productRepository.findByName(product.getName()).get();
        // 7. Assert that the product has a category
        assertThat(freshProduct.getCategory()).isNotNull();
        assertThat(freshProduct.getCategory().getName()).isEqualTo("Gadgets");
    }
}
