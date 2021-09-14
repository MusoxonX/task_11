package uz.pdp.task_11.payload;

import lombok.Data;

@Data
public class OutputProductDto {
    private double amount;
    private double price;
    private Integer productId;
    private Integer outputId;
}
