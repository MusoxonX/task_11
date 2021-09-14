package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_11.entity.Output;
import uz.pdp.task_11.payload.OutputDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @PostMapping()
    public Result addOutput(@RequestBody OutputDto outputDto){
        Result result = outputService.addOutput(outputDto);
        return result;
    }

    @GetMapping()
    public List<Output> getOutput(){
        List<Output> output = outputService.getOutput();
        return output;
    }

    @GetMapping("/{id}")
    public Output getById(@PathVariable Integer id){
        Output output = outputService.getByIdOutput(id);
        return output;
    }

    @PutMapping("/{id}")
    public Result editOutput(@PathVariable Integer id,@RequestBody OutputDto outputDto){
        Result result = outputService.editOutput(id, outputDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id){
        Result result = outputService.deleteOutput(id);
        return result;
    }
}
