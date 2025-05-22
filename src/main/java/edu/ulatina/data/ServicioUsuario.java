/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.data;

import edu.ulatina.modelds.Usuario;
import edu.ulatina.modelds.Usuario.TipoUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laboratorio
 */
public class ServicioUsuario extends Servicio {

    public double descuentoPorEdad(String tipoUsuario) {

        double precio = 500;

        double descuento = 0;

        if ("NIÑO".equals(tipoUsuario)) {
            descuento = precio * 0.25;
        } else if ("ADULTO_MAYOR".equals(tipoUsuario)) {
            descuento = precio * 0.50;
        }

        double precioFinal = precio - descuento;
        return precioFinal;
    }
    

    public void insertarUsuario(Usuario usuario) {

        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "INSERT INTO usuario (cedula, nombre, edad, sexo, tipo_usuario, ruta, placa_bus, precio, hora) VALUES (?,?,?,?,?,?,?,?,?)";
            pstmt = super.getConexion().prepareStatement(sql);

            pstmt.setInt(1, usuario.getCedula());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setInt(3, usuario.getEdad());
            usuario.asignarTipo(usuario.getEdad());
            pstmt.setString(4, usuario.getSexo());
            pstmt.setString(5, usuario.getTipo().toString());
            pstmt.setString(6, usuario.getRuta());
            pstmt.setString(7, usuario.getPlacaDeBus());
            pstmt.setDouble(8, descuentoPorEdad(usuario.getTipo().toString()));
            pstmt.setString(9, usuario.getHora());
            int cantidad = pstmt.executeUpdate();

            if (cantidad == 0) {
                throw new SQLException("No se logro realizar el insert");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {

            cerrarPreparedStatement(pstmt);
            cerrarConexion();

        }

    }
    
    public List<Usuario> historialBoletos() {
        List<Usuario> listaRetorno = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            super.conectarBD();
            String sql = "SELECT id,cedula,nombre,tipo_usuario,ruta,placa_bus,precio,hora FROM usuario";
            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                Usuario boleto = new Usuario();
                boleto.setId(rs.getInt("id"));
                boleto.setCedula(rs.getInt("cedula"));
                boleto.setNombre(rs.getString("nombre"));
                boleto.setTipo(TipoUsuario.valueOf(rs.getString("tipo_usuario")));
                boleto.setRuta(rs.getString("ruta"));
                boleto.setPlacaDeBus(rs.getString("placa_bus"));
                boleto.setPrecio(rs.getDouble("precio"));
                boleto.setHora(rs.getString("hora"));
                
                listaRetorno.add(boleto);

            }

        } catch (Exception e) {
        } finally {

            cerrarPreparedStatement(pstmt);
            cerrarResultSet(rs);
            cerrarConexion();

        }

        return listaRetorno;

    }
}
