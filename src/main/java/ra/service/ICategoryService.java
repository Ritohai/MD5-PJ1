package ra.service;

import ra.exception.customer.CustomerException;
import ra.model.dto.request.CategoryRequest;
import ra.model.dto.response.CategoryResponse;
import ra.model.entity.Category;


import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory();
    String save(CategoryRequest categoryRequest) throws CustomerException;

    CategoryResponse updateCategory(Long id,CategoryRequest categoryRequest);

    CategoryResponse findById(Long id);
    CategoryResponse changeStatus(Long id);
}
