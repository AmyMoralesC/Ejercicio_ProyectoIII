package edu.ulatina.controller;

import edu.ulatina.data.ServicioBoleto;
import edu.ulatina.models.Boleto;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "historialController")
public class historialController implements Serializable {
    private Boleto boleto = new Boleto();
    private ServicioBoleto servicioBoleto = new ServicioBoleto();
    private List<Boleto> boletos;
    private String cedulaBusqueda;

    public List<Boleto> getBoletos() {
        try {
            if (boletos == null) {
                boletos = servicioBoleto.historialBoletos();
            }
        } catch (SQLException | ClassNotFoundException | NullPointerException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede visualizar el boleto" + ex.getMessage(), null));
        }
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
                boletos = servicioBoleto.historialBoletos();
            } else {
                boletos = servicioBoleto.buscarBoletosPorCedula(cedulaBusqueda);
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
