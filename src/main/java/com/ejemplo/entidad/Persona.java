/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

/**
 *
 * @author WorkNest5
 */
@Entity
@Table(name = "personOliver")
@NamedStoredProcedureQueries({

   @NamedStoredProcedureQuery(name = "regresarpersona", 

                              procedureName = "regresarpersona",

                              parameters = {
                                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Long.class),
                                 @StoredProcedureParameter(mode = ParameterMode.OUT, name = "retorno", type = String.class)
                              }
   )
}
)
public class Persona implements Serializable{

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * @param fName the fName to set
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @return the lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * @param lName the lName to set
     */
    public void setlName(String lName) {
        this.lName = lName;
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") 
    private Long id;
    @Column(name = "fname")
    private String fName;
    @Column(name = "lname")
    private String lName;

    public Persona() {
    }

    public Persona(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }
    
    
    
    
}
