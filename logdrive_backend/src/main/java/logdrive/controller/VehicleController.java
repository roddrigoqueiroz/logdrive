package logdrive.controller;

import logdrive.dto.ResponseDTO;
import logdrive.dto.VehicleRegisterDTO;
import logdrive.model.Vehicle;
import logdrive.service.VehicleService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    // através do id na url, busca veículos do condutor
    @GetMapping("/getAll/{id}")
    public ResponseEntity<ResponseDTO<?>> getAll(@PathVariable Long id) {
        try {
            // realiza busca através de consulta no banco, se não achar correspondência lança exceção
            List<VehicleRegisterDTO> vehiclesDTO = vehicleService.getAllByDriverId(id);
            return ResponseEntity.ok(new ResponseDTO<>(true, "Veículos encontrados", vehiclesDTO));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO<>(false, "Erro ao buscar veículos: " + e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO<>(false, "Erro ao buscar veículos: " + e.getMessage(), null));
        }
    }

}
