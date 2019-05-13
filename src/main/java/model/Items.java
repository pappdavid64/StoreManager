package model;

import util.ItemEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Items implements ItemEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Price")
    private int price;

    @Column(name = "Type")
    private String type;

    public Items(){}

    public Items(String name, int quantity, int price, String type)
    {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return quantity == items.quantity &&
                price == items.price &&
                id == items.id &&
                Objects.equals(name, items.name) &&
                Objects.equals(type, items.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price, type, id);
    }

    @Override
    public String toString() {
        return "Items{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", id=" + id +
                '}';
    }
}
