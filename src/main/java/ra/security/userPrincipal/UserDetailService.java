package ra.security.userPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.model.entity.User;
import ra.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserName(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserPrincipal  userPrincipal = UserPrincipal.builder()
                    .user(user)
                    .authorities(user.getRoles().stream().map(item -> new SimpleGrantedAuthority(item.getName())).toList())
                    .build();
            return userPrincipal;
        } else {
            throw new UsernameNotFoundException(username + " not found");
        }
    }


}
