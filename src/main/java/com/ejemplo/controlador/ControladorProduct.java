/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.controlador;

import com.ejemplo.entidad.Persona;
import com.ejemplo.entidad.Product;
import com.ejemplo.repositorio.RepositorioProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author WorkNest5
 */
@RestController
@RequestMapping("/testProduct")
public class ControladorProduct {
    
    public RepositorioProduct repositorio;
    @Autowired
    public ControladorProduct (RepositorioProduct repositorio){
        this.repositorio = repositorio;
    }
    
     @GetMapping("/insert")
    public String agregarProduct (@RequestParam(value="id", defaultValue="1") String id,
                                  @RequestParam(value="nombre", defaultValue="oliver") String nombre,
                                  @RequestParam(value="number", defaultValue="666") int numbers){
        repositorio.save(new Product(id,nombre,numbers));
        
        return "Insertado\n" + id + "\n" + nombre + "\n" + numbers;
    }
    
}
