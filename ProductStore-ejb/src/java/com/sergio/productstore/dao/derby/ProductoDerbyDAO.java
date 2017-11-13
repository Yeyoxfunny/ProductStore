/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.dao.derby;

import com.sergio.productstore.dao.ProductoDAO;
import com.sergio.productstore.dao.DAOException;
import com.sergio.productstore.dtos.ProductoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author sergio
 */
public class ProductoDerbyDAO implements ProductoDAO {

    private static final String INSERT = "INSERT INTO Producto(nombre, precio, disponible) VALUES(?, ?, ?)";
    private static final String DELETE = "DELETE FROM Producto WHERE id_producto = ?";
    private static final String UPDATE = "UPDATE Producto SET nombre = ?, precio = ?, disponible = ? WHERE id_producto = ? ";
    private static final String GET_ALL = "SELECT id_producto, nombre, precio, disponible FROM Producto";
    private static final String GET_ONE = "SELECT id_producto, nombre, precio, disponible FROM Producto WHERE id_producto = ?";

    public ProductoDerbyDAO() {
    }


    @Override
    public ProductoDTO crear(ProductoDTO producto) throws DAOException {
        Objects.requireNonNull(producto);
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setBoolean(3, producto.isDisponible());
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException(String.format("No se pudo insertar el producto %s", producto.getNombre()));
            }
            int idProducto = obtenerIdGenerado(statement);
            return new ProductoDTO(idProducto, producto.getNombre(), producto.getPrecio(), producto.isDisponible());
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDerbyDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error en SQL", ex);
        }
    }

    @Override
    public void actualizar(ProductoDTO producto) throws DAOException {
        Objects.requireNonNull(producto);
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setBoolean(3, producto.isDisponible());
            statement.setInt(4, producto.getId());
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas == 0) {
                throw new DAOException(String.format("No se pudo actualizar el producto con el id: %d", producto.getId()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDerbyDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error en SQL", ex);
        }
    }

    @Override
    public void eliminar(Integer id) throws DAOException {
        Objects.requireNonNull(id);
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException(String.format("No se pudo eliminar el producto con el id %d", id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDerbyDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error en SQL", ex);
        }
    }

    @Override
    public ProductoDTO obtenerPorId(Integer id) throws DAOException {
        Objects.requireNonNull(id);
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ONE)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                throw new DAOException(String.format("El producto con el id %d no existe", id));
            }
            return convertirAProducto(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDerbyDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error en SQL", ex);
        }
    }

    @Override
    public List<ProductoDTO> obtenerTodos() throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ALL);
                ResultSet rs = statement.executeQuery()) {

            List<ProductoDTO> productos = new ArrayList<>();
            while (rs.next()) {
                productos.add(convertirAProducto(rs));
            }
            return productos;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDerbyDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error en SQL", ex);
        }
    }

    private int obtenerIdGenerado(PreparedStatement statement) throws SQLException, DAOException {
        try (ResultSet idGenerado = statement.getGeneratedKeys()) {
            if (!idGenerado.next()) {
                throw new DAOException("Ningún ID generado, no se pudo insertar el producto.");
            }
            return idGenerado.getInt(1);
        }
    }

    private ProductoDTO convertirAProducto(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id_producto");
        String nombre = rs.getString("nombre");
        double precio = rs.getDouble("precio");
        boolean estaDisponible = rs.getBoolean("disponible");
        return new ProductoDTO(id, nombre, precio, estaDisponible);
    }
}
