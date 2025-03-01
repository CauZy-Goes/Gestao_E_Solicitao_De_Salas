package io.github.cauzy.GSDS.Controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named
@SessionScoped
public class Utils implements Serializable {

    public String dataHoraFormatada(LocalDateTime dataHora){
        if (dataHora == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm  dd/MM/yyyy");
        return dataHora.format(formatter);
    }
}
