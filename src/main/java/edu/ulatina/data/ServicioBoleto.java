package edu.ulatina.data;

import edu.ulatina.models.Boleto;
import edu.ulatina.models.Boleto.TipoUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioBoleto extends Servicio {

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

    public void RegistrarBoleto(Boleto boleto) throws SQLException, NullPointerException, ClassNotFoundException {

        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "INSERT INTO boleto (cedula, nombre, edad, sexo, tipo_usuario, ruta, placa_bus, precio, hora) VALUES (?,?,?,?,?,?,?,?,?)";
            pstmt = super.getConexion().prepareStatement(sql);

            pstmt.setInt(1, boleto.getCedula());
            pstmt.setString(2, boleto.getNombre());
            pstmt.setInt(3, boleto.getEdad());
            pstmt.setString(4, boleto.getSexo());
            pstmt.setString(5, boleto.getTipo().toString());
            pstmt.setString(6, boleto.getRuta());
            pstmt.setString(7, boleto.getPlacaDeBus());
            pstmt.setDouble(8, descuentoPorEdad(boleto.getTipo().toString()));
            pstmt.setString(9, boleto.getHora());
            int cambios = pstmt.executeUpdate();

            if (cambios == 0) {
                throw new SQLException("No se logro realizar el insert");
            }

        } catch (SQLException | ClassNotFoundException | NullPointerException ex) {
            throw ex;
        } finally {

            cerrarPreparedStatement(pstmt);
            cerrarConexion();

        }

    }

    public List<Boleto> historialBoletos() throws SQLException, ClassNotFoundException {
        List<Boleto> listaRetorno = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            super.conectarBD();
            String sql = "SELECT id,cedula,nombre,tipo_usuario,ruta,placa_bus,precio,hora FROM boleto";
            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                Boleto boleto = new Boleto();
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

        } catch (SQLException | ClassNotFoundException ex) {
            throw ex;
        } finally {

            cerrarPreparedStatement(pstmt);
            cerrarResultSet(rs);
            cerrarConexion();

        }

        return listaRetorno;

    }

    public List<Boleto> buscarBoletosPorCedula(String cedulaStr) {
        List<Boleto> listaRetorno = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            int cedula = Integer.parseInt(cedulaStr); 
            super.conectarBD();
            String sql = "SELECT id, cedula, nombre, tipo_usuario, ruta, placa_bus, precio, hora FROM boleto WHERE cedula = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, cedula); 
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Boleto boleto = new Boleto();
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

        } catch (NumberFormatException e) {
            System.out.println("Error: La cédula debe ser un número válido");
        } catch (Exception e) {
            System.out.println("Error al buscar boletos por cédula: " + e.getMessage());
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarResultSet(rs);
            cerrarConexion();
        }

        return listaRetorno;
    }

}
