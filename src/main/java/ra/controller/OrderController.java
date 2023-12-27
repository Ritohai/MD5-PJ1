package ra.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.exception.customer.EmptyException;
import ra.exception.customer.LoginException;
import ra.model.dto.request.OrderRequest;
import ra.model.dto.response.OrderResponse;
import ra.service.IOrdersService;

@RestController
@RequestMapping("/user/orders")
public class OrderController {
    @Autowired
    private IOrdersService ordersService;

    @PostMapping
    public ResponseEntity<OrderResponse> createToOrders(@Valid @RequestBody OrderRequest orderRequest, Authentication authentication) throws LoginException, EmptyException {
        return new ResponseEntity<>(ordersService.addToOrders(orderRequest, authentication), HttpStatus.CREATED);
    }
}
