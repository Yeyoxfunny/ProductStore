/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.dao.jpa.conversores;

import com.sergio.productstore.dtos.ProveedorDTO;
import com.sergio.productstore.entities.Proveedor;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author sergio
 */
public class ProveedorConversor implements PojoConversor<Proveedor, ProveedorDTO> {

    private static ProveedorConversor instancia;
    
    private ProveedorConversor() {
    }
    
    public static ProveedorConversor obtenerInstancia() {
        if (instancia == null) {
            instancia = new ProveedorConversor();
        }
        return instancia;
    }
    
    @Override
    public ProveedorDTO convertirADTO(Proveedor entidad) {
        return new ProveedorDTO(entidad.getId(), entidad.getNombre());
    }

    @Override
    public Proveedor convertirAEntidad(ProveedorDTO dto) {
        return new Proveedor(dto.getId(), dto.getNombre());
    }

    @Override
    public List<ProveedorDTO> convertirADTO(List<Proveedor> entidades) {
        return entidades.stream().map(this::convertirADTO).collect(toList());
    }

    @Override
    public List<Proveedor> convertirAEntidad(List<ProveedorDTO> dtos) {
        return dtos.stream().map(this::convertirAEntidad).collect(toList());
    }
    
}
