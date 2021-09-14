package uz.pdp.task_11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp inputdate;

    @ManyToOne
    private Warehouse wareHouse;

    @ManyToOne
    private Supplier suplier;

    @OneToOne
    private Currency currency;

    private String code;
}
