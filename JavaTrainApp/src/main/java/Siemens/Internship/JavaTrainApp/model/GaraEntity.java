package Siemens.Internship.JavaTrainApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gara")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GaraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGara;
    private String numeGara;
}