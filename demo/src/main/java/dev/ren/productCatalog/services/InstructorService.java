package dev.ren.productCatalog.services;

import dev.ren.productCatalog.inheritanceExample.tableperclass.Batch;
import dev.ren.productCatalog.inheritanceExample.tableperclass.Instructor;
import dev.ren.productCatalog.repositories.InstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Instructor getInstructorDetails(Long id){
        //fetch the entity using the JOIN FETCH method
        Instructor instructor = instructorRepository.findByIdWithBatches(id)
                .orElseThrow(()-> new RuntimeException("Instructor Not Found"));
        //map to DTO while the session is still active
        try {
            List<Batch> batches = instructor.getBatches();
            //UserService.findUserById(id).getEmail()
            return new Instructor("renu.rachakulla@gmail.com" ,batches);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
