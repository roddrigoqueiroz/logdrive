package logdrive.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import logdrive.model.Driver;

public record VehicleRegisterDTO (
        @NotBlank(message = "Campo obrigatório")
        String plate,
        @NotNull
        Long driverId,
//        private String chassis;
        int year,
        String color,
//        private String maker;
        String model,
        String maker
//        private double km;
) {}
