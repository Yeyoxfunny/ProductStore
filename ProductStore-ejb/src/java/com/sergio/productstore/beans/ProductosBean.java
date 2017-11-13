/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.beans;

import com.sergio.productstore.dao.anotacion.AppDAO;
import com.sergio.productstore.dao.ProductoDAO;
import com.sergio.productstore.dao.DAOException;
import com.sergio.productstore.dtos.ProductoDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;

/**
 *
 * @author sergio
 */
@Stateless
@LocalBean
public class ProductosBean {

    @Inject
    @AppDAO
    private ProductoDAO productoDAO;

    public ProductoDTO crear(ProductoDTO producto) throws DAOException {
        return productoDAO.crear(producto);
    }

    public void actualizar(ProductoDTO producto) throws DAOException {
        productoDAO.actualizar(producto);
    }

    public void eliminar(Integer id) throws DAOException {
        productoDAO.eliminar(id);
    }
    
    public ProductoDTO obtenerPorId(Integer id) throws DAOException {
        return productoDAO.obtenerPorId(id);
    }

    public List<ProductoDTO> obtenerTodos() throws DAOException {
        return productoDAO.obtenerTodos();
    }
}
