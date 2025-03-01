package io.github.cauzy.GSDS.DTO;

import java.time.LocalDateTime;

public class LogAcoesDTO {

    private Integer idLogAcoes;
    private LocalDateTime dataHora;
    private Integer idUsuario;
    private String acao;

    public LogAcoesDTO() {

    }

    public LogAcoesDTO(LocalDateTime dataHora, Integer idUsuario, String acao) {
        this.dataHora = dataHora;
        this.idUsuario = idUsuario;
        this.acao = acao;
    }

    public Integer getIdLogAcoes() {
        return idLogAcoes;
    }

    public void setIdLogAcoes(Integer idLogAcoes) {
        this.idLogAcoes = idLogAcoes;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    @Override
    public String toString() {
        return "LogAcoesDTO [idLogAcoes=" + idLogAcoes + ", dataHora=" + dataHora + ", idUsuario=" + idUsuario + ", acao=" + acao + "]";
    }

}
