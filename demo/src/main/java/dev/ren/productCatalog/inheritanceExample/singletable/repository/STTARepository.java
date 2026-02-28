package dev.ren.productCatalog.inheritanceExample.singletable.repository;


import dev.ren.productCatalog.inheritanceExample.singletable.TA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface STTARepository extends JpaRepository<TA, Long> {
    TA save(TA ta);
}