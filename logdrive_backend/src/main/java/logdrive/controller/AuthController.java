package logdrive.controller;

import jakarta.validation.Valid;
import logdrive.dto.SignupDTO;
import logdrive.model.Vehicle;
import logdrive.dto.LoginDTO;
import logdrive.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    DriverService driverService;

    // atende requisição POST, cadastrando usuário
    @PostMapping("/register")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupDTO signupDTO) {
        try {
            // Checa se já há conta registrada com o email recebido
            if (driverService.checkIfExists(signupDTO.email())) {
                throw new IllegalArgumentException("Usuário já registrado");
            }
            // Salva o novo condutor diretamente a partir do DTO recebido
            driverService.saveDriver(signupDTO);
            return ResponseEntity.ok("Usuário cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginRequest) {
        try {
            if (driverService.authenticate(loginRequest.email(), loginRequest.password())) {
                return ResponseEntity.ok("Login bem-sucedido");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais Inválidas");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao login: " + e.getMessage());
        }
    }

    @PostMapping("/driver/{email}/vehicle")
    public ResponseEntity<String> addVehicleToDriver(@PathVariable String email, @Valid @RequestBody Vehicle vehicleDTO) {
        try {
            driverService.addVehicleToDriver(email, vehicleDTO);
            return ResponseEntity.ok("Veículo adicionado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao adicionar veículo: " + e.getMessage());
        }
    }

}
