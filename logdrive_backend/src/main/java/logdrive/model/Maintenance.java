package logdrive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double oilKm; // oleoKm
    private double tireKm; // pneuKm
    private double price;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @Temporal(TemporalType.DATE)
    private Date fuelDate;
    @Temporal(TemporalType.DATE)
    private Date calibrationDate;
    @Temporal(TemporalType.DATE)
    private Date batteryDate;
    @Temporal(TemporalType.DATE)
    private Date filterDate;
    @Temporal(TemporalType.DATE)
    private Date brakeDate;

}
