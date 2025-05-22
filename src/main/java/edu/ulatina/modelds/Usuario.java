/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.modelds;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author laboratorio
 */
@ManagedBean(name = "usuario")
@SessionScoped
public class Usuario implements Serializable {

    private int id;
    private int cedula;
    private int edad;
    private String sexo;
    private String nombre;
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

    public Usuario() {
    }

    public Usuario(int id, int cedula, int edad, String sexo, String nombre, String ruta, String hora, String placaDeBus, double precio) {
        this.id = id;
        this.cedula = cedula;
        this.edad = edad;
        this.sexo = sexo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.hora = hora;
        this.placaDeBus = placaDeBus;
        this.tipo = asignarTipo(edad);
        this.precio = precio;
    }

    public TipoUsuario asignarTipo(int edad) {

        if (edad < 17) {
            return tipo = TipoUsuario.NIÑO;
        } else if (edad < 65) {
            return tipo = TipoUsuario.ADULTO;
        } else {
            return tipo = TipoUsuario.ADULTO_MAYOR;
        }

    }
    

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
    

    public TipoUsuario getTipo() {
        return tipo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    

}
