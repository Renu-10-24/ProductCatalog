package dev.ren.productCatalog.inheritanceExample.singletable.repository;

import dev.ren.productCatalog.inheritanceExample.singletable.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface STMentorRepository extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor user);
    Mentor findMentorById(Long id);
}