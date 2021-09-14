package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_11.entity.User;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public Result addUser(@RequestBody User userDto){
        Result result = userService.addUser(userDto);
        return result;
    }
    @GetMapping()
    public List<User> getUsers(){
        List<User> users = userService.getUsers();
        return users;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        User user = userService.getById(id);
        return user;
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        Result result = userService.deleteUser(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result editUser(@PathVariable Integer id,@RequestBody User userDto){
        Result result = userService.editUser(id, userDto);
        return result;
    }
}



