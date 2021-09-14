package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.*;
import uz.pdp.task_11.payload.InputDto;
import uz.pdp.task_11.payload.OutputDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.repository.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addOutput(@RequestBody OutputDto outputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()){
            return new Result("warehouse not found",false);
        }

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()){
            return new Result("client not found",false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("currency not found",false);
        }


        Output output = new Output();
        output.setDate(outputDto.getDate());
        output.setWareHouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setCode(UUID.randomUUID().toString());
        outputRepository.save(output);
        return new Result("output saved",true);
    }

    public List<Output> getOutput(){
        List<Output> all = outputRepository.findAll();
        return all;
    }

    public Output getByIdOutput(@PathVariable Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()){
            Output output = optionalOutput.get();
            return output;
        }
        return new Output();
    }

    public Result editOutput(@PathVariable Integer id,@RequestBody OutputDto outputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()){
            return new Result("warehouse not found",false);
        }

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()){
            return new Result("client not found",false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()){
            return new Result("currency not found",false);
        }

        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()){
            return new Result("Output not found",false);
        }
        Output output = optionalOutput.get();
        output.setWareHouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        outputRepository.save(output);
        return new Result("input edited",true);
    }

    public Result deleteOutput(@PathVariable Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()){
            outputRepository.deleteById(id);
            return new Result("output deleted",true);
        }
        return new Result("output not found",false);
    }
}
