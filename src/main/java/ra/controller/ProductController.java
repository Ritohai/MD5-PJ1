package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @PostMapping
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

    @PatchMapping("/status/{id}")
    public ResponseEntity<String> changStatus(@PathVariable Long id) throws CustomerException{
        return new ResponseEntity<>(productService.changeStatus(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam(required = false,name = "nameProduct", defaultValue = "") String nameProduct,
                                                @RequestParam(required = false, name = "startPrice") Double startPrice,
                                                @RequestParam(required = false, name = "endPrice") Double endPrice,
                                                @RequestParam(defaultValue = "0", name = "page") Integer page,
                                                @RequestParam(defaultValue = "2",name = "limit") Integer limit) {
        return new ResponseEntity<>(productService.searchByNameProductOrPrice(nameProduct,startPrice,endPrice,page, limit), HttpStatus.OK);

    }



}
