package logdrive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import logdrive.dto.ResponseDTO;
import logdrive.model.Driver;
import logdrive.service.DriverService;

@RestController
@RequestMapping("driver")
public class DriverController {
    DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseDTO<Driver>> getDriverById(@PathVariable Long id) {
        try {
            Driver driver = driverService.getDriverById(id);
            if (driver == null) {
                throw new IllegalStateException("Condutor não encontrado");
            }
            return ResponseEntity.ok(new ResponseDTO<>(true, "Condutor encontrado", driver));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO<>(false, e.getMessage(), null));
        }
    }
}
