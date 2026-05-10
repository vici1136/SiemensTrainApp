package Siemens.Internship.JavaTrainApp.repository;

import Siemens.Internship.JavaTrainApp.model.BiletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface BiletRepository extends JpaRepository<BiletEntity, Integer> {
    List<BiletEntity> findByTrenNumarTren(int numarTren);
    int countByTrenNumarTrenAndDataCalatorieiBetween(int numarTren, LocalDateTime start, LocalDateTime end);
}