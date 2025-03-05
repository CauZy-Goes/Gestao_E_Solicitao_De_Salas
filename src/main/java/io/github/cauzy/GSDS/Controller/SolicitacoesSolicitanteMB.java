package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.SolicitacaoClient;
import io.github.cauzy.GSDS.DTO.SolicitacaoDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import io.github.cauzy.GSDS.Utility.Message;
import io.github.cauzy.GSDS.Utility.Utils.FacesUtil;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

@Named
@ViewScoped
public class SolicitacoesSolicitanteMB extends SolicitacoesBaseMB {

    @PostConstruct
    @Override
    public void init() {
        try {
            Integer idProfessor = FacesUtil.getUsuarioLogado().getIdUsuario();
            solicitacaoDTOList = solicitacaoClient.getSolicitacaoByIdSolicitante(idProfessor);
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar solicitações: " + e.getMessage());
        }
    }

    public void adicionar() {
        try {
            preencherSolicitacaoDefault();
            solicitacaoDTO = solicitacaoClient.createSolicitacao(solicitacaoDTO);

            logAcoesMB.addLogAcoes("A solicitação com id: " + solicitacaoDTO.getIdSolicitacoes() + " foi criada");

            init();
            solicitacaoDTO = new SolicitacaoDTO();
            Message.info("Salvo com sucesso");
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    private void preencherSolicitacaoDefault() {
        solicitacaoDTO.setDataHoraSolicitacao(LocalDateTime.now());
        Integer idProfessor = FacesUtil.getUsuarioLogado().getIdUsuario();
        solicitacaoDTO.setIdUsuarioSolicitante(idProfessor);
    }
}

