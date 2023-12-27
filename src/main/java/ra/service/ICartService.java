package ra.service;

import org.springframework.security.core.Authentication;
import ra.exception.customer.CustomerException;
import ra.exception.customer.LoginException;
import ra.model.dto.request.CartRequest;
import ra.model.dto.response.CartResponse;
import ra.model.entity.Cart;

import java.util.List;

public interface ICartService {
    List<Cart> findAll(Authentication authentication) throws LoginException;
    CartResponse addToCart(Authentication authentication, CartRequest cartRequest) throws LoginException;

    String deleteCart(Authentication authentication, Long id) throws CustomerException;
    CartResponse findByIdCart(Long id, Authentication authentication, CartRequest cartRequest) throws LoginException;

}
