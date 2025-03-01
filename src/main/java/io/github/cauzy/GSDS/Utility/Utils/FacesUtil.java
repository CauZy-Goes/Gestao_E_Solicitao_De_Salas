package io.github.cauzy.GSDS.Utility.Utils;

import io.github.cauzy.GSDS.DTO.UsuarioDTO;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import jakarta.servlet.http.HttpSession;

public class FacesUtil {

    // Obtém o FacesContext atual
    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    // Obtém o ExternalContext atual
    public static ExternalContext getExternalContext() {
        FacesContext facesContext = getFacesContext();
        return (facesContext != null) ? facesContext.getExternalContext() : null;
    }

    // Obtém a sessão atual
    public static HttpSession getCurrentSession() {
        ExternalContext externalContext = getExternalContext();
        return (externalContext != null) ? (HttpSession) externalContext.getSession(false) : null;
    }

    // Obtém o usuário logado
    public static UsuarioDTO getUsuarioLogado() {
        HttpSession session = getCurrentSession();

        if (session != null) {
            UsuarioDTO usuarioGestor = (UsuarioDTO) session.getAttribute("gestor");
            UsuarioDTO usuarioProfessor = (UsuarioDTO) session.getAttribute("professor");

            return (usuarioGestor != null) ? usuarioGestor : usuarioProfessor;
        }
        return null; // Nenhum usuário logado
    }
}
