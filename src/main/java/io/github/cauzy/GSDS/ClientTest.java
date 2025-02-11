package io.github.cauzy.GSDS;

import io.github.cauzy.GSDS.Client.UsuarioClient;
import io.github.cauzy.GSDS.Client.CargoClient;
import io.github.cauzy.GSDS.DTO.UsuarioDTO;
import io.github.cauzy.GSDS.DTO.CargoDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;

import java.util.List;

public class ClientTest {
    public static void main(String[] args) {
        UsuarioClient usuarioClient = new UsuarioClient();
        CargoClient cargoClient = new CargoClient();

        // Testando listar usuários
        try {
            List<UsuarioDTO> usuarios = usuarioClient.listarUsuarios();
            System.out.println("Usuários encontrados: " + usuarios.size());
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        }

//        // Testando criar usuário
//        try {
//            UsuarioDTO novoUsuario = new UsuarioDTO();
//            novoUsuario.setNomeUsuario("Teste");
//            novoUsuario.setEmail("teste@email.com");
//            UsuarioDTO usuarioCriado = usuarioClient.createUsuario(novoUsuario);
//            System.out.println("Usuário criado com ID: " + usuarioCriado.getIdUsuario());
//        } catch (EntityCreationException e) {
//            System.err.println(e.getMessage());
//        }
//
//         Testando buscar usuário por ID
        try {
            UsuarioDTO usuario = usuarioClient.getUsuarioById(1);
            System.out.println("Usuário encontrado: " + usuario.getNomeUsuario());
            System.out.println(usuario);
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        }
//
//        // Testando listar cargos
//        try {
//            List<CargoDTO> cargos = cargoClient.listarCargos();
//            System.out.println("Cargos encontrados: " + cargos.size());
//        } catch (EntityNotFoundException e) {
//            System.err.println(e.getMessage());
//        }
//
//        // Testando criar cargo
//        try {
//            CargoDTO novoCargo = new CargoDTO();
//            novoCargo.setNomeCargo("Gerente");
//            CargoDTO cargoCriado = cargoClient.createCargo(novoCargo);
//            System.out.println("Cargo criado com ID: " + cargoCriado.getIdCargo());
//        } catch (EntityCreationException e) {
//            System.err.println(e.getMessage());
//        }
//
//        // Testando buscar cargo por ID
//        try {
//            CargoDTO cargo = cargoClient.getCargoById(1);
//            System.out.println("Cargo encontrado: " + cargo.getNomeCargo());
//        } catch (EntityNotFoundException e) {
//            System.err.println(e.getMessage());
//        }
    }
}

