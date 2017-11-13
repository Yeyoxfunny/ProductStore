/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.beans;

import com.sergio.productstore.dao.DAOException;
import com.sergio.productstore.dao.ProveedorDAO;
import com.sergio.productstore.dao.anotacion.AppDAO;
import com.sergio.productstore.dtos.ProveedorDTO;
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
public class ProveedoresBean {

    @Inject
    @AppDAO
    private ProveedorDAO proveedorDAO;

    public ProveedorDTO crear(ProveedorDTO proveedor) throws DAOException {
        return proveedorDAO.crear(proveedor);
    }

    public void actualizar(ProveedorDTO proveedor) throws DAOException {
        proveedorDAO.actualizar(proveedor);
    }

    public void eliminar(Integer id) throws DAOException {
        proveedorDAO.eliminar(id);
    }

    public ProveedorDTO obtenerPorId(Integer id) throws DAOException {
        return proveedorDAO.obtenerPorId(id);
    }

    public List<ProveedorDTO> obtenerTodos() throws DAOException {
        return proveedorDAO.obtenerTodos();
    }
}
