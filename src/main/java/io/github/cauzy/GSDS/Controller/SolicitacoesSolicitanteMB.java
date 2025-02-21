package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.SolicitacaoClient;
import io.github.cauzy.GSDS.DTO.SolicitacaoDTO;
import io.github.cauzy.GSDS.DTO.UsuarioDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import io.github.cauzy.GSDS.Utility.Message;
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

@Named
@ViewScoped
public class SolicitacoesSolicitanteMB implements Serializable {

    @Inject
    private SolicitacaoClient solicitacaoClient;

    private SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();

    private List<SolicitacaoDTO> solicitacaoDTOList;

//    List<SolicitacaoDTO> solicitacoesDoSolicitanteDTOList;

    @PostConstruct
    public void init()  {
        try {
            Integer idProfessor = getSolicitanteId();

            solicitacaoDTOList = solicitacaoClient.getSolicitacaoByIdSolicitante(idProfessor);
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar solicitacao: " + e.getMessage());
        }
    }

    public void adicionar() {
        try {
//            Preenchimento Default
            solicitacaoDTO.setDataHoraSolicitacao(LocalDateTime.now());
            solicitacaoDTO.setDataHoraLocacao(LocalDateTime.now());
            Integer idProfessor = getSolicitanteId();
            solicitacaoDTO.setIdUsuarioSolicitante(idProfessor);

//            Salva no banco de dados
            solicitacaoClient.createSolicitacao(solicitacaoDTO);
            init();
            solicitacaoDTO = new SolicitacaoDTO();
            Message.info("Salvo com sucesso");
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    public Integer getSolicitanteId(){
        UsuarioDTO professor = (UsuarioDTO) getSession().getAttribute("professor");
        return  professor.getIdUsuario();
    }

    public HttpSession getSession(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        return  (HttpSession) externalContext.getSession(false);
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

    public String dataHoraFormatada(LocalDateTime dataHora){
        if (dataHora == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm  dd/MM/yyyy");
        return dataHora.format(formatter);
    }
}
