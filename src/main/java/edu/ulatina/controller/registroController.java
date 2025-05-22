/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.controller;

import edu.ulatina.data.ServicioUsuario;
import edu.ulatina.modelds.Usuario;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author laboratorio
 */
@ManagedBean(name = "registroController")
@SessionScoped
public class registroController implements Serializable {
    private Usuario usuario = new Usuario();
    
    private ServicioUsuario servicioUsuario = new ServicioUsuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
     public void registrarYComprar() {
        try {
            servicioUsuario.insertarUsuario(usuario);
            

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
