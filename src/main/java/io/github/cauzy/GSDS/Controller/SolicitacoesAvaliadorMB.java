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
public class SolicitacoesAvaliadorMB implements Serializable {

    @Inject
    private SolicitacaoClient solicitacaoClient;

    @Inject
    private LogAcoesMB logAcoesMB;

    private SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();

    private List<SolicitacaoDTO> solicitacaoDTOList;

    @PostConstruct
    public void init() {
        try {
            solicitacaoDTOList = solicitacaoClient.listarSolicitacoes();
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar solicitacao: " + e.getMessage());
        }
    }

    //    1 = pendente, 2 = Aceita, 3 = Rejeitada

    public void aceitarSolicitacao(SolicitacaoDTO solicitacaoDTO) throws EntityCreationException {
        solicitacaoDTO.setIdStatus(2);
        solicitacaoDTO.setDataHoraAprovacao(LocalDateTime.now());
        solicitacaoDTO.setIdUsuarioAvaliador(FacesUtil.getUsuarioLogado().getIdUsuario());
        solicitacaoClient.updateSolicitacao(solicitacaoDTO, solicitacaoDTO.getIdSolicitacoes() );
        logAcoesMB.addLogAcoes("A solicitacao com o id: " + solicitacaoDTO.getIdSolicitacoes() + " Foi Aceita");
    }

    public void rejeitarSolicitacao(SolicitacaoDTO solicitacaoDTO) throws EntityCreationException {
        solicitacaoDTO.setIdStatus(3);
        solicitacaoDTO.setDataHoraAprovacao(LocalDateTime.now());
        solicitacaoDTO.setIdUsuarioAvaliador(FacesUtil.getUsuarioLogado().getIdUsuario());
        solicitacaoClient.updateSolicitacao(solicitacaoDTO, solicitacaoDTO.getIdSolicitacoes());
        logAcoesMB.addLogAcoes("A solicitacao com o id: " + solicitacaoDTO.getIdSolicitacoes() + " foi Rejeitada");
    }

    public void preencherPopPupDescricao(SolicitacaoDTO solicitacaoDTO) {
        this.solicitacaoDTO.setDescricao(solicitacaoDTO.getDescricao());
    }

    public String dataHoraFormatada(LocalDateTime dataHora){
        if (dataHora == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm  dd/MM/yyyy");
        return dataHora.format(formatter);
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
