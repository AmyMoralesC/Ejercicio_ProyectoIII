package edu.ulatina.controller;

import edu.ulatina.data.ServicioBoleto;
import edu.ulatina.models.Boleto;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "registroController")
public class registroController implements Serializable {

    private Boleto boleto = new Boleto();

    private ServicioBoleto servicioBoleto = new ServicioBoleto();

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    private void agregarMensajeError(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null));
    }

    private boolean validarDatosBoleto(Boleto boleto) {
        boolean esValido = true;

        if (boleto.getCedula() <= 0) {
            agregarMensajeError("Cédula inválida");
            esValido = false;
        }
        if (boleto.getNombre() == null || boleto.getNombre().trim().isEmpty()) {
            agregarMensajeError("El nombre es obligatorio");
            esValido = false;
        } else if (!boleto.getNombre().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            agregarMensajeError("El nombre solo debe contener letras");
            esValido = false;
        }
        if (boleto.getEdad() <= 0 || boleto.getEdad() > 120) {
            agregarMensajeError("La edad es inválida");
            esValido = false;
        }
        if (boleto.getRuta() == null || boleto.getRuta().trim().isEmpty()) {
            agregarMensajeError("La ruta es obligatoria");
            esValido = false;
        }
        if (boleto.getHora().trim().isEmpty()) {
            agregarMensajeError("La hora no es valida");
            esValido = false;
        }
        if (boleto.getPlacaDeBus().trim().isEmpty()) {
            agregarMensajeError("La placa esta vacia");
            esValido = false;
        }

        return esValido;
    }

    public void registrarYComprar() {
        if (!validarDatosBoleto(boleto)) {
            return;
        }

        try {
            servicioBoleto.registrarBoleto(boleto);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro el boleto correctamente", null));
            
            boleto = new Boleto();

        } catch (SQLException | ClassNotFoundException | NullPointerException ex) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar boleto: " + ex.getMessage(), null));
        }
    }

}
