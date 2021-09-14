package uz.pdp.task_11.payload;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Integer> warehousesId;
}
