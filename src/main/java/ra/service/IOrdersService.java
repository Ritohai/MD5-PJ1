package ra.service;

import org.springframework.security.core.Authentication;
import ra.exception.customer.EmptyException;
import ra.exception.customer.LoginException;
import ra.model.dto.request.OrderRequest;
import ra.model.dto.response.OrderResponse;
import ra.model.entity.Orders;

import java.util.List;

public interface IOrdersService {

    OrderResponse addToOrders(OrderRequest orderRequest, Authentication authentication) throws LoginException, EmptyException;
}
