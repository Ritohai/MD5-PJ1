package ra.model.dto.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailRequest {

    private Long productId;
    private Double price;
    private Integer quantity;

}
