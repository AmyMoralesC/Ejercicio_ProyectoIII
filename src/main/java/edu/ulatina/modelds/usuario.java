/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.modelds;

/**
 *
 * @author laboratorio
 */
public class usuario {
    private int id;
    private int cedula;
    private int edad;
    private String sexo;
    private String nombre;
    private String ruta;
    private String hora;
    private String placaDeBus;

    public usuario() {
    }

    public usuario(int id, int cedula, int edad, String sexo, String nombre, String ruta, String hora, String placaDeBus) {
        this.id = id;
        this.cedula = cedula;
        this.edad = edad;
        this.sexo = sexo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.hora = hora;
        this.placaDeBus = placaDeBus;
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
    
    
    
    
    
            }

