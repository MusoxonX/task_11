package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_11.entity.OutputProduct;
import uz.pdp.task_11.payload.OutputProductDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @PostMapping()
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        Result result = outputProductService.addOutputProduct(outputProductDto);
        return result;
    }
    @GetMapping()
    public List<OutputProduct> getOutputProduct(){
        List<OutputProduct> outputProduct = outputProductService.getOutputProduct();
        return outputProduct;
    }
    @GetMapping("/{id}")
    public OutputProduct getById(@PathVariable Integer id){
        OutputProduct outputProduct = outputProductService.getById(id);
        return outputProduct;
    }
    @DeleteMapping("/{id}")
    public Result deleteOutputProduct(@PathVariable Integer id){
        Result result = outputProductService.deleteOutputProduct(id);
        return result;
    }
    @PutMapping("/{id}")
    public Result editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        Result result = outputProductService.editOutputProduct(id, outputProductDto);
        return result;
    }
}
