package dev.ren.productCatalog.inheritanceExample.singletable.repository;


import dev.ren.productCatalog.inheritanceExample.singletable.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface STInstructorRepository extends JpaRepository<Instructor, Long> {
    Instructor save(Instructor user);
}