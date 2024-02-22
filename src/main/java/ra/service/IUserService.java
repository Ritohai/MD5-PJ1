package ra.service;

import org.springframework.security.core.Authentication;
import ra.exception.customer.CustomerException;
import ra.exception.customer.EmptyException;
import ra.exception.customer.LoginException;
import ra.model.dto.request.UserRequestLogin;
import ra.model.dto.request.UserRequestRegister;
import ra.model.dto.response.JwtResponse;
import ra.model.dto.response.UserResponse;
import ra.model.entity.User;

import java.util.List;

public interface IUserService {
    JwtResponse handleLogin(UserRequestLogin userRequestLogin) throws CustomerException, LoginException;

    String handleRegister(UserRequestRegister userRequestRegister) throws CustomerException, LoginException;

    List<User> getAllUser();

    String changeStatus(Long id) throws CustomerException;

    UserResponse findById(Long id);



}
