package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_11.entity.Input;
import uz.pdp.task_11.payload.InputDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping()
    public Result addInput(@RequestBody InputDto inputDto){
        Result result = inputService.addInput(inputDto);
        return result;
    }

    @GetMapping()
    public List<Input> getInput(){
        List<Input> input = inputService.getInput();
        return input;
    }

    @GetMapping("/{id}")
    public Input getById(@PathVariable Integer id){
        Input input = inputService.getByIdInput(id);
        return input;
    }

    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id,@RequestBody InputDto inputDto){
        Result result = inputService.editInput(id, inputDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id){
        Result result = inputService.deleteInput(id);
        return result;
    }
}
