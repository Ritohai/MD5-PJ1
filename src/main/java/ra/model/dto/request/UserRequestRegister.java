package ra.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestRegister {
    @NotEmpty(message = "Không để trống")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$", message = "Email không đúng định dạng.")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",message = "Yêu cầu phải 8 kí tự trở lên.")
    private String userName;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$", message = "Mật khẩu phải có 8 kí tự và một chữ thường và 1 số")
    private String passWord;
    private Set<String> roles;

}
