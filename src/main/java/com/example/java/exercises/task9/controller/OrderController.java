package com.example.java.exercises.task9.controller;

import com.example.java.exercises.task9.dto.order.OrderDTO;
import com.example.java.exercises.task9.dto.order.OrderModifyDTO;
import com.example.java.exercises.task9.service.interfaces.CheckoutService;
import com.example.java.exercises.task9.service.interfaces.OrderService;
import com.example.java.exercises.task9.utils.ReflectionValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private final OrderService orderService;
    private final CheckoutService checkoutService;

    public OrderController (OrderService orderService, CheckoutService checkoutService) {
        this.orderService = orderService;
        this.checkoutService = checkoutService;
    }

    //
    @PostMapping(value = "/{customerId}")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable int customerId, @RequestBody OrderModifyDTO orderModifyDTO){
        try {
            ReflectionValidator.validate(orderModifyDTO);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        OrderDTO orderDTO = orderService.createOrder(customerId, orderModifyDTO);
        if (orderDTO == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> getOrderDetail(@PathVariable int id){
        OrderDTO orderDTO = orderService.getOrder(id);
        if (orderDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping(value = "/{id}/{paymentAccountId}")
    public ResponseEntity<String> checkout(@PathVariable int id, @PathVariable int paymentAccountId){
        try  {
            checkoutService.checkout(id, paymentAccountId);
            return ResponseEntity.ok("success");
        }
        catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<OrderDTO>> getOrders(
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC)
            Pageable pageable){
        Page<OrderDTO> orderDTOs = orderService.getOrders(pageable);
        return ResponseEntity.ok(orderDTOs);
    }
}
