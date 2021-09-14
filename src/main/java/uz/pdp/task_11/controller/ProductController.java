package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;
import uz.pdp.task_11.entity.Product;
import uz.pdp.task_11.payload.ProductDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping()
    public Result addProduct(@RequestBody ProductDto productDto){
        Result result = productService.addProduct(productDto);
        return result;
    }

    @GetMapping()
    public List<Product> getProduct(){
        List<Product> productList = productService.getProduct();
        return productList;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id){
        Product product = productService.getById(id);
        return product;
    }

    @PutMapping("/{id}")
    public Result editProduct(@PathVariable Integer id,@RequestBody ProductDto productDto){
        Result result = productService.editProduct(id, productDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id){
        Result delete = productService.delete(id);
        return delete;
    }
}
