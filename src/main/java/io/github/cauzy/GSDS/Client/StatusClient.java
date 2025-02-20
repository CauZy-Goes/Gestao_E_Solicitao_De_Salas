package io.github.cauzy.GSDS.Client;

import io.github.cauzy.GSDS.DTO.StatusDTO;
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
public class StatusClient {

    @Inject
    private Client client;

    private static final String API_URL = "http://localhost:8081/api/status";

    public List<StatusDTO> listarStatus() throws EntityNotFoundException {
        WebTarget target = client.target(API_URL);
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<StatusDTO>>() {});
        } else {
            throw new EntityNotFoundException("Erro ao listar Status: " + response.getStatus());
        }
    }

    public StatusDTO getStatusById(Integer id) throws EntityNotFoundException {
        WebTarget target = client.target(API_URL + "/" + id);
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(StatusDTO.class);
        } else {
            throw new EntityNotFoundException("Erro ao encontrar Status pelo ID: " + response.getStatus());
        }
    }

    public StatusDTO createStatus(StatusDTO statusDTO) throws EntityCreationException {
        WebTarget target = client.target(API_URL);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(statusDTO, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(StatusDTO.class);
        } else {
            throw new EntityCreationException("Erro ao criar Status: " + response.getStatus());
        }
    }
}
