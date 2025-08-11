package foodly_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItemDTO {
    @JsonProperty("id")
    private int itemId;
    private int menuId;
    private String title;
    @JsonProperty("imageSource")
    private String image_source;
    private double price;
    private int quantity;

    public OrderItemDTO() {
    }

    public OrderItemDTO(int itemId, int menuId, String title, String image_source, double price, int quantity) {
        this.itemId = itemId;
        this.menuId = menuId;
        this.title = title;
        this.image_source = image_source;
        this.price = price;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
