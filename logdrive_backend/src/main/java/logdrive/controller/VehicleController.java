package logdrive.controller;

import logdrive.dto.ResponseDTO;
import logdrive.dto.VehicleRegisterDTO;
import logdrive.model.Vehicle;
import logdrive.service.VehicleService;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

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

    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<VehicleRegisterDTO>> getAll(@PathParam Long id) {
        try {
            return ResponseEntity.ok(vehicleService.getAllVehicles(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao buscar veículos: " + e.getMessage());
        }
    }

}
