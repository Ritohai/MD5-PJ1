package ra.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where ((lower(p.nameProduct) like %:nameProduct%) or :nameProduct is null or :nameProduct = '') and " +
            "(((:startPrice is not null and :endPrice is not null) and (p.price between :startPrice and :endPrice)) or" +
            " ((:startPrice is not null and :endPrice is null) and (p.price =  :startPrice)) or " +
            "((:startPrice is null and :endPrice is not null) and (p.price = :endPrice)) or" +
            " (:startPrice is null and :endPrice is null)) and p.statusProduct = true")
    Page<Product> findByNameProductOrPrice(String nameProduct,
                                           @Param("startPrice") Double startPrice,
                                           @Param("endPrice") Double endPrice, Pageable pageable);

}
