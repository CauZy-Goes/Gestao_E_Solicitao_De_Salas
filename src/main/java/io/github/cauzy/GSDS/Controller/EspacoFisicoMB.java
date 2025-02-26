package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.EspacoFisicoClient;
import io.github.cauzy.GSDS.DTO.EspacoFisicoDTO;
import io.github.cauzy.GSDS.DTO.UsuarioDTO;
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

    public void adicionar() throws EntityCreationException {
        try {
//            Verifica de é para dar update ou save
            espacoFisicoClient.getEspacoFisicoById(espacoFisicoDTO.getIdEspacoFisico());

            espacoFisicoClient.updateEspacoFisico(espacoFisicoDTO);
            init();
            espacoFisicoDTO = new EspacoFisicoDTO();
            Message.warn("Sala Modificada com sucesso!");
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        } catch (EntityNotFoundException e) {
            salvar();
        }
    }

    public void salvar() {
        try {
            espacoFisicoClient.createEspacoFisico(espacoFisicoDTO);
            init();
            espacoFisicoDTO = new EspacoFisicoDTO();
            Message.info("Sala salva com sucesso!");
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    public void excluir() {
        try {
            espacoFisicoClient.deleteEspacoFisico(espacoFisicoDTO.getIdEspacoFisico());
            espacoFisicoDTO = new EspacoFisicoDTO();
            init();
            Message.warn( "A Sala foi removida");
        } catch (EntityNotFoundException e){
            Message.erro( "A Sala não existe");
        }catch (ForeignKeyException e){
            Message.erro( "Uma solicitacao depende dessa sala");
        }
    }

    public Integer getNumeroEspacoFisicos(Integer id) throws EntityNotFoundException {
        return espacoFisicoClient.getEspacoFisicoById(id).getNumero();
    }

    public Integer getIdTipoSala(Integer id) throws EntityNotFoundException {
        return espacoFisicoClient.getEspacoFisicoById(id).getIdTipoSala();
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
