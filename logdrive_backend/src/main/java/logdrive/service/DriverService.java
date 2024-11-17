package logdrive.service;

import logdrive.dto.SignupDTO;
import logdrive.repository.DriverRepository;
import logdrive.model.Driver;
import logdrive.model.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// classe responsável pela lógica de negócio das ações do relacionadas ao condutor (ex: cadastro, login...)
@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    // salva condutor no banco de dados
    public void saveDriver(SignupDTO signupDTO) {
        Driver driver = new Driver(signupDTO);
        driverRepository.save(driver); // salva condutor no banco de dados via JPA
    }

    // checa se usuário já está registrado através do email no parâmetro
    public Boolean checkIfExists(String email) {
        return driverRepository.findByEmail(email).isPresent();
    }


    public boolean authenticate(String email, String password) {
        // busca condutor atraves do email
        Driver driver = getDriverByEmail(email);

        if (driver == null) {
            // se nao encontrar lanca excecao
            throw new IllegalArgumentException("Email não encontrado na base de dados");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, driver.getPassword());
    }

    public void checkInputData(String input) {
        // Validação de dados de entrada
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Dados inválidos ou vazios");
        }
    }

    public Driver getDriverByEmail(String email) {
        return driverRepository.findByEmail(email).orElse(null);
    }

    public void addVehicleToDriver(String email, Vehicle vehicle) {
        Driver driver = getDriverByEmail(email);
        if (driver != null) {
            vehicle.setDriver(driver);
            driver.getVehicles().add(vehicle);
            driverRepository.save(driver);
        } else {
            throw new IllegalArgumentException("Driver not found");
        }
    }

}
