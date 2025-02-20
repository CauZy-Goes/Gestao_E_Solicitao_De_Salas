package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.StatusClient;
import io.github.cauzy.GSDS.DTO.StatusDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import io.github.cauzy.GSDS.Utility.Message;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class StatusMB implements Serializable {

    @Inject
    private StatusClient statusClient;

    private StatusDTO statusDTO = new StatusDTO();

    private List<StatusDTO> statusDTOList;

    @PostConstruct
    public void init()  {
        try {
            statusDTOList = statusClient.listarStatus();
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar status: " + e.getMessage());
        }
    }

    public StatusDTO getStatusDTO() {
        return statusDTO;
    }

    public void setStatusDTO(StatusDTO statusDTO) {
        this.statusDTO = statusDTO;
    }

    public List<StatusDTO> getStatusDTOList() {
        return statusDTOList;
    }
}
