package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_11.entity.InputProduct;
import uz.pdp.task_11.payload.InputProductDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputproduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;
    @PostMapping()
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto){
        Result result = inputProductService.addInputP(inputProductDto);
        return result;
    }

    @GetMapping()
    public List<InputProduct> getInputProduct(){
        List<InputProduct> inputProduct = inputProductService.getInputProduct();
        return inputProduct;
    }

    @GetMapping("/{id}")
    public InputProduct getByIdInputProduct(@PathVariable Integer id){
        InputProduct service = inputProductService.getByIdInputProduct(id);
        return service;
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id){
        Result result = inputProductService.deleteInputProduct(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result editInputProduct(@PathVariable Integer id,@RequestBody InputProductDto inputProductDto){
        Result result = inputProductService.editInputProduct(id, inputProductDto);
        return result;
    }
}
