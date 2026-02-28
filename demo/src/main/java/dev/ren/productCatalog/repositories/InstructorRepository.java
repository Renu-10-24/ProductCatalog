package dev.ren.productCatalog.repositories;

import dev.ren.productCatalog.inheritanceExample.tableperclass.Instructor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
// The "JOIN FETCH" ensures the collection is initialized in 1 SQL query
//SELECT i FROM Instructor i JOIN FETCH i.batches WHERE i.id = :id - older way of doing a join fetch for one to many mapping
@Query("SELECT i FROM Instructor i WHERE i.id = :id")
Optional<Instructor> findByIdWithBatches(@Param("id") Long id);
Optional<Instructor> findById(@Param("id") Long id);
}
