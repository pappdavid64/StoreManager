package model;

import util.ItemEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Class with information about sold items.
 */
@Entity
public class SoldItems implements ItemEntity {

    /**
     * The id of the item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;


    /**
    * The name of the item.
    */
    @Column(name = "Name")
    private String name;


    /**
     * The type of the item.
     */
    @Column(name = "Type")
    private String type;

    /**
     * The quantity of the item.
     */
    @Column(name = "Quantity")
    private int quantity;

    /**
     * The price of the item.
     */
    @Column(name = "Price")
    private int price;

    /**
     * The sold date of the item.
     */
    @Column(name = "Sold_date")
    private Date soldDate;

    /**
     * Constructor of the class.
     */
    public SoldItems() {
    }


    /**
     * Constructor of the class.
     * @param name the name of the item
     * @param quantity the quantity of the item
     * @param price the price of the item
     * @param type the type of the item
     * @param soldDate the sold date of the item
     */
    public SoldItems(String name, int quantity, int price, String type, Date soldDate) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.soldDate = soldDate;
    }

    /**
     * Gets the id of the item.
     * @return the id of the item
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets the id of the item.
     * @param id the new id of the item
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Gets the name of the item.
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     * @param name the new name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of the item.
     * @return the type of the item
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the item.
     * @param type the new type of the item
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the quantity of the item.
     * @return the quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     * @param quantity the new quantity of the item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *Gets the price of the item.
     * @return the price of the item
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the item.
     * @param price the new price of the item
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets the sold date of the item.
     * @return the sold date of the item
     */
    public Date getSoldDate() {
        return soldDate;
    }

    /**
     * Sets the sold date of the item.
     * @param soldDate the new sold date of the item
     */
    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    /**
     * The toString() for the SoldItems class.
     * @return the formatted string
     */
    @Override
    public String toString() {
        return  name + " (" +
                "type='" + type + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", id=" + Id +
                ", sold date=" + soldDate +
                ")";
    }

    /**
     * The equals() for the SoldItems class.
     * @param o the object we want to test equals
     */
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

    /**
     * The hashCode() for the SoldItemsClass.
     * @return the hascode
     */
    @Override
    public int hashCode() {
        return Objects.hash(Id, name, type, quantity, price, soldDate);
    }
}
