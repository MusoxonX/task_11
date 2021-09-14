package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.Currency;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

//    add
    public Result addCurrency(@RequestBody Currency currencyDto){
        boolean exists = currencyRepository.existsByName(currencyDto.getName());
        if (exists){
            return new Result("like this currency already exists",false);
        }
        currencyRepository.save(currencyDto);
        return new Result("currency added to repository",true);
    }

//    get
    public List<Currency> getCurrency(){
        List<Currency> all = currencyRepository.findAll();
        return all;
    }

//    get by Id
    public Currency getById(@PathVariable Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()){
            return optionalCurrency.get();
        }
        return new Currency();
    }

//    update
    public Result editCurrency(@PathVariable Integer id, @RequestBody Currency currencyDto){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()){
            return new Result("currency not found",false);
        }
        Currency currency = optionalCurrency.get();
        currency.setName(currencyDto.getName());
        currencyRepository.save(currency);
        return new Result("currency updated",true);
    }

//    delete
    public Result deleteCurrency(@PathVariable Integer id){
        boolean exists = currencyRepository.existsById(id);
        if (exists){
            currencyRepository.deleteById(id);
            return new Result("currency deleted",true);
        }
        return new Result("currency not found",false);
    }
}
