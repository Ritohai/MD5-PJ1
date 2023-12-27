package ra.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestLogin {
    @NotEmpty(message = "Không để trống!")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",message = "Yêu cầu phải 8 kí tự trở lên.")
    private String userName;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{6,}$", message = "Mật khẩu phải có 8 kí tự, ít nhất có 1 chữ hoa, một chữ thường và 1 số")
    private String passWord;

}
