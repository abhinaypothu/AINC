package com.example.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "emd")
public class EMD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "emd_id")
    private List<DynamicEMD> dynamicEMDs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DynamicEMD> getDynamicEMDs() {
        return dynamicEMDs;
    }

    public void setDynamicEMDs(List<DynamicEMD> dynamicEMDs) {
        this.dynamicEMDs = dynamicEMDs;
    }
}
