package uz.pdp.task_11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.task_11.entity.template.AbsEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends AbsEntity {
}
