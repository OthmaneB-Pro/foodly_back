package foodly_backend.dto;

import java.util.List;

public class OrderDTO {
    private int orderId;
    private String status;
    private List<OrderItemDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, String status, List<OrderItemDTO> items) {
        this.orderId = orderId;
        this.status = status;
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
