package ra.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {
    @NotEmpty(message = "Không để trống!")
    private String nameProduct;
    @NotBlank(message = "Không để trống")
    private MultipartFile imageProduct;
    private String description;
    @Min(value = 1, message = "Giá nhỏ nhất là 1.")
    private Double price;
    @Min(value = 1, message = "Số lượng nhỏ nhất là 1.")
    private Integer stock;
    @NotEmpty(message = "Không để trống.")
    private Long category;
    @NotEmpty(message = "Không để trống.")
    private Long brand;
}
