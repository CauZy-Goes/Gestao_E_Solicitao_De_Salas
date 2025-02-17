package io.github.cauzy.GSDS.Controller;

import io.github.cauzy.GSDS.Client.CargoClient;
import io.github.cauzy.GSDS.DTO.CargoDTO;
import io.github.cauzy.GSDS.Utility.Exception.EntityCreationException;
import io.github.cauzy.GSDS.Utility.Exception.EntityNotFoundException;
import io.github.cauzy.GSDS.Utility.Message;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CargoMB implements Serializable {

    @Inject
    private CargoClient cargoClient;

    private CargoDTO cargoDTO = new CargoDTO();

    private List<CargoDTO> cargosList;

    @PostConstruct
    public void init()  {
        try {
            cargosList = cargoClient.listarCargos();
        } catch (EntityNotFoundException e) {
            Message.erro("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    public void adicionar() {
        try {
            cargoClient.createCargo(cargoDTO);
            init();
            cargoDTO = new CargoDTO();
            Message.info("Salvo com sucesso");
        } catch (EntityCreationException e) {
            Message.erro(e.getMessage());
        }
    }

    public List<CargoDTO> getCargosList() {
        return cargosList;
    }

    public CargoDTO getCargoDTO() {
        return cargoDTO;
    }

    public void setCargoDTO(CargoDTO cargoDTO) {
        this.cargoDTO = cargoDTO;
    }
}
