package uz.pdp.task_11.payload;

import lombok.Data;

import java.util.Date;

@Data
public class InputProductDto {
    private Integer productId;
    private Integer inputId;
    private double amount;
    private double price;
    private Date expireDate;
}
