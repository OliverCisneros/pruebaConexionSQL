/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.servicios;

import com.ejemplo.entidad.Persona;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Stream;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author WorkNest5
 */
public interface ServicioPersona {
    
    Collection<Persona> findAll();
    
    Persona encuentraUno(Long id);
    
    Persona crear(Persona persona);
    
    Persona actualizar (Persona persona);
    
    String insertarSQL(Long id);
    
    void borrar (Long id);
    
    Stream<Path> cargarTodo();
    
    void guardar(MultipartFile archivo);

    
}
