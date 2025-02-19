package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.UsuarioClient;
import io.github.cauzy.GSDS.DTO.UsuarioDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import io.github.cauzy.GSDS.Utility.Message;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Named
@ViewScoped
public class UsuarioMB implements Serializable {

    @Inject
    private UsuarioClient usuarioClient;

    private UsuarioDTO usuarioDTO = new UsuarioDTO();

    private List<UsuarioDTO> usuariosList ;

    @PostConstruct
    public void init()  {
        try {
            usuariosList = usuarioClient.listarUsuarios();
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    public void adicionar() {
        try {
            usuarioClient.createUsuario(usuarioDTO);
            init();
            login();
            usuarioDTO = new UsuarioDTO();
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    public void login() {
        try {
            UsuarioDTO usuarioLogin = usuarioClient.getUsuarioByEmail(usuarioDTO.getEmail());
            if(Objects.equals(usuarioDTO.getSenha(), usuarioLogin.getSenha())) {

                // Redirecinar para a pagina
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                HttpSession session = (HttpSession) externalContext.getSession(true);

                Boolean userIsGestor = (usuarioLogin.getIdCargo() == 1);

                String dashboardURL = userIsGestor ? "/pages/gestor/dashboardGestor.xhtml" : "/pages/professor/dashboardProfessor.xhtml";
                String usuarioCargo = userIsGestor ? "gestor" : "professor";

                session.setAttribute(usuarioCargo, usuarioLogin);


                externalContext.redirect(externalContext.getRequestContextPath() + dashboardURL);
            } else {
                Message.erro("Senha incorreta");
                }
        } catch (EntityNotFoundException e) {
            Message.erro("Esse email não está cadastrado");
        }
        catch (IOException e) {
            Message.erro("Erro ao redirecionar");
        }
    }

    public void logout() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session != null) {
            session.invalidate(); // Encerra a sessão
        }

        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml"); // Redireciona para o login
    }


    public List<UsuarioDTO> getUsuariosList() {
        return usuariosList;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }
}
