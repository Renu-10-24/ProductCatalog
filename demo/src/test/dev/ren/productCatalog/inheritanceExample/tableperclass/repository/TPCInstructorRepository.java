package dev.ren.productCatalog.inheritanceExample.tableperclass.repository;

import dev.ren.productCatalog.inheritanceExample.tableperclass.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TPCInstructorRepository extends JpaRepository<Instructor, Long> {
    Instructor save(Instructor instructor);
}
