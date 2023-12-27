package ra.service;

import ra.exception.customer.CustomerException;
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

    UserResponse changeStatus(Long id);
    UserResponse findById(Long id);

    UserResponse searchUser(String username);
}
