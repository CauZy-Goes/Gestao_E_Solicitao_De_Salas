package io.github.cauzy.GSDS.Client;

import io.github.cauzy.GSDS.DTO.CargoDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.GenericType;

import java.util.List;

@ApplicationScoped
public class CargoClient {

    @Inject
    private Client client; // Client injetado pelo CDI

    private static final String API_URL = "http://localhost:8081/api/cargos";

    public List<CargoDTO> listarCargos() throws EntityNotFoundException {
        WebTarget target = client.target(API_URL);

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<CargoDTO>>() {});
        } else {
            throw new EntityNotFoundException("Erro ao listar cargos: " + response.getStatus());
        }
    }

    public CargoDTO getCargoById(Integer id) throws EntityNotFoundException {
        WebTarget target = client.target(API_URL + "/" + id);

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(CargoDTO.class);
        } else {
            throw new EntityNotFoundException("Erro ao encontrar cargo pelo ID: " + response.getStatus());
        }
    }

    public CargoDTO createCargo(CargoDTO cargoDTO) throws EntityCreationException {
        WebTarget target = client.target(API_URL);

        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(cargoDTO, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(CargoDTO.class);
        } else {
            throw new EntityCreationException("Erro ao criar cargo: " + response.getStatus());
        }
    }
}
