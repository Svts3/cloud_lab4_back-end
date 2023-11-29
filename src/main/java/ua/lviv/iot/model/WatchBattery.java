package ua.lviv.iot.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "watch_battery")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WatchBattery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "charge_level")
    private Integer chargeLevel;

    @ManyToOne
    @JoinColumn(name = "watch_serial_number", referencedColumnName = "serialNumber", nullable = false)
    private Watch watch;

}
