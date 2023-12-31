package main;

public class Inventory {
    // Fixed spelling for contesnts to contents
    private Item[] contents = new Item[30];
    private String name;
    private int currHousingSpace;
    private final int maximumCAPACITY = 20;

    public Inventory(String name) {
        this.name = name;
        currHousingSpace = 0;
    }
    public Inventory() {
        this.name = "";
        currHousingSpace = 0;
    }

    public boolean addItem(Item toAdd) {
        if (toAdd.getSize() + currHousingSpace <= maximumCAPACITY) {
            for (int i = 0; i < contents.length; i++) {
                if (contents[i] == null) {
                    contents[i] = toAdd;
                    break;
                }
            }
            currHousingSpace += toAdd.getSize();
            return true;
        } else {
            System.out.println("There's not enough inventory space to add this item. ");
            return false;
        }
    }

    public void dropItem(Item ite) {
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] == ite) {
                Item cur = contents[i];
                contents[i] = null;
                currHousingSpace -= cur.getSize();
            }
        }
    }
    public Item getItem(int index) {
        return contents[index];
    }

    public int getCurrHousingSpace() {
        return currHousingSpace;
    }

    public void setCurrHousingSpace(int number) {
        currHousingSpace = number;
    }

    public Item[] getContents() {
        return contents;
    }

    public int getMaximumCapacity() {
        return maximumCAPACITY;
    }
}
