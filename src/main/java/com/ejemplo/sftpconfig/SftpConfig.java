/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.sftpconfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class SftpConfig {

    @Autowired
    @Qualifier("myFtpSessionFactory")
    
    private SessionFactory myFtpSessionFactory;
    @Bean
    public SessionFactory myFtpSessionFactory()
    {
        DefaultFtpSessionFactory ftpSessionFactory = new DefaultFtpSessionFactory();
        ftpSessionFactory.setHost("192.168.0.25");
        ftpSessionFactory.setClientMode(0);
        ftpSessionFactory.setFileType(0);
        ftpSessionFactory.setPort(8001);
        ftpSessionFactory.setUsername("backend");
        ftpSessionFactory.setPassword("backend");
        return ftpSessionFactory;
    }
    }