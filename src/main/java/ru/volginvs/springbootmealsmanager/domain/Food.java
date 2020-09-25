package ru.volginvs.springbootmealsmanager.domain;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Food {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String species;
  
    
    public Food(){
        
    }
    public Food(String name, String species) {
        this.name=name;
        this.species=species; 
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }   
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    
    public String toString() {
        return this.getName()+", "+this.getSpecies();
    }    
}