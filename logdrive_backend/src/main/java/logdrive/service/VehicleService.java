package logdrive.service;

import jakarta.persistence.EntityNotFoundException;
import logdrive.dto.VehicleRegisterDTO;
import logdrive.model.Driver;
import logdrive.model.Vehicle;
import logdrive.repository.DriverRepository;
import logdrive.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final DriverRepository driverRepository;
    VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, DriverRepository driverRepository) {
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
    }

    public void saveVehicle(VehicleRegisterDTO vehicleRegisterDTO) {
        // busca condutor referente ao veiculo, atraves do id no DTO
        // se não achar, lança exceção correspondente
        Driver driver = driverRepository.findById(vehicleRegisterDTO.driverId())
                .orElseThrow(() -> new EntityNotFoundException("Driver não encontrado"));
        // encontrou, então cria veiculo a partir do DTO
        Vehicle vehicle = new Vehicle(vehicleRegisterDTO, driver);
        vehicleRepository.save(vehicle); // salva veiculo no banco de dados via JPA
    }

    public List<Vehicle> getVehicleListByDriverId(Long driverId) {
        return vehicleRepository.findAllByDriverId(driverId);
    }

    public List<VehicleRegisterDTO> getAllByDriverId(Long driverId) throws IllegalStateException {
        List<Vehicle> vehicles = getVehicleListByDriverId(driverId); // busca veiculos do condutor
        // empacota veiculos encontrados em DTOs
        if (vehicles.isEmpty()) {
            throw new IllegalStateException("Veículos não encontrados");
        }
        return vehicles.stream().map(VehicleRegisterDTO::new).collect(Collectors.toList());
    }

}
