package ra.model.dto.response;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private Long productId;
    private String nameProduct;
    private String imageProduct;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    private String brand;
}
