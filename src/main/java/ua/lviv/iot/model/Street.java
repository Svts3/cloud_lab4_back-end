package ua.lviv.iot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Street {
    @Id
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_name", referencedColumnName = "name", nullable = false)
    private City city;

    @OneToOne(mappedBy = "street")
    private Watch watch;
}
