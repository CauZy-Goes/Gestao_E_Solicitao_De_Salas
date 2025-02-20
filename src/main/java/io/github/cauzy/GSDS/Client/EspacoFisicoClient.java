package io.github.cauzy.GSDS.Client;

import io.github.cauzy.GSDS.DTO.CargoDTO;
import io.github.cauzy.GSDS.DTO.EspacoFisicoDTO;
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
public class EspacoFisicoClient {

    @Inject
    private Client client; // Client injetado pelo CDI

    private static final String API_URL = "http://localhost:8081/api/espacoFisicos";

    public List<EspacoFisicoDTO> listarEspacosFisicos() throws EntityNotFoundException {
        WebTarget target = client.target(API_URL);

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<EspacoFisicoDTO>>() {});
        } else {
            throw new EntityNotFoundException("Erro ao listar Espacos FIsicos: " + response.getStatus());
        }
    }

    public EspacoFisicoDTO getEspacoFisicoById(Integer id) throws EntityNotFoundException {
        WebTarget target = client.target(API_URL + "/" + id);

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(EspacoFisicoDTO.class);
        } else {
            throw new EntityNotFoundException("Erro ao encontrar Espaco Fisico pelo ID: " + response.getStatus());
        }
    }

    public EspacoFisicoDTO createEspacoFisico(EspacoFisicoDTO espacoFisicoDTO) throws EntityCreationException {
        WebTarget target = client.target(API_URL);

        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(espacoFisicoDTO, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(EspacoFisicoDTO.class);
        } else {
            throw new EntityCreationException("Erro ao criar Espaco FIsico:  " + response.getStatus());
        }
    }
}
