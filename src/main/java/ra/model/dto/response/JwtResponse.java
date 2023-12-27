package ra.model.dto.response;

import lombok.*;
import ra.model.entity.User;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private User users;
    private String token;
    private final String type = "Bearer";
    private Set<String> roles;

}
