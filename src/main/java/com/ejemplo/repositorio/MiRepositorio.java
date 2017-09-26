/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.repositorio;

import com.ejemplo.entidad.Persona;
import java.util.List;

/**
 *
 * @author WorkNest5
 */
interface MiRepositorio {
    
    List<Persona> findByNameWithWeirdOrdering(String name);
}
