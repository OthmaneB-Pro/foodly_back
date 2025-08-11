package foodly_backend.service;

import foodly_backend.dto.OrderDTO;
import foodly_backend.dto.OrderItemDTO;
import foodly_backend.dto.UpdateOrderItemRequest;
import foodly_backend.entity.MenuEntity;
import foodly_backend.entity.OrderEntity;
import foodly_backend.entity.OrderItemEntity;
import foodly_backend.repository.MenuRepository;
import foodly_backend.repository.OrderItemRepository;
import foodly_backend.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuService menuService;
    private final MenuRepository menuRepository;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, MenuService menuService, MenuRepository menuRepository, UserService userService, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.menuService = menuService;
        this.menuRepository = menuRepository;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderEntity addOrderMenu(int userId, int menuId, int quantity) {
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

        return order;
    }


    public List<OrderDTO> getOrderList() {
        List<OrderEntity> orders = orderRepository.findAll();

        return orders.stream().map(order -> {
            List<OrderItemDTO> items = order.getItems().stream().map(item -> {
                MenuEntity menu = item.getMenu();
                return new OrderItemDTO(item.getId(), menu.getId(), menu.getTitle(), menu.getImage_source(), menu.getPrice(), item.getQuantity());
            }).collect(Collectors.toList());

            return new OrderDTO(order.getId(), order.getStatus(), items);
        }).collect(Collectors.toList());
    }

    @Transactional
    public void deleteOrderItem(int itemId) {
        OrderItemEntity item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem non trouvé"));
        orderItemRepository.delete(item);
    }

    @Transactional
    public void updateOrderItem(int itemId, UpdateOrderItemRequest request) {
        OrderItemEntity item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem non trouvé"));

        MenuEntity menu = item.getMenu();
        menu.setTitle(request.getTitle());
        menu.setImage_source(request.getImage_source());
        menu.setPrice(request.getPrice());

        menuRepository.save(menu);
    }
}
