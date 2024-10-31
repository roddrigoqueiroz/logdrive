package logdrive.service;

import logdrive.model.Driver;
//import logdrive.repository.DriverRepository;
//import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveDriver(Driver condutor) {
        // salva senha cifrada com Bcrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        condutor.setPassword(encoder.encode(condutor.getPassword()));
        drivers.add(condutor);
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

}
