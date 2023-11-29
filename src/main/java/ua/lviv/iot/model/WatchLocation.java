package ua.lviv.iot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "watch_location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WatchLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "watch_serial_number", referencedColumnName = "serialNumber", nullable = false)
    private Watch watch;
}
