package ra.security.userPrincipal;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ra.model.entity.User;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserPrincipal implements UserDetails {
    private User user;
    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassWord();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    // Account hết hạn
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Account khóa
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Thông tin hết hạn
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Account không tồn tại
    @Override
    public boolean isEnabled() {
        return true;
    }
}
