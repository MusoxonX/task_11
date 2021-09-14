package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_11.entity.Category;
import uz.pdp.task_11.payload.CategoryDto;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @PostMapping()
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategory(categoryDto);
        return result;
    }

    @GetMapping()
    public List<Category> getCategory(){
        List<Category> category = categoryService.getCategory();
        return category;
    }

    //    get by id
    @GetMapping("/{id}")
    public Category getByIdCategory(@PathVariable Integer id){
        Category category = categoryService.getByIdCategory(id);
        return category;
    }

    //    edit
    @PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        Result result = categoryService.editCategory(id, categoryDto);
        return result;
    }

    //   delete
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        Result result = categoryService.deleteCategory(id);
        return result;
    }
}
