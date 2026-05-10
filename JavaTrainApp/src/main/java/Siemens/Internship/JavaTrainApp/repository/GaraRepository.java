package Siemens.Internship.JavaTrainApp.repository;

import Siemens.Internship.JavaTrainApp.model.GaraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GaraRepository extends JpaRepository<GaraEntity, Integer> {
    GaraEntity findByNumeGara(String numeGara);
}