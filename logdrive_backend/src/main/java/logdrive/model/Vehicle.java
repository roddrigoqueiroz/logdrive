package logdrive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
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

    public Vehicle(String plate, String chassis, int year, String color, String maker, String model, double km, Driver driver) {
        this.plate = plate;
        this.chassis = chassis;
        this.year = year;
        this.color = color;
        this.maker = maker;
        this.model = model;
        this.km = km;
        this.driver = driver;
    }

    public String plate() {
        return plate;
    }

    public String chassis() {
        return chassis;
    }

    public int year() {
        return year;
    }

    public String color() {
        return color;
    }

    public String maker() {
        return maker;
    }

    public String model() {
        return model;
    }

    public double km() {
        return km;
    }

    public Driver driver() {
        return driver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

}
