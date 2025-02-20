package io.github.cauzy.GSDS.Client;

import io.github.cauzy.GSDS.DTO.SolicitacaoDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class SolicitacaoClient {

    @Inject
    private Client client;

    private static final String API_URL = "http://localhost:8081/api/solicitacoes";

    public List<SolicitacaoDTO> listarSolicitacoes() throws EntityNotFoundException {
        WebTarget target = client.target(API_URL);
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<SolicitacaoDTO>>() {});
        } else {
            throw new EntityNotFoundException("Erro ao listar Solicitações: " + response.getStatus());
        }
    }

    public SolicitacaoDTO getSolicitacaoById(Integer id) throws EntityNotFoundException {
        WebTarget target = client.target(API_URL + "/" + id);
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(SolicitacaoDTO.class);
        } else {
            throw new EntityNotFoundException("Erro ao encontrar Solicitação pelo ID: " + response.getStatus());
        }
    }
    public SolicitacaoDTO getSolicitacaoByIdSolicitante(Integer idSolicitante) throws EntityNotFoundException {
        WebTarget target = client.target(API_URL + "/solicitante/" + idSolicitante);
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(SolicitacaoDTO.class);
        } else {
            throw new EntityNotFoundException("Erro ao encontrar Solicitação pelo ID do solicitante: " + response.getStatus());
        }
    }

    public SolicitacaoDTO createSolicitacao(SolicitacaoDTO solicitacaoDTO) throws EntityCreationException {
        WebTarget target = client.target(API_URL);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(solicitacaoDTO, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(SolicitacaoDTO.class);
        } else {
            throw new EntityCreationException("Erro ao criar Solicitação: " + response.getStatus());
        }
    }
}
