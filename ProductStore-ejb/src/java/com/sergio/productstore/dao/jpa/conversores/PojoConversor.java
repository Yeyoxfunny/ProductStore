/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.dao.jpa.conversores;

import java.util.List;

/**
 *
 * @author sergio
 */
public interface PojoConversor<E, D> {
    
    D convertirADTO(E entidad);
    
    E convertirAEntidad(D dto);
    
    List<D> convertirADTO(List<E> entidades);
    
    List<E> convertirAEntidad(List<D> dtos);
}
