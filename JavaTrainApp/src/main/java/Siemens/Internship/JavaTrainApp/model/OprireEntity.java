package Siemens.Internship.JavaTrainApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "oprire")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OprireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOprire;

    private LocalTime oraSosire;
    private LocalTime oraPlecare;

    @ManyToOne
    @JoinColumn(name = "numar_tren")
    private TrenEntity tren;

    @ManyToOne
    @JoinColumn(name = "id_gara")
    private GaraEntity gara;
}