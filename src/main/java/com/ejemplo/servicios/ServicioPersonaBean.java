/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.servicios;

import com.ejemplo.entidad.Persona;
import com.ejemplo.excepciones.StorageException;
import com.ejemplo.repositorio.RepositorioPersona;

import java.io.File;
import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

import java.util.Collection;

import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author WorkNest5
 */
@Service
public class ServicioPersonaBean implements ServicioPersona{

    private final Path dirRoot = Paths.get("upload-dir");
    private final File baseFolder = new File("backend" + File.separator + "rifa");
    private static final Logger LOGGER = LoggerFactory.getLogger(ServicioPersonaBean.class);
    
   @Autowired
   private RepositorioPersona repositorio;

    
    @Override
    public Collection<Persona> findAll() {
        Collection<Persona> saludos = repositorio.findAll();
        return saludos;
    }

    @Override
    public Persona encuentraUno(Long id) {
        Persona persona = repositorio.findOne(id);
        return persona;
    }

    @Override
    public Persona crear(Persona persona) {
        if(persona.getId() != null){
            //No se puede crear la persona con el ID especificado
            return null;
        }
        Persona personaGuardada = repositorio.save(persona);
        return personaGuardada;
    }
    
    @Override
    public Persona actualizar(Persona persona) {
        
        Persona personaPersiste = encuentraUno(persona.getId());
        
        Persona personaActualizada = repositorio.save(persona);
        return personaActualizada;
    }

    @Override
    public void borrar(Long id) {
        
         repositorio.delete(id);
         
         
    }

    @Override
    public String insertarSQL(Long id) {
        String persona = repositorio.regresarpersona(id);
        return persona;
    }

    @Override
    public Stream<Path> cargarTodo(){
        try {
                return Files.walk(this.dirRoot, 1)
                            .filter(path -> !path.equals(this.dirRoot))
                            .map(path -> this.dirRoot.relativize(path));
                }
                catch (IOException e) {
                throw new StorageException("Failed to read stored files", e);
                }
    }

    
    //////////////////////////////////////////////////////////////////////////////
    @Override
    public void guardar(MultipartFile archivo) {
     
        long unixTimestamp =  Instant.now().getEpochSecond();

        String filename = StringUtils.cleanPath(String.valueOf(unixTimestamp) + agregarExtension(archivo.getOriginalFilename()));
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/integration/FtpOutboundChannelAdapterSample-context.xml");
         
        MessageChannel ftpChannel = ctx.getBean("ftpChannel",MessageChannel.class);

         
        final File archivoAEnviar = new File(filename);
        
        try {
            
            FileUtils.copyInputStreamToFile(archivo.getInputStream(), archivoAEnviar);
            
        
        } catch (IOException ex) {
            LOGGER.info("Valio verga");
        }

       
        final Message<File> messageOrders =MessageBuilder.withPayload(archivoAEnviar).build();
        
        ftpChannel.send(messageOrders);
        FileUtils.deleteQuietly(archivoAEnviar);

        ctx.close();
                    }
    
    
    public String agregarExtension(String filenameOG){
        
        String filenameVolteado = new StringBuilder(filenameOG).reverse().toString();
        String extension = "";
        for(char c : filenameVolteado.toCharArray()){
            
            if(c == '.'){
                extension += '.';
                return new StringBuilder(extension).reverse().toString();
            }
            else{
                extension += c;
            }
        }
        return new StringBuilder(extension).reverse().toString();
    }
}
