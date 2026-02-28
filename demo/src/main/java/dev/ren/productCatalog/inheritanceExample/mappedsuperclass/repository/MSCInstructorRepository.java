package dev.ren.productCatalog.inheritanceExample.mappedsuperclass.repository;

import dev.ren.productCatalog.inheritanceExample.mappedsuperclass.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MSCInstructorRepository extends JpaRepository<Instructor, Long> {
    Instructor save(Instructor user);
}