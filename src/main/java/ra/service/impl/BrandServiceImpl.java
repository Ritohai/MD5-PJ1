package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exception.customer.CustomerException;
import ra.model.dto.request.BrandRequest;
import ra.model.dto.response.BrandResponse;
import ra.model.entity.Brand;
import ra.repository.BrandRepository;
import ra.service.IBrandService;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public String save(BrandRequest brandRequest) throws CustomerException {

        if (brandRepository.existsByBrandName(brandRequest.getNameBrand())) {
            throw new CustomerException("Nhãn hiệu đã tồn tại.");
        }
        Brand brand = Brand.builder()
                .brandName(brandRequest.getNameBrand())
                .status(true)
                .build();
        brandRepository.save(brand);
        return "Success";
    }

    @Override
    public BrandResponse updateBrand(Long id, BrandRequest brandRequest) {
        Brand brand = brandRepository.findById(id).get();
        if (brand != null) {
            brand.setBrandName(brandRequest.getNameBrand());
            brandRepository.save(brand);
        }
        return findById(id);
    }

    @Override
    public BrandResponse findById(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        return BrandResponse.builder()
                .brandId(optionalBrand.get().getBrandId())
                .brandName(optionalBrand.get().getBrandName())
                .status(optionalBrand.get().getStatus())
                .build();
    }

    @Override
    public BrandResponse changeStatus(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand != null) {
            Brand brand = optionalBrand.get();
            brand.setStatus(!brand.getStatus());
            brandRepository.save(brand);
        }
        return findById(id);
    }
}
