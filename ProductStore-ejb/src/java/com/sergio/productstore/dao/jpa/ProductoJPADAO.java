/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.dao.jpa;

import com.sergio.productstore.dao.ProductoDAO;
import com.sergio.productstore.dao.DAOException;
import com.sergio.productstore.dao.anotacion.AppDAO;
import com.sergio.productstore.dao.jpa.conversores.ProductoConversor;
import com.sergio.productstore.dtos.ProductoDTO;
import com.sergio.productstore.entities.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author sergio
 */
@AppDAO
public class ProductoJPADAO implements ProductoDAO {
    
    @PersistenceContext(unitName = "ProductStore-ejbPU")
    private EntityManager em;
    
    private final ProductoConversor conversor = ProductoConversor.obtenerInstancia();;

    public ProductoJPADAO() {
    }

    @Override
    public ProductoDTO crear(ProductoDTO productoDTO) throws DAOException {
        try {
            Producto producto = conversor.convertirAEntidad(productoDTO);
            em.persist(producto);
            em.flush();
            return conversor.convertirADTO(producto);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void actualizar(ProductoDTO productoDTO) throws DAOException {
        try {
            Producto producto = conversor.convertirAEntidad(productoDTO);
            em.merge(producto);
            em.flush();
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void eliminar(Integer id) throws DAOException {
        try {
            Producto producto = em.find(Producto.class, id);
            if (producto == null) {
                throw new DAOException("El producto con el id: " + id + " no existe");
            }
            em.remove(producto);
            em.flush();
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public ProductoDTO obtenerPorId(Integer id) throws DAOException {
        try {
            Producto producto = em.find(Producto.class, id);
            if (producto == null) {
                throw new DAOException("El producto con el id: " + id + " no existe");
            }
            return conversor.convertirADTO(producto);
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<ProductoDTO> obtenerTodos() throws DAOException {
        try {
         List<Producto> productos = em.createQuery("SELECT p FROM Producto p").getResultList();
            return conversor.convertirADTO(productos);   
        } catch (PersistenceException e) {
            throw new DAOException(e);
        }        
    } 
}
