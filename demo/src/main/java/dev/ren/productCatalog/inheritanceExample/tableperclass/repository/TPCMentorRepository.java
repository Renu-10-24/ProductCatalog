package dev.ren.productCatalog.inheritanceExample.tableperclass.repository;

import dev.ren.productCatalog.inheritanceExample.tableperclass.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TPCMentorRepository  extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);
    Mentor findMentorById(Long id);
}