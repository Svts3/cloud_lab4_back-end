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
public class City {
    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_name", referencedColumnName = "name", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "city")
    private List<Street> streets;


}
