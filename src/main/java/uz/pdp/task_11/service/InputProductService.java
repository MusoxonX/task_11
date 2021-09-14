package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.Input;
import uz.pdp.task_11.entity.InputProduct;
import uz.pdp.task_11.entity.Product;
import uz.pdp.task_11.payload.InputProductDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.repository.InputProductRepository;
import uz.pdp.task_11.repository.InputRepository;
import uz.pdp.task_11.repository.ProductRepository;

import javax.jnlp.IntegrationService;
import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InputRepository inputRepository;

    public Result addInputP(@RequestBody InputProductDto inputProductDto){
        InputProduct inputProduct = new InputProduct();
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()){
            return new Result("product not found",false);
        }
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()){
            return new Result("input not found",false);
        }
        inputProduct.setInput(optionalInput.get());
        return new Result(" Product input added to repository",true);
    }

    public List<InputProduct> getInputProduct(){
        List<InputProduct> all = inputProductRepository.findAll();
        return all;
    }

    public InputProduct getByIdInputProduct(@PathVariable Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()){
            return optionalInputProduct.get();
        }
        return new InputProduct();
    }
    public Result deleteInputProduct(@PathVariable Integer id){
        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (byId.isPresent()){
            inputProductRepository.deleteById(id);
            return new Result("Product input deleted",true);
        }
        return new Result("Input product not found",false);
    }

    public Result editInputProduct(@PathVariable Integer id,@RequestBody InputProductDto inputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()){
            return new Result("product not found",false);
        }

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()){
            return new Result("input not found",false);
        }

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()){
            return new Result("Input product not found",false);
        }
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setInput(optionalInput.get());
        return new Result("Input product edited",true);
    }
}
