package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_11.entity.Warehouse;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping()
    public Result addWarehouse(@RequestBody Warehouse warehouseDto){
        Result result = warehouseService.addWarehouse(warehouseDto);
        return result;
    }

    @GetMapping()
    public List<Warehouse> getWarehouse(){
        List<Warehouse> warehouse = warehouseService.getWarehouse();
        return warehouse;
    }

    @GetMapping("/{id}")
    public Warehouse getById(@PathVariable Integer id){
        Warehouse warehouse = warehouseService.getById(id);
        return warehouse;
    }

    @PutMapping("/{id}")
    public Result updateWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouseDto){
        Result result = warehouseService.editWarehouse(id, warehouseDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehouse(@PathVariable Integer id){
        Result result = warehouseService.deleteWarehouse(id);
        return result;
    }
}
