/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.controlador;

import com.ejemplo.entidad.Persona;
import com.ejemplo.servicios.ServicioPersona;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author WorkNest5
 */

@RestController
@RequestMapping("/testPersona")
public class ControladorPersona {
    
    @Autowired
    private ServicioPersona servicioPersona;
    
   ////////////////////////////////////////////////////////////////////////////////////////
    
    
    @PostMapping("/")
    public String manejadorSubirArchivos(@RequestParam("archivo") MultipartFile archivo){
        
      
        servicioPersona.guardar(archivo);
        return "Archivo subido";
    }
    

    /////////////////////////////////////////////////////////////////////////////////////////
    
    @RequestMapping(value="/personas",
                    method=RequestMethod.GET, 
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Persona>> getPersonas(){
        
        Collection<Persona> saludos = servicioPersona.findAll();
        
        return new ResponseEntity<Collection<Persona>>(saludos, HttpStatus.OK);
    }
    
    @RequestMapping(value="/personas/{id}",
                    method=RequestMethod.GET, 
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> getPersona(@PathVariable("id")Long id){
        
        Persona persona = servicioPersona.encuentraUno(id);
        
        if(persona == null){
            return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
            
    }
    
    @RequestMapping(value="/personas", 
                    method=RequestMethod.POST, 
                    consumes=MediaType.APPLICATION_JSON_VALUE, 
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> creaPersona(@RequestBody Persona persona ){
        
        Persona personaGuardada = servicioPersona.crear(persona);
        return new ResponseEntity<Persona>(personaGuardada, HttpStatus.CREATED);
    }
    

    @RequestMapping(value="/actualizar", 
                    method=RequestMethod.PUT, 
                    consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity actualizaPersona(@RequestBody Persona persona){
        
        
        
        Persona personaActualizada = servicioPersona.actualizar(persona);
        
        
        
        if(personaActualizada == null){
            return new ResponseEntity<Persona>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<Persona>(personaActualizada, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/borrar/{id}",
                    method=RequestMethod.DELETE,
                    consumes=MediaType.APPLICATION_JSON_VALUE)
    public void borrarPersona(@PathVariable("id") Long id){
        
        servicioPersona.borrar(id);
    }
    
    
    //////////////////////////
    
    
    @RequestMapping(value="/insertarSQL/{id}",
                    method=RequestMethod.GET,
                    produces=MediaType.APPLICATION_JSON_VALUE)
    public String insertarSQL(@PathVariable("id") Long id){
        
        
        String personasRecuperadas = servicioPersona.insertarSQL(id);
        return personasRecuperadas;
    }
}
