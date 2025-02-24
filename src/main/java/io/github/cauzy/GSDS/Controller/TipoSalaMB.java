package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.TipoSalaClient;
import io.github.cauzy.GSDS.DTO.TipoSalaDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
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

    private TipoSalaDTO tipoSalaDTO = new TipoSalaDTO();

    private List<TipoSalaDTO> tipoSalaDTOList;

    @PostConstruct
    public void init()  {
        try {
            tipoSalaDTOList = tipoSalaClient.listarTipoSalas();
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar tipoSala: " + e.getMessage());
        }
    }

    public void adicionar() {
        try {
            Boolean existe = tipoSalaDTO.getIdTipoSala() != null;
            tipoSalaClient.createTipoSala(tipoSalaDTO);
            if (existe) {
                Message.warn("Tipo de Sala Modificado com sucesso!");
            } else {
                Message.info("Tipo de Sala salvo com sucesso!");
            }
            init();
            tipoSalaDTO = new TipoSalaDTO();
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    public void excluir() throws EntityNotFoundException {
        tipoSalaClient.deleteTipoSala(tipoSalaDTO.getIdTipoSala());
        tipoSalaDTO = new TipoSalaDTO();
        init();
        Message.erro("O Tipo de Sala foi removido com sucesso!");
    }


    public String getNomeSala(Integer id) throws EntityNotFoundException {
        return tipoSalaClient.getTipoSalaById(id).getNomeSala();
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
