package logdrive.service;

import logdrive.dto.SignupDTO;
import logdrive.model.Driver;
import logdrive.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// classe responsável pela lógica de negócio das ações do relacionadas ao condutor (ex: cadastro, login...)
@Service
public class DriverService {

    DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    // salva condutor no banco de dados
    public void saveDriver(SignupDTO signupDTO) {
        // salva senha cifrada com Bcrypt
        Driver driver = new Driver(signupDTO);
        driverRepository.save(driver); // salva condutor no banco de dados via JPA
    }

    // checa se usuário já está registrado através do email no parâmetro
    public Boolean checkIfExists(String email) {
        return driverRepository.findByEmail(email).isPresent();
    }

    public Driver getDriverByEmail(String email) {
        if (driverRepository.findByEmail(email).isPresent()) {
            return driverRepository.findByEmail(email).get();
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
