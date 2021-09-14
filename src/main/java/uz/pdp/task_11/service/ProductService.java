package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.Attachment;
import uz.pdp.task_11.entity.Category;
import uz.pdp.task_11.entity.Measurement;
import uz.pdp.task_11.entity.Product;
import uz.pdp.task_11.payload.ProductDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.repository.AttachmentRepository;
import uz.pdp.task_11.repository.CategoryRepository;
import uz.pdp.task_11.repository.MeasurementRepository;
import uz.pdp.task_11.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(@RequestBody ProductDto productDto){
        boolean b = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategory_id());
        if (b){
            return new Result("like this categry and product already exsists",false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory_id());
        if (!optionalCategory.isPresent()){
            return new Result("like this category not found",false);
        }

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()){
            return new Result("photo not found",false);
        }

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()){
            return new Result("measurement not found",false);
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(UUID.randomUUID().toString());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("product added",true);
    }

    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    public Product getById(@PathVariable Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            return product;
        }
        return new Product();
    }

    public Result editProduct(@PathVariable Integer id,@RequestBody ProductDto productDto){
        boolean b = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategory_id());
        if (b){
            return new Result("like this categry and product already exsists",false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory_id());
        if (!optionalCategory.isPresent()){
            return new Result("like this category not found",false);
        }

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()){
            return new Result("photo not found",false);
        }

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()){
            return new Result("measurement not found",false);
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("product adited",true);
    }

    public Result delete(@PathVariable Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return new Result("product deleted",true);
        }
        return new Result("product not found",false);
    }
}
