package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.Output;
import uz.pdp.task_11.entity.OutputProduct;
import uz.pdp.task_11.entity.Product;
import uz.pdp.task_11.payload.OutputProductDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.repository.OutputProductRepository;
import uz.pdp.task_11.repository.OutputRepository;
import uz.pdp.task_11.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OutputRepository outputRepository;

    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getOutputId());
        if (!optionalProduct.isPresent()){
            return new Result("like this product not found",false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()){
            return new Result("output error",false);
        }
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("Product output saved",true);
    }

    public List<OutputProduct> getOutputProduct(){
        List<OutputProduct> all = outputProductRepository.findAll();
        return all;
    }

    public OutputProduct getById(@PathVariable Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()){
            OutputProduct outputProduct = optionalOutputProduct.get();
            return outputProduct;
        }
        return new OutputProduct();
    }

    public Result deleteOutputProduct(@PathVariable Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()){
            outputProductRepository.deleteById(id);
            return new Result("output product deleted",true);
        }
        return new Result("output product not found",false);
    }

    public Result editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()){
            return new Result("output product not found",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getOutputId());
        if (!optionalProduct.isPresent()){
            return new Result("like this product not found",false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()){
            return new Result("output error",false);
        }
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("output product edited",true);
    }
}
