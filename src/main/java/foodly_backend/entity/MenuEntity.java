package foodly_backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Menu")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("imageSource")
    @Column(name = "image_source")
    private String imageSource;
    private String title;
    private int price;
    private int quantity;
    @JsonProperty("isAvailable")
    @Column(name = "is_available")
    private boolean isAvailable;
    @JsonProperty("isAdvertised")
    @Column(name = "is_advertised")
    private boolean isAdvertised;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    public MenuEntity() {
    }

    public MenuEntity(int id, String imageSource, String title, int price, int quantity, boolean isAvailable, boolean isAdvertised, UserEntity user) {
        this.id = id;
        this.imageSource = imageSource;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
        this.isAdvertised = isAdvertised;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_source() {
        return imageSource;
    }

    public void setImage_source(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isIs_available() {
        return isAvailable;
    }

    public void setIs_available(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isIs_advertised() {
        return isAdvertised;
    }

    public void setIs_advertised(boolean isAdvertised) {
        this.isAdvertised = isAdvertised;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
