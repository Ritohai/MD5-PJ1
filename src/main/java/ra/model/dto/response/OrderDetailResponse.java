package ra.model.dto.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailResponse {
    private Long orderDetailId;
    private String nameProduct;
    private Double price;
    private Integer quantity;
    private String statusOrder;
}
