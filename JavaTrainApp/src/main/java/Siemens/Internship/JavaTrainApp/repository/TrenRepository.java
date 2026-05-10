package Siemens.Internship.JavaTrainApp.repository;

import Siemens.Internship.JavaTrainApp.model.TrenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrenRepository extends JpaRepository<TrenEntity, Integer> {}