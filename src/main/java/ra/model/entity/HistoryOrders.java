package ra.repository;

import jakarta.persistence.*;
import lombok.*;
import ra.model.entity.OrderDetail;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HistoryOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private OrderDetail orderDetail;

}
