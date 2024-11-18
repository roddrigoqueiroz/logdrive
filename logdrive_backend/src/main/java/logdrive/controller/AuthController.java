package logdrive.controller;

import jakarta.validation.Valid;
import logdrive.dto.ResponseDTO;
import logdrive.dto.SignupDTO;
import logdrive.dto.LoginDTO;
import logdrive.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    DriverService driverService;

    // atende requisição POST, cadastrando usuário
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<?>> signup(@Valid @RequestBody SignupDTO signupDTO) {
        try {
            // Checa se já há conta registrada com o email recebido
            if (driverService.checkIfExists(signupDTO.email())) {
                throw new IllegalArgumentException("Usuário já registrado");
            }
            // Salva o novo condutor diretamente a partir do DTO recebido
            driverService.saveDriver(signupDTO);
            return ResponseEntity.ok(new ResponseDTO<>(true, "Usuário cadastrado com sucesso", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO<>(false, e.getMessage(), null));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<?>> login(@Valid @RequestBody LoginDTO loginRequest) {
        try {
            Long userId = driverService.authenticate(loginRequest.email(), loginRequest.password());
            if (userId != null) {
                return ResponseEntity.ok(new ResponseDTO<>(true, "Login efetuado com sucesso", userId));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(false, "Credenciais Inválidas", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO<>(false, e.getMessage(), null));
        }
    }

}