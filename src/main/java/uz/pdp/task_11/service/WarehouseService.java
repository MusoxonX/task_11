package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.Warehouse;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

//    add
    public Result addWarehouse(@RequestBody Warehouse warehouseDto){
        boolean exists = warehouseRepository.existsByName(warehouseDto.getName());
        if (exists){
            return new Result("like this warehouse already exists",false);
        }
        warehouseRepository.save(warehouseDto);
        return new Result("Warehouse added to repository",true);
    }

//    get
    public List<Warehouse> getWarehouse(){
        List<Warehouse> all = warehouseRepository.findAll();
        return all;
    }

//    get by Id
    public Warehouse getById(@PathVariable Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()){
            return optionalWarehouse.get();
        }
        return new Warehouse();
    }

//    update
    public Result editWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouseDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()){
            return new Result("Warehouse not found",false);
        }
        Warehouse warehouse = optionalWarehouse.get();
        warehouse.setName(warehouseDto.getName());
        warehouseRepository.save(warehouse);
        return new Result("Warehouse updated",true);
    }

//    delete
    public Result deleteWarehouse(@PathVariable Integer id){
        boolean exists = warehouseRepository.existsById(id);
        if (exists){
            warehouseRepository.deleteById(id);
            return new Result("Warehouse deleted",true);
        }
        return new Result("Warehouse not found",false);
    }
}
