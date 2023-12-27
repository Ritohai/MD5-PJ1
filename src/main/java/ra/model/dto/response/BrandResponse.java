package ra.model.dto.response;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BrandResponse {
    private Long brandId;
    private String brandName;
    private Boolean status;

}
