package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.response.CategoryResponse;
import ra.model.dto.response.UserResponse;
import ra.model.entity.Category;
import ra.model.entity.User;
import ra.service.ICategoryService;
import ra.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }
    @PatchMapping("/status/{id}")
    public ResponseEntity<UserResponse> changeStatus(@PathVariable Long id) {
        return new ResponseEntity<>(userService.changeStatus(id), HttpStatus.OK);
    }




}
