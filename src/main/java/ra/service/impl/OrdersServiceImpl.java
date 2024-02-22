package ra.service.impl;

import jakarta.transaction.Transactional;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ra.exception.customer.EmptyException;
import ra.exception.customer.LoginException;
import ra.model.dto.request.OrderRequest;
import ra.model.dto.response.CartResponse;
import ra.model.dto.response.OrderResponse;
import ra.model.entity.*;
import ra.repository.CartRepository;
import ra.repository.OrderDetailRepository;
import ra.repository.OrdersRepository;
import ra.security.userPrincipal.UserPrincipal;
import ra.service.IOrdersService;

import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;



    @Override
    public OrderResponse addToOrders(OrderRequest orderRequest, Authentication authentication) throws LoginException, EmptyException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (userPrincipal== null){
            throw  new EmptyException("Yêu cầu đăng nhập.");
        }
        User user = userPrincipal.getUser();
        List<Cart> carts = cartRepository.findAllByUser(user);
        if (carts.isEmpty()) {
            throw new EmptyException("Giỏ hàng trống.");
        }
        Orders orders = Orders.builder()
                .user(user)
                .address(orderRequest.getAddress())
                .phone(orderRequest.getPhone())
                .description(orderRequest.getDescription())
                .createdDate(new Date())
                .status(StatusOrder.WAITING.toString())
                .build();
        ordersRepository.save(orders);
        for (Cart c: carts) {
            OrderDetail orderDetail = OrderDetail.builder()
                    .orders(orders)
                    .product(c.getProduct())
                    .quantity(c.getQuantity())
                    .price(c.getProduct().getPrice())
                    .statusOrders(StatusOrder.WAITING.toString())
                    .build();
            orderDetailRepository.save(orderDetail);
            cartRepository.delete(c);
        }
        return OrderResponse.builder()
                .address(orders.getAddress())
                .phone(orders.getPhone())
                .description(orders.getDescription())
                .createdDate(orders.getCreatedDate())
                .status(orders.getStatus())
                .build();
    }
}
