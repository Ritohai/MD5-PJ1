package ra.service;

import ra.exception.customer.CustomerException;
import ra.model.dto.request.BrandRequest;
import ra.model.dto.response.BrandResponse;
import ra.model.entity.Brand;

import java.util.List;

public interface IBrandService {
    List<Brand> getAllBrand();
    String save(BrandRequest bannerRequest) throws CustomerException;
    BrandResponse updateBrand(Long id, BrandRequest bannerRequest);
    BrandResponse findById(Long id);
    BrandResponse changeStatus(Long id);
}
