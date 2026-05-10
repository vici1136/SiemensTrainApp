package Siemens.Internship.JavaTrainApp.repository;

import Siemens.Internship.JavaTrainApp.model.OprireEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OprireRepository extends JpaRepository<OprireEntity, Integer> {
    List<OprireEntity> findByTrenNumarTrenOrderByOraSosireAsc(int numarTren);
    List<OprireEntity> findByGaraIdGara(int idGara);
}