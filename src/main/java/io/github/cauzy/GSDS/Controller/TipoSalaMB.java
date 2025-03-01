package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.TipoSalaClient;
import io.github.cauzy.GSDS.DTO.EspacoFisicoDTO;
import io.github.cauzy.GSDS.DTO.LogAcoesDTO;
import io.github.cauzy.GSDS.DTO.TipoSalaDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import io.github.cauzy.GSDS.Utility.Exception.ForeignKeyException;
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
//            Verifica se é para modificar ou salvar
            tipoSalaClient.getTipoSalaById(tipoSalaDTO.getIdTipoSala());

//            Update
            tipoSalaClient.updateTipoSala(tipoSalaDTO);
            init();
            tipoSalaDTO = new TipoSalaDTO();
            Message.warn("Tipo de Sala Modificado com sucesso!");
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        } catch (EntityNotFoundException e) {
            salvar();
        }
    }

    public void salvar() {
        try {
            tipoSalaClient.createTipoSala(tipoSalaDTO);
            init();
            tipoSalaDTO = new TipoSalaDTO();
            Message.info("Tipo Sala salvo com sucesso!");
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    public void excluir() {
        try {
            tipoSalaClient.deleteTipoSala(tipoSalaDTO.getIdTipoSala());
            tipoSalaDTO = new TipoSalaDTO();
            init();
            Message.warn("O Tipo de Sala foi removido com sucesso!");
        } catch (EntityNotFoundException e){
            Message.erro( "O tipo de Sala não existe");
        }catch (ForeignKeyException e){
            Message.erro( "Uma sala depende desse tipo de sala");
        }
    }


    public String getNomeSala(Integer id) {
        try {
            return tipoSalaClient.getTipoSalaById(id).getNomeSala();
        } catch (EntityNotFoundException e){
            return e.getMessage();
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
