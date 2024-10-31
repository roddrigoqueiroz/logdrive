package logdrive.controller;

import logdrive.model.Driver;
import logdrive.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @Autowired
    DriverService driverService;

    // atende requisição POST, cadastrando usuário
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Driver registerRequest) {
        try {
            // checa se já há conta registrada com o email recebido
            if(driverService.checkIfExists(registerRequest.getEmail())) throw new IllegalArgumentException("Usuário já registrado");
            Driver newCondutor = new Driver();
            newCondutor.setEmail(registerRequest.getEmail());
            newCondutor.setNome(registerRequest.getNome());
            newCondutor.setCpf(registerRequest.getCpf());
            newCondutor.setPassword(registerRequest.getPassword());
            driverService.saveDriver(newCondutor); // salva o condutor
            return ResponseEntity.ok("Usuário cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Driver loginRequest) {
        try {
            if (driverService.authenticate(loginRequest.getEmail(), loginRequest.getPassword())) {
                return ResponseEntity.ok("Login bem-sucedido");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro no login");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao login: " + e.getMessage());
        }
    }

}
