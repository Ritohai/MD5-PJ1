package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.entity.Image;

public interface IUploadFile extends JpaRepository<Image, Long> {
}
