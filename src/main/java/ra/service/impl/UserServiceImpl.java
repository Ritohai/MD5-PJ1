package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.exception.customer.CustomerException;
import ra.exception.customer.EmptyException;
import ra.exception.customer.LoginException;
import ra.model.dto.request.UserRequestLogin;
import ra.model.dto.request.UserRequestRegister;
import ra.model.dto.response.JwtResponse;
import ra.model.dto.response.UserResponse;
import ra.model.entity.Role;
import ra.model.entity.User;
import ra.repository.RoleRepository;
import ra.repository.UserRepository;
import ra.security.jwt.JwtProvider;
import ra.security.userPrincipal.UserPrincipal;
import ra.service.IUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public JwtResponse handleLogin(UserRequestLogin userRequestLogin) throws CustomerException, LoginException {
        Authentication authentication;
        try {
            authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userRequestLogin.getUserName(), userRequestLogin.getPassWord()));

        } catch (AuthenticationException au) {
            throw new CustomerException("Tài khoản hoặc mật khẩu sai.");
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (!userPrincipal.getUser().getStatus()){
            throw new CustomerException("Tài khoản bị khóa.");
        }

        String token = jwtProvider.generateToken(userPrincipal);
        JwtResponse jwtResponse = JwtResponse.builder()
                .users(userPrincipal.getUser())
                .token(token)
                .roles(userPrincipal.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toSet()))
                .build();
        return jwtResponse;


    }

    @Override
    public String handleRegister(UserRequestRegister userRequestRegister) throws CustomerException, LoginException {
        if (userRepository.existsByEmail(userRequestRegister.getEmail())) {
            throw new CustomerException("Email đã tồn tại");
        }
        if (userRepository.existsByUserName(userRequestRegister.getUserName())) {
            throw new CustomerException("Username đã tồn tại.");
        }
        Set<Role> roles = new HashSet<>();
        userRequestRegister.getRoles().forEach(item -> {
//            Lặp từng phần tử trong mảng role<String> từ người dùng gửi lên
            roles.add(roleRepository.findByName(item).orElseThrow(() -> new RuntimeException(item + " not found")));
        });
        User user = User.builder()
                .email(userRequestRegister.getEmail())
                .userName(userRequestRegister.getUserName())
                .passWord(passwordEncoder.encode(userRequestRegister.getPassWord()))
                .status(true)
                .roles(roles)
                .build();
        userRepository.save(user);
        return "Success";
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    @Override
    public UserResponse changeStatus(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser != null) {
            User user = optionalUser.get();
            user.setStatus(!user.getStatus());
            userRepository.save(user);
        }
        return null;
    }

    @Override
    public UserResponse findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return UserResponse.builder()
                .id(optionalUser.get().getId())
                .email(optionalUser.get().getEmail())
                .userName(optionalUser.get().getUserName())
                .passWord(optionalUser.get().getPassWord())
                .status(optionalUser.get().getStatus())
                .roles(optionalUser.get().getRoles().toString())
                .build();

    }

    @Override
    public UserResponse searchUser(String username) {
        return null;
    }
}
