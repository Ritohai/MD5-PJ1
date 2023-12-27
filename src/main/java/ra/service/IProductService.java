package ra.service;

import org.springframework.web.multipart.MultipartFile;
import ra.exception.customer.CustomerException;
import ra.model.dto.request.ProductRequest;
import ra.model.dto.response.ProductResponse;
import ra.model.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    String saveProduct(ProductRequest request) throws CustomerException;
    ProductResponse updateProduct(Long id, ProductRequest productRequest) throws CustomerException;
    String addSingleImage(MultipartFile image);

    String delete(Long id);
    ProductResponse findById(Long id);

    List<Product> getAllProduct();
}
