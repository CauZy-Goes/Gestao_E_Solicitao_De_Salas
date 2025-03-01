package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.LogClient;
import io.github.cauzy.GSDS.DTO.LogAcoesDTO;
import io.github.cauzy.GSDS.DTO.UsuarioDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import io.github.cauzy.GSDS.Utility.Message;
import io.github.cauzy.GSDS.Utility.Utils.FacesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ViewScoped
public class LogAcoesMB implements Serializable {

    @Inject
    private LogClient logAcoesClient;

    private LogAcoesDTO logAcoesDTO = new LogAcoesDTO();

    private List<LogAcoesDTO> logAcoesDTOList;

    @PostConstruct
    public void init()  {
        try {
            logAcoesDTOList = logAcoesClient.listarLogs();
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar logAcoes: " + e.getMessage());
        }
    }

    public void addLogAcoes(String message) {
        try {
            UsuarioDTO usuarioDTO = FacesUtil.getUsuarioLogado();
            logAcoesClient.createLog(new LogAcoesDTO(LocalDateTime.now(), usuarioDTO.getIdUsuario(), message));
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    public LogAcoesDTO getLogAcoesDTO() {
        return logAcoesDTO;
    }

    public void setLogAcoesDTO(LogAcoesDTO logAcoesDTO) {
        this.logAcoesDTO = logAcoesDTO;
    }

    public List<LogAcoesDTO> getLogAcoesDTOList() {
        return logAcoesDTOList;
    }
}
