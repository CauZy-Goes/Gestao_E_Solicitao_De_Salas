package io.github.cauzy.GSDS.Utility;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class Message {

    public static void info(String text){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, text, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void erro(String text){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, text, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void warn(String text){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, text, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
