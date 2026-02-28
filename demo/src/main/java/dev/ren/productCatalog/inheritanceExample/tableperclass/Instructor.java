package dev.ren.productCatalog.inheritanceExample.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="tpc_instructor")
public class Instructor extends User {
    public Instructor(String email, List<Batch> batches){
        super(email);
        this.batches = batches;
    }
    private boolean handsome;
    private Double salary;
    // Default is FetchType.LAZY
    @OneToMany(mappedBy = "instructor")
    private List<Batch> batches;



//    // Getters and Setters
    public List<Batch> getBatches() { return this.batches; }
    public void addBatch(Batch batch){
        this.batches.add(batch);
        System.out.println("added the batch to instructor with id : "+this.id);
    }

    public void setHandsome(boolean b) {
        this.handsome = b;
    }
}



