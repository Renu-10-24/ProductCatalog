package dev.ren.productCatalog.inheritanceExample.tableperclass.repository;

import dev.ren.productCatalog.inheritanceExample.tableperclass.TA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TPCTARepository  extends JpaRepository<TA, Long> {
    TA save(TA mentor);
    TA findMentorById(Long id);
}