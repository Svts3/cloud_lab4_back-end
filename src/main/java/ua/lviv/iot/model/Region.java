package ua.lviv.iot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Region {
    @Id
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_name", referencedColumnName = "name", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<City> cities;


}
