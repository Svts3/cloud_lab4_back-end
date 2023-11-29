package ua.lviv.iot.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "emergency_phone_number")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmergencyPhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "watch_serial_number", referencedColumnName = "serialNumber", nullable = false)
    private Watch watch;
}
