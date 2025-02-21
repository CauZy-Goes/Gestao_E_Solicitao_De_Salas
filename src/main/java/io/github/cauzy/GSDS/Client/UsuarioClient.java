package io.github.cauzy.GSDS.Client;

import io.github.cauzy.GSDS.DTO.UsuarioDTO;
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
public class UsuarioClient {

    @Inject
    private Client client; // Client injetado pelo CDI

    private static final String API_URL = "http://localhost:8081/api/usuarios";

    public List<UsuarioDTO> listarUsuarios() throws EntityNotFoundException {
        WebTarget target = client.target(API_URL);

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<UsuarioDTO>>() {});
        } else {
            throw new EntityNotFoundException("Erro ao listar usuários: " + response.getStatus());
        }
    }

    public UsuarioDTO getUsuarioById(Integer id) throws EntityNotFoundException {
        WebTarget target = client.target(API_URL + "/" + id);

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(UsuarioDTO.class);
        } else {
            throw new EntityNotFoundException("Erro ao encontrar usuário pelo id: " + response.getStatus());
        }
    }

    public UsuarioDTO getUsuarioByEmail(String email) throws EntityNotFoundException {
        WebTarget target = client.target(API_URL + "/email/" + email);

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(UsuarioDTO.class);
        } else {
            throw new EntityNotFoundException("Erro ao encontrar usuário pelo email: " + response.getStatus());
        }
    }

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) throws EntityCreationException {
        WebTarget target = client.target(API_URL);

        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(usuarioDTO, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(UsuarioDTO.class);
        } else {
            throw new EntityCreationException("Erro ao criar usuário: " + response.getStatus());
        }
    }

    public UsuarioDTO updateUsuario(Integer id, UsuarioDTO usuarioDTO) throws EntityCreationException {
        WebTarget target = client.target(API_URL + "/" + id);

        Response response = target.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(usuarioDTO, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(UsuarioDTO.class);
        } else {
            throw new EntityCreationException("Erro ao fazer update do usuario usuário: " + response.getStatus());
        }
    }

}
