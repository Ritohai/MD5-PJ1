package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.WishList;
@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
}
