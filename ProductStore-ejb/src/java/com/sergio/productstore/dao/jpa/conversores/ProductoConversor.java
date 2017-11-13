/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.dao.jpa.conversores;

import com.sergio.productstore.dtos.ProductoDTO;
import com.sergio.productstore.entities.Producto;
import java.util.List;
import static java.util.stream.Collectors.toList;
/**
 *
 * @author sergio
 */
public class ProductoConversor implements PojoConversor<Producto, ProductoDTO> {
    
    private static ProductoConversor instancia;
    
    private ProductoConversor() {
    }
    
    public static ProductoConversor obtenerInstancia() {
        if (instancia == null) {
            instancia = new ProductoConversor();
        }
        return instancia;
    }

    @Override
    public ProductoDTO convertirADTO(Producto entidad) {
        ProductoDTO producto = new ProductoDTO();
        producto.setId(entidad.getId());
        producto.setNombre(entidad.getNombre());
        producto.setPrecio(entidad.getPrecio());
        producto.setDisponible(entidad.isDisponible());
        producto.setIdProveedor(entidad.getIdProveedor());
        return producto;
    }

    @Override
    public Producto convertirAEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setDisponible(dto.isDisponible());
        producto.setIdProveedor(dto.getIdProveedor());
        return producto;
    }

    @Override
    public List<ProductoDTO> convertirADTO(List<Producto> entidades) {
        return entidades.stream().map(this::convertirADTO).collect(toList());
    }

    @Override
    public List<Producto> convertirAEntidad(List<ProductoDTO> dtos) {
        return dtos.stream().map(this::convertirAEntidad).collect(toList());
    }
}
