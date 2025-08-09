package foodly_backend.service;

import foodly_backend.dto.OrderDTO;
import foodly_backend.dto.OrderItemDTO;
import foodly_backend.entity.MenuEntity;
import foodly_backend.entity.OrderEntity;
import foodly_backend.entity.OrderItemEntity;
import foodly_backend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuService menuService;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, MenuService menuService, UserService userService) {
        this.orderRepository = orderRepository;
        this.menuService = menuService;
        this.userService = userService;
    }

    public void addOrderMenu(int userId, int menuId, int quantity) {
        OrderEntity order = orderRepository.findByUserIdAndStatus(userId, "IN_CART")
                .orElseGet(() -> {
                    OrderEntity newOrder = new OrderEntity();
                    newOrder.setUser(userService.getUserById(userId));
                    newOrder.setStatus("IN_CART");
                    return newOrder;
                });

        Optional<OrderItemEntity> existingItem = order.getItems().stream()
                .filter(i -> i.getMenu().getId() == menuId)
                .findFirst();

        MenuEntity menu = menuService.getMenuById(menuId);

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            OrderItemEntity newItem = new OrderItemEntity();
            newItem.setOrder(order);
            newItem.setMenu(menu);
            newItem.setQuantity(quantity);
            order.getItems().add(newItem);
        }

        orderRepository.save(order);
    }

    public List<OrderDTO> getOrderList() {
        List<OrderEntity> orders = orderRepository.findAll();

        return orders.stream().map(order -> {
            List<OrderItemDTO> items = order.getItems().stream().map(item -> {
                MenuEntity menu = item.getMenu();
                return new OrderItemDTO(menu.getTitle(), menu.getImage_source(), menu.getPrice(), item.getQuantity());
            }).collect(Collectors.toList());

            return new OrderDTO(order.getId(), order.getStatus(), items);
        }).collect(Collectors.toList());
    }
}
