package ua.lviv.iot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Watch {
    @Id
    private String serialNumber;

    @OneToOne
    @JoinColumn(name = "street_name", referencedColumnName = "name", nullable = false)
    private Street street;

    @OneToMany(mappedBy = "watch")
    private List<HealthInfo> healthInfos;

    @OneToMany(mappedBy = "watch")
    private List<WatchLocation> watchLocations;

    @OneToMany(mappedBy = "watch")
    private List<WatchBattery> watchBatteries;

    @OneToMany(mappedBy = "watch")
    private List<EmergencyPhoneNumber> emergencyPhoneNumbers;

    @OneToMany(mappedBy = "watch")
    public Set<PropertyInfo> propertyInfos;

}
