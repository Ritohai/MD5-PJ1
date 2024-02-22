package ra.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.exception.customer.CustomerException;
import ra.exception.customer.LoginException;
import ra.model.dto.request.CategoryRequest;
import ra.model.dto.request.UserRequestLogin;
import ra.model.dto.request.UserRequestRegister;
import ra.model.dto.response.JwtResponse;
import ra.model.entity.Category;
import ra.service.ICategoryService;
import ra.service.IProductService;
import ra.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControler {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;

    @PostMapping("/login")
    private ResponseEntity<JwtResponse> handleLogin(@Valid @RequestBody UserRequestLogin userRequestLogin) throws CustomerException, LoginException {
        return new ResponseEntity<>(userService.handleLogin(userRequestLogin), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> handleRegister(@Valid @RequestBody UserRequestRegister userRequestRegister) throws CustomerException, LoginException{
        return new ResponseEntity<>(userService.handleRegister(userRequestRegister), HttpStatus.CREATED);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getAllProduct() {
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

}
