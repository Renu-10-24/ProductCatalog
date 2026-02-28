package dev.ren.productCatalog.inheritanceExample.mappedsuperclass.repository;

import dev.ren.productCatalog.inheritanceExample.mappedsuperclass.TA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MSCTARepository extends JpaRepository<TA, Long> {
    TA save(TA ta);
}