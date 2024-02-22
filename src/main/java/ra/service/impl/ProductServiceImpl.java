package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ra.exception.customer.CustomerException;
import ra.model.dto.request.ProductRequest;
import ra.model.dto.response.ProductResponse;
import ra.model.entity.Brand;
import ra.model.entity.Category;
import ra.model.entity.Product;
import ra.repository.BrandRepository;
import ra.repository.CategoryRepository;
import ra.repository.IUploadFile;
import ra.repository.ProductRepository;
import ra.service.IProductService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private IUploadFile uploadFile;
    @Value("${path-upload}")
    private String pathUpload;
    @Value("${server.port}")
    private Long port;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public String saveProduct(ProductRequest request) throws CustomerException {
        Category category = categoryRepository.findById(request.getCategory()).get();
        Brand brand = brandRepository.findById(request.getBrand()).get();
        if (category == null) {
            throw new CustomerException("Id not found");
        }
        Product product = Product.builder()
                .nameProduct(request.getNameProduct())
                .imageProduct(addSingleImage(request.getImageProduct()))
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(category)
                .brand(brand)
                .statusProduct(true)
                .build();
        productRepository.save(product);
        return "Success";
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) throws CustomerException {
        Category category = categoryRepository.findById(productRequest.getCategory()).get();
        Brand brand = brandRepository.findById(productRequest.getBrand()).get();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            throw new CustomerException("Id not found");
        }
        Product product = optionalProduct.get();
        product.setNameProduct(productRequest.getNameProduct());
        product.setImageProduct(addSingleImage(productRequest.getImageProduct()));
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setCategory(category);
        product.setBrand(brand);
        product.setStatusProduct(optionalProduct.get().getStatusProduct());
        productRepository.save(product);

        return ProductResponse.builder()
                .productId(product.getProductId())
                .nameProduct(product.getNameProduct())
                .imageProduct(product.getImageProduct())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(product.getCategory().getNameCategory())
                .brand(product.getBrand().getBrandName())
                .build();
    }

    @Override
    public String addSingleImage(MultipartFile image) {
        String fileName = image.getOriginalFilename();

        try {
            FileCopyUtils.copy(image.getBytes(), new File(pathUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
//        Image image1 = String.builder().url("http://localhost:" + port + "/" + fileName).build();
        return fileName;
    }

    @Override
    public ProductResponse findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return ProductResponse.builder()
                .productId(optionalProduct.get().getProductId())
                .nameProduct(optionalProduct.get().getNameProduct())
                .imageProduct(optionalProduct.get().getImageProduct())
                .description(optionalProduct.get().getDescription())
                .price(optionalProduct.get().getPrice())
                .stock(optionalProduct.get().getStock())
                .category(optionalProduct.get().getCategory().getNameCategory())
                .brand(optionalProduct.get().getBrand().getBrandName())
                .build();
    }

    @Override
    public String delete(Long id) {
        productRepository.deleteById(id);
        return "Success";
    }


    @Override
    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }

    @Override
    public String changeStatus(Long id) throws CustomerException {
        if (findById(id) != null) {
            Product product = productRepository.findById(id).get();
            product.setStatusProduct(!product.getStatusProduct());
            if (product.getStatusProduct() == false) {
                throw new CustomerException("Không thấy id.");
            }
        }
        return "Thay đổi trạng thái thành công.";
    }

    @Override
    public List<Product> searchByNameProductOrPrice(String nameProduct, Double startPrice, Double endPrice, Integer page, Integer limit) {
        Sort sort = Sort.by(Sort.Direction.fromString("DESC"), "nameProduct");
        Pageable pageable = PageRequest.of(page, limit).withSort(sort);
        Page<Product> products = productRepository.findByNameProductOrPrice(nameProduct, startPrice, endPrice, pageable);
        List<Product> list = new ArrayList<>();
        for (Product p : products.getContent()) {
            if (p.getStatusProduct()) {
                list.add(p);
            }
        }
        return list;
    }
}
