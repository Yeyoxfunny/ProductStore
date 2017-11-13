/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.dao;

import java.util.List;

/**
 *
 * @author sergio
 */
public interface GenericDAO<E, K> {
    
    E crear(E entidad) throws DAOException;
    
    void actualizar(E entidad) throws DAOException;
    
    void eliminar(K id) throws DAOException;
    
    E obtenerPorId(K id) throws DAOException;
    
    List<E> obtenerTodos() throws DAOException;
}
