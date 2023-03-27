package inventory;
import java.util.ArrayList;

import inventory.item.Item;

public class Inventory {
    private ArrayList<Item> items;
    
    public Inventory() {
        this.items = new ArrayList<Item>();
    }
    
    public void addItem(Item item) {
        this.items.add(item);
    }
    
    public void removeItem(Item item) {
        this.items.remove(item);
    }
    
    public ArrayList<Item> getItems() {
        return this.items;
    }
    
    public int getSize() {
        return this.items.size();
    }
}