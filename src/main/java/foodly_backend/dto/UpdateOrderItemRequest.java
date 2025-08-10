package foodly_backend.dto;

public class UpdateOrderItemRequest {
    private String title;
    private String imageSource;
    private int price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_source() {
        return imageSource;
    }

    public void setImage_source(String imageSource) {
        this.imageSource = imageSource;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
