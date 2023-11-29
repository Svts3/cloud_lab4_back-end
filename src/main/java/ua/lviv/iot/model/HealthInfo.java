package ua.lviv.iot.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "health_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HealthInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "heartbeat_rate")
    private Integer heartbeatRate;
    @ManyToOne
    @JoinColumn(name = "watch_serial_number", referencedColumnName = "serialNumber",
            nullable = false)
    private Watch watch;

}
