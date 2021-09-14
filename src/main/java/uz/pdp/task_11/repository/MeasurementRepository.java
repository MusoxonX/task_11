package uz.pdp.task_11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_11.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    boolean existsByName(String name);
}
