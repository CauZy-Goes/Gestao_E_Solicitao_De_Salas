package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.SolicitacaoClient;
import io.github.cauzy.GSDS.DTO.SolicitacaoDTO;
import io.github.cauzy.GSDS.DTO.UsuarioDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import io.github.cauzy.GSDS.Utility.Message;
import io.github.cauzy.GSDS.Utility.Utils.FacesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class SolicitacoesAvaliadorMB extends SolicitacoesBaseMB {

    @PostConstruct
    @Override
    public void init() {
        try {
            solicitacaoDTOList = solicitacaoClient.listarSolicitacoes();
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar solicitações: " + e.getMessage());
        }
    }

    public void aceitarSolicitacao(SolicitacaoDTO solicitacaoDTO) throws EntityCreationException {
        solicitacaoDTO.setIdStatus(2);
        solicitacaoDTO.setDataHoraAprovacao(LocalDateTime.now());
        solicitacaoDTO.setIdUsuarioAvaliador(FacesUtil.getUsuarioLogado().getIdUsuario());
        solicitacaoClient.updateSolicitacao(solicitacaoDTO, solicitacaoDTO.getIdSolicitacoes());
        logAcoesMB.addLogAcoes("A solicitação com id: " + solicitacaoDTO.getIdSolicitacoes() + " foi aceita");
        init();
    }

    public void rejeitarSolicitacao(SolicitacaoDTO solicitacaoDTO) throws EntityCreationException {
        solicitacaoDTO.setIdStatus(3);
        solicitacaoDTO.setDataHoraAprovacao(LocalDateTime.now());
        solicitacaoDTO.setIdUsuarioAvaliador(FacesUtil.getUsuarioLogado().getIdUsuario());
        solicitacaoClient.updateSolicitacao(solicitacaoDTO, solicitacaoDTO.getIdSolicitacoes());
        logAcoesMB.addLogAcoes("A solicitação com id: " + solicitacaoDTO.getIdSolicitacoes() + " foi rejeitada");
        init();
    }
}

