package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ra.exception.customer.CustomerException;
import ra.exception.customer.LoginException;
import ra.model.dto.request.CartRequest;
import ra.model.dto.response.CartResponse;
import ra.model.entity.Cart;
import ra.model.entity.Product;
import ra.model.entity.StatusOrder;
import ra.model.entity.User;
import ra.repository.CartRepository;
import ra.repository.ProductRepository;
import ra.security.userPrincipal.UserPrincipal;
import ra.service.ICartService;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Cart> findAll(Authentication authentication) throws LoginException{
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (userPrincipal== null){
            throw  new LoginException("Yêu cầu đăng nhập.");
        }
        User user = userPrincipal.getUser();
        return cartRepository.findAllByUser(user);

    }

    @Override
    public CartResponse addToCart(Authentication authentication, CartRequest cartRequest) throws LoginException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (userPrincipal== null){
            throw  new LoginException("Yêu cầu đăng nhập.");
        }
        User user = userPrincipal.getUser();
        Product product = productRepository.findById(cartRequest.getProductId()).get();
        if (product==null){
            throw new LoginException("Product not found");}
        List<Cart> carts = cartRepository.findAllByUser(user);
        Cart cart = Cart.builder()
                .product(product)
                .quantity(cartRequest.getQuantity())
                .user(user)
                .build();
        if (carts.isEmpty()){
           cartRepository.save(cart);
            return CartResponse.builder()
                    .cartId(cart.getCartId())
                    .product(cart.getProduct())
                    .quantity(cart.getQuantity())
                    .build();
        }
        for (Cart c: carts) {
            if (c.getProduct().getProductId().equals(cartRequest.getProductId())){
                c.setQuantity(c.getQuantity() + cartRequest.getQuantity());
                cartRepository.save(c);
                return CartResponse.builder()
                        .cartId(c.getCartId())
                        .product(c.getProduct())
                        .quantity(c.getQuantity())
                        .build();
            }
        }
        cartRepository.save(cart);

        return CartResponse.builder()
                .cartId(cart.getCartId())
                .product(cart.getProduct())
                .quantity(cart.getQuantity())
                .build();
    }


    @Override
    public CartResponse findByIdCart(Long id, Authentication authentication, CartRequest cartRequest) throws LoginException{
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (userPrincipal== null){
            throw  new LoginException("Yêu cầu đăng nhập.");
        }
        User user = userPrincipal.getUser();
        if (user.getStatus()) {
            Optional<Cart> cart = cartRepository.findById(id);
            return CartResponse.builder()
                    .cartId(cart.get().getCartId())
                    .product(cart.get().getProduct())
                    .quantity(cart.get().getQuantity())
                    .build();

        }
        throw new LoginException("Tài khoản bị khóa.");
    }

    @Override
    public String deleteCart(Authentication authentication, Long id) throws CustomerException{
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (userPrincipal== null){
            throw  new CustomerException("Yêu cầu đăng nhập.");
        }
        User user = userPrincipal.getUser();
        List<Cart> cart = cartRepository.findAllByUser(user);
        if (!cart.isEmpty()) {
            Optional<Cart> cartNew = cartRepository.findById(id);
            if (cartNew.isPresent()) {
                cartRepository.deleteById(id);
            }
        }
        return "Delete done";
    }
}
