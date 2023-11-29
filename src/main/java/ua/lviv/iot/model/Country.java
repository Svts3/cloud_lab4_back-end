package ua.lviv.iot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "country")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Country {
    @Id
    private String name;

    private String continent_name;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Region> regions;
}
