package ra.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
    private User user;
    private String fullAddress;
    private String phone;
    private String receiveName;

}
