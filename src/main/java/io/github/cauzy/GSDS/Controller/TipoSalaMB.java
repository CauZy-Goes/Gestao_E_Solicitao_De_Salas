package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.TipoSalaClient;
import io.github.cauzy.GSDS.DTO.TipoSalaDTO;
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
public class TipoSalaMB implements Serializable {

    @Inject
    private TipoSalaClient tipoSalaClient;

    public TipoSalaDTO tipoSalaDTO = new TipoSalaDTO();

    List<TipoSalaDTO> tipoSalaDTOList;

    @PostConstruct
    public void init()  {
        try {
            tipoSalaDTOList = tipoSalaClient.listarTipoSalas();
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar tipoSala: " + e.getMessage());
        }
    }

    public TipoSalaDTO getTipoSalaDTO() {
        return tipoSalaDTO;
    }

    public void setTipoSalaDTO(TipoSalaDTO tipoSalaDTO) {
        this.tipoSalaDTO = tipoSalaDTO;
    }

    public List<TipoSalaDTO> getTipoSalaDTOList() {
        return tipoSalaDTOList;
    }
}
