package foodly_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Menu")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String image_source;
    private String title;
    private int price;
    private int quantity;
    private boolean is_available;
    private boolean is_advertised;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    public MenuEntity() {
    }

    public MenuEntity(int id, String image_source, String title, int price, int quantity, boolean is_available, boolean is_advertised, UserEntity user) {
        this.id = id;
        this.image_source = image_source;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.is_available = is_available;
        this.is_advertised = is_advertised;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
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
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public boolean isIs_advertised() {
        return is_advertised;
    }

    public void setIs_advertised(boolean is_advertised) {
        this.is_advertised = is_advertised;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
