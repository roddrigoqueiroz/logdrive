package logdrive.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleRegisterDTO (
        @NotBlank(message = "Campo obrigatório")
        String plate,
        @NotNull
        Long driverId,
        String chassis,
        int year,
        String color,
        String maker,
        String model,
        double km
) {}
