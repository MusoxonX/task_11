package uz.pdp.task_11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_11.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
