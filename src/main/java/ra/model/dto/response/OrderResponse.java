package ra.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import ra.model.entity.User;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {

    private String phone;
    private String address;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;
    private Boolean status;
}
