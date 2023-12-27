package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exception.customer.CustomerException;
import ra.model.dto.request.CategoryRequest;
import ra.model.dto.response.CategoryResponse;
import ra.model.entity.Category;
import ra.repository.CategoryRepository;
import ra.service.ICategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public String save(CategoryRequest categoryRequest) throws CustomerException {
        if (categoryRepository.existsByNameCategory(categoryRequest.getNameCategory())) {
            throw new CustomerException("Danh mục đã tồn tại");
        }
        Category category = Category.builder()
                .nameCategory(categoryRequest.getNameCategory())
                .status(true)
                .build();
        categoryRepository.save(category);
        return "Success";
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).get();
        if (category != null) {
            category.setNameCategory(categoryRequest.getNameCategory());
            categoryRepository.save(category);
        }
        return findById(id);

    }

    @Override
    public CategoryResponse findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        return CategoryResponse.builder()
                .categoryId(category.get().getCategoryId())
                .nameCategory(category.get().getNameCategory())
                .status(category.get().getStatus())
                .build();
    }

    @Override
    public CategoryResponse changeStatus(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory != null) {
            Category category = optionalCategory.get();
            category.setStatus(!category.getStatus());
            categoryRepository.save(category);
        } else {
            throw new RuntimeException("Id not found");
        }
        return findById(id);
    }
}
