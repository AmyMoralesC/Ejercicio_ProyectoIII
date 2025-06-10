package edu.ulatina.models;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "boleto")
public class Boleto implements Serializable {

    private int id;
    private Integer cedula;
    private String nombre;
    private Integer edad;
    private String sexo;
    private String ruta;
    private String hora;
    private String placaDeBus;
    private double precio;

    public enum TipoUsuario {
        NIÑO,
        ADULTO,
        ADULTO_MAYOR
    }

    private TipoUsuario tipo;

    public Boleto() {
    }

    public Boleto(Integer cedula, String nombre, Integer edad, String sexo, String ruta, String hora, String placaDeBus, double precio) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.ruta = ruta;
        this.hora = hora;
        this.placaDeBus = placaDeBus;
        this.precio = precio;
        this.tipo = asignarTipo(edad);
    }

    private TipoUsuario asignarTipo(int edad) {
        if (edad < 17) {
            return TipoUsuario.NIÑO;
        } else if (edad < 65) {
            return TipoUsuario.ADULTO;
        } else {
            return TipoUsuario.ADULTO_MAYOR;
        }
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
        this.tipo = asignarTipo(edad); 
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPlacaDeBus() {
        return placaDeBus;
    }

    public void setPlacaDeBus(String placaDeBus) {
        this.placaDeBus = placaDeBus;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}
