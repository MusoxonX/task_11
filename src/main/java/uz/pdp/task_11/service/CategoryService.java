package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.Category;
import uz.pdp.task_11.payload.CategoryDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    //    add
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (category.getParentCategory()!=null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
            if (optionalCategory.isPresent()){
                return new Result("like this father category not found",false);
            }
            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("category added",true);
    }

//    get
    public List<Category> getCategory(){
        List<Category> all = categoryRepository.findAll();
        return all;
    }

//    get by id
    public Category getByIdCategory(@PathVariable Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        return new Category();
    }

//    edit
    public Result editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getCategoryId());
            if (categoryOptional != null){
                category.setParentCategory(categoryOptional.get());
            }
            categoryRepository.save(category);
            return new Result("category edited",true);
        }
        return new Result("category not not found",false);
    }

//   delete
    public Result deleteCategory(@PathVariable Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            categoryRepository.deleteById(id);
            return new Result("category deleted",true);
        }
        return new Result("category not found",false);
    }
}
