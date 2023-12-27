package ra.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.exception.customer.CustomerException;
import ra.model.dto.request.BrandRequest;
import ra.model.dto.response.BrandResponse;
import ra.model.entity.Brand;
import ra.service.IBrandService;

import java.util.List;

@RestController
@RequestMapping("/admin/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrand() {
        return new ResponseEntity<>(brandService.getAllBrand(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> findByIdBrand(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBrand(@Valid @RequestBody BrandRequest brandRequest) throws CustomerException {
        return new ResponseEntity<>(brandService.save(brandRequest),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable Long id, @RequestBody BrandRequest brandRequest) {
        return new ResponseEntity<>(brandService.updateBrand(id, brandRequest), HttpStatus.OK);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<BrandResponse> changeStatus(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.changeStatus(id), HttpStatus.OK);
    }


}
