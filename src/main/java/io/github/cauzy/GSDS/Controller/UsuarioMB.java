package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.UsuarioClient;
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
import java.util.List;
import java.util.Objects;

@Named
@ViewScoped
public class UsuarioMB implements Serializable {

    @Inject
    private UsuarioClient usuarioClient;

    @Inject
    private LogAcoesMB logAcoesMB;

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
            login();
            init();
            usuarioDTO = new UsuarioDTO();
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    //    1 = gestor, 2 = Prefessor
    public void updateUser(){
        try {
            usuarioClient.updateUsuario(usuarioDTO.getIdUsuario(), usuarioDTO);
            logAcoesMB.addLogAcoes("O usuario com id : " + usuarioDTO.getIdUsuario() + " foi modificado");

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
                logAcoesMB.addLogAcoes("O usuario com id : " + usuarioLogin.getIdUsuario() + " fez login");

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

        usuarioDTO = FacesUtil.getUsuarioLogado();
        logAcoesMB.addLogAcoes("O usuario com id : " + usuarioDTO.getIdUsuario() + " fez log-out");

        if (session != null) {
            session.invalidate();
        }

        try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public UsuarioDTO getUsuarioLogado() {
        return FacesUtil.getUsuarioLogado();
    }

    public void prepararEdicao(){
        usuarioDTO = FacesUtil.getUsuarioLogado();
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
