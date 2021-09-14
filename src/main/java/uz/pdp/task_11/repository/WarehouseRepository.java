package uz.pdp.task_11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_11.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    boolean existsByName(String name);
}
