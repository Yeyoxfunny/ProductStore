/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productostore.controller;

import com.google.gson.Gson;
import com.sergio.productstore.beans.ProveedoresBean;
import com.sergio.productstore.dao.DAOException;
import com.sergio.productstore.dtos.ProveedorDTO;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergio
 */
@WebServlet(name = "ProveedoresServlet", urlPatterns = {"/proveedores", "/proveedores/"})
public class ProveedoresServlet extends HttpServlet {

    @EJB
    private ProveedoresBean proveedoresBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idProveedorStr = request.getParameter("id");
            if (idProveedorStr != null) {
                Integer idProveedor = Integer.parseInt(idProveedorStr);
                obtenerPorId(idProveedor, response);
            } else {
                obtenerTodos(response);
            }
        } catch (DAOException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    private void obtenerPorId(Integer idProveedor, HttpServletResponse response) throws DAOException, IOException, NumberFormatException {
        ProveedorDTO proveedor = proveedoresBean.obtenerPorId(idProveedor);
        Gson gson = new Gson();
        String proveedorJSON = gson.toJson(proveedor);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(proveedorJSON);
    }

    private void obtenerTodos(HttpServletResponse response) throws IOException, DAOException {
        List<ProveedorDTO> proveedores = proveedoresBean.obtenerTodos();
        Gson gson = new Gson();
        String proveedoresJSON = gson.toJson(proveedores);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(proveedoresJSON);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            ProveedorDTO proveedor = new ProveedorDTO(nombre);
            proveedor = proveedoresBean.crear(proveedor);
            Gson gson = new Gson();
            String proveedorJSON = gson.toJson(proveedor);
            
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().print(proveedorJSON);
        } catch (DAOException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer idProveedor = Integer.parseInt(request.getParameter("id"));
            proveedoresBean.eliminar(idProveedor);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            response.getWriter().print("");
        } catch (DAOException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer idProveedor = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            ProveedorDTO proveedor = new ProveedorDTO(idProveedor, nombre);
            proveedoresBean.actualizar(proveedor);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            response.getWriter().print("");
        } catch (DAOException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
