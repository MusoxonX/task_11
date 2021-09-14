package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_11.entity.Supplier;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping()
    public Result addSupplier(@RequestBody Supplier supplierDto){
        Result result = supplierService.addSupplier(supplierDto);
        return result;
    }

    @GetMapping()
    public List<Supplier> getSupplier(){
        List<Supplier> supplier = supplierService.getSupplier();
        return supplier;
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Integer id){
        Supplier supplier = supplierService.getById(id);
        return supplier;
    }

    @PutMapping("/{id}")
    public Result updateSupplier(@PathVariable Integer id, @RequestBody Supplier supplierDto){
        Result result = supplierService.editSupplier(id, supplierDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id){
        Result result = supplierService.deleteSupplier(id);
        return result;
    }
}
