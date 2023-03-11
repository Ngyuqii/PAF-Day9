package paf.order.model;

public class LineItem {

    private String item;
    private Integer quantity;

    //Getters
    public String getItem() {
        return item;
    }
    public Integer getQuantity() {
        return quantity;
    } 

    //Setters
    public void setItem(String item) {
        this.item = item;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "LineItem [item=" + item + ", quantity=" + quantity + "]";
    }
    
}
