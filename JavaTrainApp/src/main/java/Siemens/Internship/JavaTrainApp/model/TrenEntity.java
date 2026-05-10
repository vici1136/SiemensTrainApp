package Siemens.Internship.JavaTrainApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tren")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TrenEntity {
    @Id
    private int numarTren;
    private int capacitate;

    @ManyToOne
    @JoinColumn(name = "id_gara_plecare")
    private GaraEntity garaPlecare;

    @ManyToOne
    @JoinColumn(name = "id_gara_sosire")
    private GaraEntity garaSosire;
}