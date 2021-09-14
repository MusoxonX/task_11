package uz.pdp.task_11.payload;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class OutputDto {
    private Date date;
    private Integer warehouseId;
    private Integer clientId;
    private Integer currencyId;
}
