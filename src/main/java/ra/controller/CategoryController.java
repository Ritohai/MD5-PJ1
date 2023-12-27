package ra.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.exception.customer.CustomerException;
import ra.model.dto.request.CategoryRequest;
import ra.model.dto.response.CategoryResponse;
import ra.model.entity.Category;
import ra.service.ICategoryService;

import java.util.List;


@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) throws CustomerException {
        return new ResponseEntity<>(categoryService.save(categoryRequest),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> editCategory(@PathVariable Long id,@RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryRequest),HttpStatus.ACCEPTED);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<CategoryResponse> changeStatus(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.changeStatus(id),HttpStatus.OK);
    }

}
