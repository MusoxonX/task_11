package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_11.entity.Currency;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @PostMapping()
    public Result addCurrency(@RequestBody Currency currencyDto){
        Result result = currencyService.addCurrency(currencyDto);
        return result;
    }

    @GetMapping()
    public List<Currency> getCurrency(){
        List<Currency> currency = currencyService.getCurrency();
        return currency;
    }

    @GetMapping("/{id}")
    public Currency getById(@PathVariable Integer id){
        Currency currency = currencyService.getById(id);
        return currency;
    }

    @PutMapping("/{id}")
    public Result updateCurrency(@PathVariable Integer id, @RequestBody Currency currencyDto){
        Result result = currencyService.editCurrency(id, currencyDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteCurrency(@PathVariable Integer id){
        Result result = currencyService.deleteCurrency(id);
        return result;
    }
}
