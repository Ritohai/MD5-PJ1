package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Brand;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByBrandName(String brand);
}
