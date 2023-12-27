package ra.model.dto.response;

import lombok.*;
import ra.model.entity.Product;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartResponse {
    private Long cartId;
    private Product product;
    private Integer quantity;

}
