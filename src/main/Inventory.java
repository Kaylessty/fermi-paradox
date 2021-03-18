package main;

public class Inventory {
    private Item[] contesnts = new Item[8];
    private String name;

    public Inventory(String name) {
        this.name = name;
    }

    public void addItem(Item toAdd) {
        for(int i = 0; i < contesnts.length; i++) {
            if(contesnts[i] == null) {
                contesnts[i] = toAdd;
                return;
            }
        }
    }

    public Item getItem(int index) {
        return contesnts[index];
    }
}
