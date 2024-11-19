package logdrive.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import logdrive.model.Vehicle;

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
) {
        // construtor a partir do model Vehicle
        public VehicleRegisterDTO(Vehicle next) {
                this(next.getPlate(),
                        next.getDriver().getId(),
                        next.getChassis(),
                        next.getYear(),
                        next.getColor(),
                        next.getMaker(),
                        next.getModel(),
                        next.getKm());
        }
}
