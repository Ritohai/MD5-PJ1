package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ra.model.entity.OrderDetail;
import ra.repository.OrderDetailRepository;
import ra.service.IOrderDetailService;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;





}
