package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.SolicitacaoClient;
import io.github.cauzy.GSDS.DTO.SolicitacaoDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import io.github.cauzy.GSDS.Utility.Message;
import io.github.cauzy.GSDS.Utility.Utils.FacesUtil;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public abstract class SolicitacoesBaseMB implements Serializable {

    @Inject
    protected SolicitacaoClient solicitacaoClient;

    @Inject
    protected LogAcoesMB logAcoesMB;

    protected SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();
    protected List<SolicitacaoDTO> solicitacaoDTOList;

    public abstract void init();

    public void preencherPopPupDescricao(SolicitacaoDTO solicitacaoDTO) {
        this.solicitacaoDTO = solicitacaoDTO;
    }

    public void updateSolicitacao() {
        try {
            solicitacaoClient.updateSolicitacao(solicitacaoDTO, solicitacaoDTO.getIdSolicitacoes());
            Message.info("Solicitação atualizada com sucesso!");
            logAcoesMB.addLogAcoes("A solicitação com id: " + solicitacaoDTO.getIdSolicitacoes() + " foi Atualizada");
        } catch (EntityCreationException e) {
            Message.erro("Erro ao atualizar solicitação: " + e.getMessage());
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
