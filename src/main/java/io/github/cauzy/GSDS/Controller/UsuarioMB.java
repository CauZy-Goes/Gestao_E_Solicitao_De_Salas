package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.LogClient;
import io.github.cauzy.GSDS.Client.UsuarioClient;
import io.github.cauzy.GSDS.DTO.LogAcoesDTO;
import io.github.cauzy.GSDS.DTO.UsuarioDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import io.github.cauzy.GSDS.Utility.Message;

import io.github.cauzy.GSDS.Utility.Utils.FacesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Named
@ViewScoped
public class UsuarioMB implements Serializable {

    @Inject
    private UsuarioClient usuarioClient;

    @Inject
    private LogClient logClient;

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
            usuarioDTO = usuarioClient.createUsuario(usuarioDTO);
            createLogUser(usuarioDTO,"O usuario com id : " + usuarioDTO.getIdUsuario() + " foi cadastrado");

            init();
            login();
            usuarioDTO = new UsuarioDTO();
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    //    1 = gestor, 2 = Prefessor
    public void updateUser(){
        try {
            usuarioClient.updateUsuario(usuarioDTO.getIdUsuario(), usuarioDTO);
            createLogUser(usuarioDTO,"O usuario com id : " + usuarioDTO.getIdUsuario() + " foi modificado");

            updateUserSession();

            Message.info("Usuario atualizado com sucesso!");
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    public void updateUserSession(){
        HttpSession session = FacesUtil.getCurrentSession();
        Boolean userIsGestor = (usuarioDTO.getIdCargo() == 1);
        String usuarioCargo = userIsGestor ? "gestor" : "professor";
        session.setAttribute(usuarioCargo, usuarioDTO);
    }

    public void login() {
        try {
            UsuarioDTO usuarioLogin = usuarioClient.getUsuarioByEmail(usuarioDTO.getEmail());
            if(Objects.equals(usuarioDTO.getSenha(), usuarioLogin.getSenha())) {

                createSession(usuarioLogin);
                createLogUser(usuarioLogin,"O usuario com id : " + usuarioLogin.getIdUsuario() + " fez login");

            } else {
                Message.erro("Senha incorreta");
                }
        } catch (EntityNotFoundException e) {
            Message.erro("Esse email não está cadastrado");
        }
    }

    public void createSession(UsuarioDTO usuarioLogin) {
        try {
            // Redirecinar para a pagina
            ExternalContext externalContext = FacesUtil.getExternalContext();
            HttpSession session = FacesUtil.getCurrentSession();

            Boolean userIsGestor = (usuarioLogin.getIdCargo() == 1);

            String dashboardURL = userIsGestor ? "/pages/gestor/dashboardGestor.xhtml" : "/pages/professor/dashboardProfessor.xhtml";
            String usuarioCargo = userIsGestor ? "gestor" : "professor";

            session.setAttribute(usuarioCargo, usuarioLogin);

            externalContext.redirect(externalContext.getRequestContextPath() + dashboardURL);
        } catch(IOException e){
                Message.erro("Erro ao redirecionar");
            }
    }


    public void logout() {
        ExternalContext externalContext = FacesUtil.getExternalContext();
        HttpSession session = FacesUtil.getCurrentSession();

        usuarioDTO = getUsuarioLogado();
        createLogUser(usuarioDTO,"O usuario com id : " + usuarioDTO.getIdUsuario() + " fez log-out");

        if (session != null) {
            session.invalidate();
        }

        try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createLogUser(UsuarioDTO usuarioDTO, String message) {
        try {
            logClient.createLog(new LogAcoesDTO(LocalDateTime.now(), usuarioDTO.getIdUsuario(), message));
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    public UsuarioDTO getUsuarioLogado() {
        HttpSession session = FacesUtil.getCurrentSession();

        if (session != null) {
            UsuarioDTO usuarioGestor = (UsuarioDTO) session.getAttribute("gestor");
            UsuarioDTO usuarioProfessor = (UsuarioDTO) session.getAttribute("professor");

            if (usuarioGestor != null) {
                return usuarioGestor; // Retorna o usuário gestor
            } else if (usuarioProfessor != null) {
                return usuarioProfessor; // Retorna o usuário professor
            }
        }
        return null; // Retorna null se não houver usuário logado
    }

    public void prepararEdicao(){
        usuarioDTO = getUsuarioLogado();
    }


    public List<UsuarioDTO> getUsuariosList() {
        return usuariosList;
    }

    public String getNomeUsuarioById(Integer id) throws EntityNotFoundException {
        return usuarioClient.getUsuarioById(id).getNomeUsuario();
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }
}
