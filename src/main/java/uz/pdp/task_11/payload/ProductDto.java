package uz.pdp.task_11.payload;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ProductDto {
    private String name;
    private Integer category_id;
    private Integer photoId;
    private Integer measurementId;
}
