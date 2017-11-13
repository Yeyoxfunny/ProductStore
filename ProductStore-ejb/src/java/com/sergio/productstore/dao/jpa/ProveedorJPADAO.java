/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.dao.jpa;

import com.sergio.productstore.dao.DAOException;
import com.sergio.productstore.dao.ProveedorDAO;
import com.sergio.productstore.dao.anotacion.AppDAO;
import com.sergio.productstore.dao.jpa.conversores.ProveedorConversor;
import com.sergio.productstore.dtos.ProveedorDTO;
import com.sergio.productstore.entities.Proveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author sergio
 */
@AppDAO
public class ProveedorJPADAO implements ProveedorDAO {
    
    @PersistenceContext(unitName = "ProductStore-ejbPU")
    private EntityManager em;
    
    private final ProveedorConversor conversor;

    public ProveedorJPADAO() {
        conversor = ProveedorConversor.obtenerInstancia();
    }
    
    @Override
    public ProveedorDTO crear(ProveedorDTO proveedorDTO) throws DAOException {
        try {
            Proveedor proveedor = conversor.convertirAEntidad(proveedorDTO);
            em.persist(proveedor);
            em.flush();
            return conversor.convertirADTO(proveedor);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void actualizar(ProveedorDTO proveedorDTO) throws DAOException {
        try {
            Proveedor proveedor = conversor.convertirAEntidad(proveedorDTO);
            em.merge(proveedor);
            em.flush();
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void eliminar(Integer id) throws DAOException {
        try {
            Proveedor proveedor = em.find(Proveedor.class, id);
            if (proveedor == null) {
                throw new DAOException("El proveedor con id " + id + " no existe");
            }
            em.remove(proveedor);
            em.flush();
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public ProveedorDTO obtenerPorId(Integer id) throws DAOException {
        try {
            Proveedor proveedor = em.find(Proveedor.class, id);
            if (proveedor == null) {
                throw new DAOException("El proveedor con id " + id + " no existe");
            }
            return conversor.convertirADTO(proveedor);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<ProveedorDTO> obtenerTodos() throws DAOException {
        try {
            List<Proveedor> proveedores = em.createQuery("SELECT p FROM Proveedor P").getResultList();
            return conversor.convertirADTO(proveedores);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }
    
}
