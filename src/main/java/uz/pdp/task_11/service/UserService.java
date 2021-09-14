package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.User;
import uz.pdp.task_11.entity.Warehouse;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.payload.UserDto;
import uz.pdp.task_11.repository.UserRepository;
import uz.pdp.task_11.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addUser(@RequestBody UserDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        List<Warehouse> warehouses = warehouseRepository.findAllById(userDto.getWarehousesId());
        if (warehouses.isEmpty()){
            return new Result("warehouses not found",false);
        }

        if (userDto.getFirstName().isEmpty()){
            return new Result("first name must be",false);
        }
        user.setLastName(userDto.getLastName());
        boolean exists = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists){
            return new Result("phone Number alredy excist",true);
        }
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setWarehouse(warehouses);
        user.setCode(UUID.randomUUID().toString());
        userRepository.save(user);
        return new Result("user added",true);
    }
    public List<User> getUsers(){
        List<User> all = userRepository.findAll();
        return all;
    }
    public User getById(@PathVariable Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        return user;
    }

    public Result deleteUser(@PathVariable Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.deleteById(id);
            return new Result("user deleted",true);
        }
        return new Result("user not found",false);
    }

    public Result editUser(@PathVariable Integer id,@RequestBody UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new Result("user not found",false);
        }
        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        if (userDto.getFirstName().isEmpty()){
            return new Result("first name must be",false);
        }
        List<Warehouse> warehouses = warehouseRepository.findAllById(userDto.getWarehousesId());
        if (warehouses.isEmpty()){
            return new Result("warehouses not found",false);
        }
        user.setLastName(userDto.getLastName());
        boolean exists = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists){
            return new Result("phone Number alredy excist",true);
        }
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCode(UUID.randomUUID().toString());
        user.setWarehouse(warehouses);
        userRepository.save(user);
        return new Result("user adited",true);
    }
}



