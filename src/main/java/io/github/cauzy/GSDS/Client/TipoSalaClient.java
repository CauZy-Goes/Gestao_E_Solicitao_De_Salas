package io.github.cauzy.GSDS.Client;

import io.github.cauzy.GSDS.DTO.TipoSalaDTO;
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
public class TipoSalaClient {

    @Inject
    private Client client;

    private static final String API_URL = "http://localhost:8081/api/tipoSalas";

    public List<TipoSalaDTO> listarTipoSalas() throws EntityNotFoundException {
        WebTarget target = client.target(API_URL);
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<TipoSalaDTO>>() {});
        } else {
            throw new EntityNotFoundException("Erro ao listar Tipo de Sala: " + response.getStatus());
        }
    }

    public TipoSalaDTO getTipoSalaById(Integer id) throws EntityNotFoundException {
        WebTarget target = client.target(API_URL + "/" + id);
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(TipoSalaDTO.class);
        } else {
            throw new EntityNotFoundException("Erro ao encontrar Tipo de Sala pelo ID: " + response.getStatus());
        }
    }

    public TipoSalaDTO createTipoSala(TipoSalaDTO tipoSalaDTO) throws EntityCreationException {
        WebTarget target = client.target(API_URL);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(tipoSalaDTO, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(TipoSalaDTO.class);
        } else {
            throw new EntityCreationException("Erro ao criar Tipo de Sala: " + response.getStatus());
        }
    }

    public TipoSalaDTO updateTipoSala(TipoSalaDTO tipoSalaDTO) throws EntityCreationException {
        WebTarget target = client.target(API_URL + "/" + tipoSalaDTO.getIdTipoSala());

        Response response = target.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(tipoSalaDTO, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(TipoSalaDTO.class);
        } else {
            throw new EntityCreationException("Erro ao atualizar Tipo de Sala: " + response.getStatus());
        }
    }

    public void deleteTipoSala(Integer id) throws EntityNotFoundException {
        WebTarget target = client.target(API_URL + "/" + id);

        Response response = target.request(MediaType.APPLICATION_JSON).delete();

        if (response.getStatus() != 204) { // 204 significa No Content, esperado ao deletar
            throw new EntityNotFoundException("Erro ao deletar Tipo de Sala: " + response.getStatus());
        }
    }

}
