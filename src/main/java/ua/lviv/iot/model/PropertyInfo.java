package ua.lviv.iot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "property_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PropertyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "watch_serial_number", referencedColumnName = "serialNumber", nullable = false)
    private Watch watch;

    @OneToMany(mappedBy = "propertyInfo")
    private List<User> users;

}
