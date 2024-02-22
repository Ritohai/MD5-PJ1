package ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ra.exception.customer.CustomerException;
import ra.exception.customer.LoginException;
import ra.exception.customer.NotFoundException;
import ra.model.dto.request.CartRequest;
import ra.model.dto.response.CartResponse;
import ra.service.ICartService;

@RestController
@RequestMapping("/user/cart")
@AllArgsConstructor
public class CartController {
    private final ICartService cartService;

    @GetMapping
    public ResponseEntity<?> findByUser(Authentication authentication) throws LoginException{
        return new ResponseEntity<>(cartService.findAll(authentication), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartResponse> addToCart(@RequestBody CartRequest cartRequest,Authentication authentication) throws LoginException, NotFoundException {
        return new ResponseEntity<>(cartService.addToCart(authentication,cartRequest),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> findByIdCart(@PathVariable Long id, Authentication authentication,@RequestBody CartRequest cartRequest) throws LoginException{
        return new ResponseEntity<>(cartService.findByIdCart(id, authentication, cartRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteByIdCart(@PathVariable Long id, Authentication authentication) throws CustomerException {
        return new ResponseEntity<>(cartService.deleteCart(authentication, id), HttpStatus.OK);

    }

}
