package Siemens.Internship.JavaTrainApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bilet")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BiletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBilet;

    private LocalDateTime dataCalatoriei;
    private int vagon;
    private int loc;
    private String emailClient;

    @ManyToOne
    @JoinColumn(name = "numar_tren")
    private TrenEntity tren;

    @ManyToOne
    @JoinColumn(name = "id_gara_plecare")
    private GaraEntity garaPlecare;

    @ManyToOne
    @JoinColumn(name = "id_gara_destinatie")
    private GaraEntity garaDestinatie;
}