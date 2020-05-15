package itemstore;

import java.util.ArrayList;
import java.util.List;

public class ItemStore {

    private static ItemStore theInstance;

    private List<Item> items;

    private ItemStore() {
        items = new ArrayList<>();
        items.add(new Item(""));
    }

    public static ItemStore getInstance() {
        if (theInstance == null) {
            theInstance = new ItemStore();
        }
        return theInstance;
    }
    
    public Item peek() {
        return items.get(items.size() - 1);
    }
    
    public void addItem(Item i) {
        i.setPreviousItem(peek());
        items.add(i);
    }
    
    public Boolean isStoreValid() {
        return items.stream()
             .skip(1)
             .allMatch(this::itemvalid);
    }
    
    public Boolean itemvalid(Item i) {
        return i.calculateHash().equals(i.getHash()) && 
               i.getPreviousHash().equals(i.getPreviousItem().getHash());
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    

    
}