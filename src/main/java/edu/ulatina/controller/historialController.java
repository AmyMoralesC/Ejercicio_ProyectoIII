/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ulatina.controller;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import edu.ulatina.data.ServicioUsuario;
import edu.ulatina.modelds.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "historialController")
@SessionScoped
public class historialController implements Serializable {

    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private List<Usuario> boletos;
    private String cedulaBusqueda;

    public List<Usuario> getBoletos() {
        return boletos;
    }

    public String getCedulaBusqueda() {
        return cedulaBusqueda;
    }

    public void setCedulaBusqueda(String cedulaBusqueda) {
        this.cedulaBusqueda = cedulaBusqueda;
    }

    public void buscarHistorial() {
    try {
        if (cedulaBusqueda == null || cedulaBusqueda.trim().isEmpty()) {
            // Si no hay cédula, mostrar todos los boletos
            boletos = servicioUsuario.historialBoletos();
        } else {
            // Buscar por cédula específica
            boletos = servicioUsuario.buscarBoletosPorCedula(cedulaBusqueda);
            if (boletos.isEmpty()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "No se encontraron boletos", 
                    "No existen boletos para la cédula ingresada");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    } catch (Exception e) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Error en la búsqueda", 
            e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
        System.out.println("Error al buscar historial: " + e.getMessage());
    }
}
}