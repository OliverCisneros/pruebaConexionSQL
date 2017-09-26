/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author WorkNest5
 */
@Entity
@Table(name = "product")
public class Product {

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the numbers
     */
    public int getNumbers() {
        return numbers;
    }

    /**
     * @param numbers the numbers to set
     */
    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }
    
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "nombre")
    private String name;
    @Column(name="numbers")
    private int numbers;

    public Product() {
    }

    public Product(String id, String name, int numbers) {
        this.id = id;
        this.name = name;
        this.numbers = numbers;
    }

    public Product(String name, int numbers) {
        this.name = name;
        this.numbers = numbers;
    }
    
    
}
