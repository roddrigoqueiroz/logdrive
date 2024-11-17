package logdrive.controller;

import logdrive.dto.VehicleRegisterDTO;
import logdrive.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vehicle")
public class VehicleController {

    VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody VehicleRegisterDTO vehicleRegisterDTO) {
        try {
            vehicleService.saveVehicle(vehicleRegisterDTO);
            return ResponseEntity.ok("Veículo cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

}
