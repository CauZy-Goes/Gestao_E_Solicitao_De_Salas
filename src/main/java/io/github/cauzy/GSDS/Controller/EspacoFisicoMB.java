package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.EspacoFisicoClient;
import io.github.cauzy.GSDS.DTO.EspacoFisicoDTO;
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
public class EspacoFisicoMB implements Serializable {

    @Inject
    private EspacoFisicoClient espacoFisicoClient;

    private EspacoFisicoDTO espacoFisicoDTO = new EspacoFisicoDTO();

    private List<EspacoFisicoDTO> espacoFisicoDTOList;

    @PostConstruct
    public void init()  {
        try {
            espacoFisicoDTOList = espacoFisicoClient.listarEspacosFisicos();
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar EspacoFisico: " + e.getMessage());
        }
    }

    public EspacoFisicoDTO getEspacoFisicoDTO() {
        return espacoFisicoDTO;
    }

    public void setEspacoFisicoDTO(EspacoFisicoDTO espacoFisicoDTO) {
        this.espacoFisicoDTO = espacoFisicoDTO;
    }

    public List<EspacoFisicoDTO> getEspacoFisicoDTOList() {
        return espacoFisicoDTOList;
    }
}
