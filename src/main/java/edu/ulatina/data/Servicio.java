/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amy
 */
public class Servicio {
    
    protected Connection conexion = null;
    private String host = "localhost";
    private String puerto = "3306";
    private String sid = "sistema_boleteria";
    private String usuario = "root";
    private String clave = "adminadmin";
 
 
    public void conectarBD() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://" + host + ":" + puerto + "/" + sid + "?serverTimezone=UTC";
        conexion = DriverManager.getConnection(url, usuario, clave);
    }
 
 
    public void cerrarPreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                if (!ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
 
 
    public void cerrarResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
 
  
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
                conexion = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
 
    protected Connection getConexion() {
        return conexion;
    }
 
  
    protected void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
}
