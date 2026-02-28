package dev.ren.productCatalog.inheritanceExample.joinedtable.repository;

import dev.ren.productCatalog.inheritanceExample.joinedtable.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JTMentorRepository extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor user);
    Mentor findMentorById(Long id);
}
