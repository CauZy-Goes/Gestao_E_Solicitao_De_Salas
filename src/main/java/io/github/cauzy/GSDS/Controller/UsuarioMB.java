package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.UsuarioClient;
import io.github.cauzy.GSDS.DTO.UsuarioDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import io.github.cauzy.GSDS.Utility.Message;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

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
                Message.info("Login com sucesso");
            } else {
                Message.erro("Senha incorreta");
                }
        } catch (EntityNotFoundException e){
            Message.erro("Esse email não esta cadastrado");
        }
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
