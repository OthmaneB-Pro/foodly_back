package foodly_backend.dto;

import java.util.List;

public class OrderDTO {
    private int id;
    private String status;
    private List<OrderItemDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(int id, String status, List<OrderItemDTO> items) {
        this.id = id;
        this.status = status;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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