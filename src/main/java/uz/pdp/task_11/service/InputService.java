package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.Currency;
import uz.pdp.task_11.entity.Input;
import uz.pdp.task_11.entity.Supplier;
import uz.pdp.task_11.entity.Warehouse;
import uz.pdp.task_11.payload.InputDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.repository.CurrencyRepository;
import uz.pdp.task_11.repository.InputRepository;
import uz.pdp.task_11.repository.SupplierRepository;
import uz.pdp.task_11.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addInput(@RequestBody InputDto inputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()){
            return new Result("warehouse not found",false);
        }

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()){
            return new Result("supplier not found",false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("currency not found",false);
        }

        Input input = new Input();
        input.setInputdate(inputDto.getDate());
        input.setWareHouse(optionalWarehouse.get());
        input.setSuplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setCode(UUID.randomUUID().toString());
        inputRepository.save(input);
        return new Result("input saved",true);
    }

    public List<Input> getInput(){
        List<Input> all = inputRepository.findAll();
        return all;
    }

    public Input getByIdInput(@PathVariable Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()){
            Input input = optionalInput.get();
            return input;
        }
        return new Input();
    }

    public Result editInput(@PathVariable Integer id,@RequestBody InputDto inputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()){
            return new Result("warehouse not found",false);
        }

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()){
            return new Result("supplier not found",false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("currency not found",false);
        }

        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()){
            return new Result("input not found",false);
        }
        Input input = optionalInput.get();
        input.setWareHouse(optionalWarehouse.get());
        input.setSuplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        inputRepository.save(input);
        return new Result("input edited",true);
    }

    public Result deleteInput(@PathVariable Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()){
            inputRepository.deleteById(id);
            return new Result("input deleted",true);
        }
        return new Result("input not found",false);
    }
}
