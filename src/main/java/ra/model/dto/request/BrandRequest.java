package ra.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BrandRequest {
    @NotEmpty(message = "Không được trống!")
    private String nameBrand;
}
