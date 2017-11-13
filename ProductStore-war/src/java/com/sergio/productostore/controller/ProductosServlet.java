/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productostore.controller;

import com.google.gson.Gson;
import com.sergio.productstore.dao.DAOException;
import com.sergio.productstore.dtos.ProductoDTO;
import com.sergio.productstore.beans.ProductosBean;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergio
 */
@WebServlet(name = "ProductosServlet", urlPatterns = {"/productos", "/productos/"})
public class ProductosServlet extends HttpServlet {

    @EJB
    private ProductosBean productosBean;
    
    private static final String HOME_VIEW = "/productos/home.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProductoStr = request.getParameter("id");
        if (idProductoStr != null) {
            obtenerPorId(idProductoStr, response);
        } else {
            obtenerTodos(request, response);
        }
    }

    private void obtenerPorId(String idProductoStr, HttpServletResponse response) throws IOException {
        try {
            Integer idProducto = Integer.parseInt(idProductoStr);
            ProductoDTO producto = productosBean.obtenerPorId(idProducto);
            Gson gson = new Gson();
            String productoJSON = gson.toJson(producto);
            response.setHeader("Content-type", "application/json");
            response.getWriter().write(productoJSON);
        } catch (DAOException ex) {
            response.getWriter().println("Error en SQL: " + ex);
        }
    }

    private void obtenerTodos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            List<ProductoDTO> productos = productosBean.obtenerTodos();
            request.setAttribute("productos", productos);
            System.out.println(request.getContextPath() + HOME_VIEW);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(HOME_VIEW);
            dispatcher.forward(request, response);
        } catch (DAOException ex) {
            response.getWriter().println("Error en SQL: " + ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProductoDTO producto = extraerProducto(request);
            productosBean.crear(producto);
            response.sendRedirect("productos");
        } catch (DAOException ex) {
            response.getWriter().println("Error en SQL: " + ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProductoStr = request.getParameter("id");
        if (!idEsValido(idProductoStr)) {
            response.getWriter().print("El id no es valido");
            return;
        }
        System.out.println("Id Producto: " + idProductoStr);
        Integer idProducto = Integer.parseInt(idProductoStr);
        ProductoDTO producto = extraerProducto(request);
        producto.setId(idProducto);
        actualizarProducto(producto, response);
    }

    private void actualizarProducto(ProductoDTO producto, HttpServletResponse response) throws IOException {
        try {
            productosBean.actualizar(producto);
            response.getWriter().print("El producto se ha registrado satisfactoriamente");
        } catch (DAOException ex) {
            response.getWriter().println("Error en SQL: " + ex);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProductoStr = request.getParameter("id");
        if (!idEsValido(idProductoStr)) {
            response.getWriter().print("El id no es valido");
            return;
        }
        Integer idProducto = Integer.parseInt(idProductoStr);
        eliminarProducto(idProducto, response);
    }

    private void eliminarProducto(Integer idProducto, HttpServletResponse response) throws IOException {
        try {
            productosBean.eliminar(idProducto);
            response.getWriter().println("El producto con el id " + idProducto + " se ha eliminado");
        } catch (DAOException ex) {
            response.getWriter().println("Error en SQL: " + ex);
        }
    }

    private static boolean idEsValido(String idProductoStr) {
        return idProductoStr != null && idProductoStr.matches("\\d+");
    }

    private ProductoDTO extraerProducto(HttpServletRequest request) {
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String estaDisponible = request.getParameter("disponible");
        Integer idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        System.out.println("Id proveedor" + idProveedor);
        boolean disponible = estaDisponible != null ? Boolean.parseBoolean(estaDisponible) : false;
        ProductoDTO producto = new ProductoDTO(nombre, precio, disponible);
        producto.setIdProveedor(idProveedor);
        return producto;
    }
}
