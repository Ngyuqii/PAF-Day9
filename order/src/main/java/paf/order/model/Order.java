package paf.order.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Order {

    private String orderId;
    private String name;
    private String email;
    private Date deliveryDate;
    private List<LineItem> itemsList = new LinkedList<>();

    //Getters
    public String getOrderId() {
        return orderId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public List<LineItem> getItemsList() {
        return itemsList;
    }

    //Setters
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public void setItemsList(List<LineItem> itemsList) {
        this.itemsList = itemsList;
    }
    
    //Method to add a lineItem (item and quantity) into itemsList
    public void addItem(LineItem lineItem) {
        itemsList.add(lineItem);
    }

    @Override
    public String toString() {
        return "Order [name=" + name + ", email=" + email + ", deliveryDate=" 
			  + deliveryDate + ", lineItems=" + itemsList + "]";
    }

}