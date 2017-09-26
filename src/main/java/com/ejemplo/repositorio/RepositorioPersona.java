/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.repositorio;

import com.ejemplo.entidad.Persona;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WorkNest5
 */
@Repository
public interface RepositorioPersona extends JpaRepository<Persona, Long>{
    
        @Procedure
       String regresarpersona(Long id);
}
