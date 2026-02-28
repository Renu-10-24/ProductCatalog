package dev.ren.productCatalog.inheritanceExample.mappedsuperclass.repository;

import dev.ren.productCatalog.inheritanceExample.mappedsuperclass.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MSCMentorRepository  extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor user);
    Mentor findMentorById(Long id);
}
