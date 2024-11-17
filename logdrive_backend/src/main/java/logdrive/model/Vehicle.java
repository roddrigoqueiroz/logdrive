package logdrive.model;

import jakarta.persistence.*;
import logdrive.dto.VehicleRegisterDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String plate;
    private String chassis;
    private int year;
    private String color;
    private String maker;
    private String model;
    private double km;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Travel> travels;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Maintenance> maintenances;

    public Vehicle(VehicleRegisterDTO vehicleRegisterDTO, Driver driver) {
        this.plate = vehicleRegisterDTO.plate();
        this.color = vehicleRegisterDTO.color();
        this.year = vehicleRegisterDTO.year();
        this.maker = vehicleRegisterDTO.maker();
        this.model = vehicleRegisterDTO.model();
        this.driver = driver;
        tickets = new ArrayList<>();
        travels = new ArrayList<>();
        maintenances = new ArrayList<>();
    }

}
