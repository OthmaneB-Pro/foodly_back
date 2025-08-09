package foodly_backend.controller;

import foodly_backend.dto.AddToCartRequest;
import foodly_backend.dto.OrderDTO;
import foodly_backend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "cart")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest request) {
        orderService.addOrderMenu(request.getUserId(), request.getMenuId(), request.getQuantity());
        return ResponseEntity.ok("Produit ajouté au panier !");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping
    public List<OrderDTO> getOrderList() {
        return this.orderService.getOrderList();
    }

}
