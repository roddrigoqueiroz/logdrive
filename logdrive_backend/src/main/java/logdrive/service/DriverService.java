package logdrive.service;

import logdrive.dto.SignupDTO;
//import logdrive.repository.DriverRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import logdrive.model.Driver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// classe responsável pela lógica de negócio das ações do relacionadas ao condutor (ex: cadastro, login...)
@Service
public class DriverService {

//    @Autowired
//    DriverRepository driverRepository;
    List<Driver> drivers;

    // salva condutor no banco de dados
    public void saveDriver(SignupDTO signupDTO) {
        // salva senha cifrada com Bcrypt
        Driver driver = new Driver(signupDTO);
        drivers.add(driver);
//        driverRepository.save(condutor); // salva condutor no banco de dados via JPA
    }

    // checa se usuário já está registrado através do email no parâmetro
    public Boolean checkIfExists(String email) {
        for (Driver driver : drivers) {
            if (driver.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
//        return driverRepository.findByEmail(email).isPresent();
    }

    public Driver getDriverByEmail(String email) {
        for (Driver driver : drivers) {
            if (driver.getEmail().equals(email)) {
                return driver;
            }
        }
        return null;
    }

    public boolean authenticate(String email, String password) {

        // verifica se dados de entrada são válidos (não nulos e não vazios)
//        checkInputData(email);
//        checkInputData(password);

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

}
