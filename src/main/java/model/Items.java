package model;

import util.ItemEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class with information about items
 */
@Entity
public class Items implements ItemEntity {

    /**
     * The id of the item
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The name of the item
     */
    @Column(name = "Name")
    private String name;

    /**
     * The quantity of the item
     */
    @Column(name = "Quantity")
    private int quantity;

    /**
     * The price of the item
     */
    @Column(name = "Price")
    private int price;

    /**
     * The type of the item
     */
    @Column(name = "Type")
    private String type;

    /**
     * Constructor for the class
     */
    public Items(){}

    /**
     * Constructor for the class
     * @param name the name of the item
     * @param quantity the quantity of the item
     * @param price the price of the item
     * @param type the type of the item
     */
    public Items(String name, int quantity, int price, String type)
    {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    /**
     * Gets the name of the item
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item
     * @param name the new name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the quantity of the item
     * @return the quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item
     * @param quantity the new quantity of the item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the price
     * @return the price of the item
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the item
     * @param price the new price of the item
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets the type of the item
     * @return the type of the item
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the item
     * @param type the new type of the item
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the id of the item
     * @return the id of the item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the item
     * @param id the new id of the item
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * The equals() of the Items class
     */
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

    /**
     * The hasCode() of the Items class
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price, type, id);
    }

    /**
     * The toString() of the Items class
     */
    @Override
    public String toString() {
        return  name + " (" +
                "quantity=" + quantity +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", id=" + id +
                ")";
    }
}
