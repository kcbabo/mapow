package org.mapow;

public class Item {

    private String name;
    private int quantity;
    
    public String getName() {
        return name;
    }
    
    public Item setName(String name) {
        this.name = name;
        return this;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public Item setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
    
}
