package model;

import util.ItemEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class SoldItems implements ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Type")
    private String type;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Price")
    private int price;

    @Column(name = "Sold_date")
    private Date soldDate;

    public SoldItems() {
    }

    public SoldItems(String name, int quantity, int price, String type, Date soldDate) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.soldDate = soldDate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    @Override
    public String toString() {
        return  name + " (" +
                "type='" + type + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", soldDate=" + soldDate +
                "Id=" + Id +
                ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoldItems soldItems = (SoldItems) o;
        return Id == soldItems.Id &&
                quantity == soldItems.quantity &&
                price == soldItems.price &&
                Objects.equals(name, soldItems.name) &&
                Objects.equals(type, soldItems.type) &&
                Objects.equals(soldDate, soldItems.soldDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, type, quantity, price, soldDate);
    }
}
