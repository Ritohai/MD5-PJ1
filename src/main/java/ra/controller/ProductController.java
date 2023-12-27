package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.exception.customer.CustomerException;
import ra.model.dto.request.ProductRequest;
import ra.model.dto.response.ProductResponse;
import ra.model.entity.Product;
import ra.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@ModelAttribute ProductRequest productRequest) throws CustomerException {
        return new ResponseEntity<>(productService.saveProduct(productRequest), HttpStatus.CREATED);
    }

    @PatchMapping ("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @ModelAttribute ProductRequest productRequest) throws CustomerException {
        return new ResponseEntity<>(productService.updateProduct(id, productRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }

}
