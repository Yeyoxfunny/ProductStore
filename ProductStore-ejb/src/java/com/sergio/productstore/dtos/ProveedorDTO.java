/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.dtos;

import java.io.Serializable;

/**
 *
 * @author sergio
 */
public class ProveedorDTO implements Serializable {
    
    private Integer id;
    private String nombre;

    public ProveedorDTO() {
    }
    
    public ProveedorDTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public ProveedorDTO(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
