package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.SolicitacaoClient;
import io.github.cauzy.GSDS.DTO.SolicitacaoDTO;
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

@Named
@ViewScoped
public class SolicitacoesMB implements Serializable {

    @Inject
    private SolicitacaoClient solicitacaoClient;

    public SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();

    List<SolicitacaoDTO> solicitacaoDTOList;

    @PostConstruct
    public void init()  {
        try {
            solicitacaoDTOList = solicitacaoClient.listarSolicitacoes();
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar solicitacao: " + e.getMessage());
        }
    }

    public void adicionar() {
        try {
            solicitacaoClient.createSolicitacao(solicitacaoDTO);
            init();
            solicitacaoDTO = new SolicitacaoDTO();
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    public SolicitacaoDTO getSolicitacaoDTO() {
        return solicitacaoDTO;
    }

    public void setSolicitacaoDTO(SolicitacaoDTO solicitacaoDTO) {
        this.solicitacaoDTO = solicitacaoDTO;
    }

    public List<SolicitacaoDTO> getSolicitacaoDTOList() {
        return solicitacaoDTOList;
    }
}
