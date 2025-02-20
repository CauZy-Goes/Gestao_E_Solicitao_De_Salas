package io.github.cauzy.GSDS.DTO;

import java.time.LocalDateTime;

public class SolicitacaoDTO {
    private Integer idSolicitacoes;
    private LocalDateTime dataHoraSolicitacao;
    private LocalDateTime dataHoraAprovacao;
    private LocalDateTime dataHoraLocacao;
    private Integer idUsuarioAvaliador;
    private Integer idUsuarioSolicitante;
    private Integer idEspacoFisico;
    private Integer idStatus;

    public Integer getIdSolicitacoes() {
        return idSolicitacoes;
    }

    public void setIdSolicitacoes(Integer idSolicitacoes) {
        this.idSolicitacoes = idSolicitacoes;
    }

    public LocalDateTime getDataHoraSolicitacao() {
        return dataHoraSolicitacao;
    }

    public void setDataHoraSolicitacao(LocalDateTime dataHoraSolicitacao) {
        this.dataHoraSolicitacao = dataHoraSolicitacao;
    }

    public LocalDateTime getDataHoraAprovacao() {
        return dataHoraAprovacao;
    }

    public void setDataHoraAprovacao(LocalDateTime dataHoraAprovacao) {
        this.dataHoraAprovacao = dataHoraAprovacao;
    }

    public LocalDateTime getDataHoraLocacao() {
        return dataHoraLocacao;
    }

    public void setDataHoraLocacao(LocalDateTime dataHoraLocacao) {
        this.dataHoraLocacao = dataHoraLocacao;
    }

    public Integer getIdUsuarioAvaliador() {
        return idUsuarioAvaliador;
    }

    public void setIdUsuarioAvaliador(Integer idUsuarioAvaliador) {
        this.idUsuarioAvaliador = idUsuarioAvaliador;
    }

    public Integer getIdUsuarioSolicitante() {
        return idUsuarioSolicitante;
    }

    public void setIdUsuarioSolicitante(Integer idUsuarioSolicitante) {
        this.idUsuarioSolicitante = idUsuarioSolicitante;
    }

    public Integer getIdEspacoFisico() {
        return idEspacoFisico;
    }

    public void setIdEspacoFisico(Integer idEspacoFisico) {
        this.idEspacoFisico = idEspacoFisico;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }
}
