/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.controller;

import edu.ulatina.data.ServicioUsuario;
import edu.ulatina.modelds.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Jaime
 */
@ManagedBean(name = "historialController")
@SessionScoped
public class historialController implements Serializable {

    ServicioUsuario usuario = new ServicioUsuario();

    private List<Usuario> boletos;

    public List<Usuario> getBoletos() {
        if (boletos == null) {
            
            boletos = usuario.historialBoletos();
            
        }
        return boletos;

    }

}
