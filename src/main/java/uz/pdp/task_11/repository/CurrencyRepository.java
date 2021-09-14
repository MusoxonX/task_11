package uz.pdp.task_11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_11.entity.Currency;
import uz.pdp.task_11.entity.Measurement;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    boolean existsByName(String name);
}
