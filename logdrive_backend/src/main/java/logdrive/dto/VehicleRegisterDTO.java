package logdrive.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
