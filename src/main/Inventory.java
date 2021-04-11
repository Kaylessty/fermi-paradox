package main;

/**
 * This class acts as an inventory for our user
 */
public class Inventory {
    // Fixed spelling for contesnts to contents
    private Item[] contents = new Item[30];
    private String name;
    private int currHousingSpace;
    private final int maximumCAPACITY = 20;

    /**
     * 1-arg constructor to create Inventory object
     * @param name String to be set to name instance var
     */
    public Inventory(String name) {
        this.name = name;
        currHousingSpace = 0;
    }

    /**
     * default constructor to create Inventory object
     */
    public Inventory() {
        this.name = "";
        currHousingSpace = 0;
    }

    /**
     * function to add an item to the player's inventory
     * @param toAdd Item to be added
     * @return boolean if was successful
     */
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

    /**
     * function to remove an item from the Inventory
     * @param ite Item to be removed
     */
    public void dropItem(Item ite) {
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] == ite) {
                Item cur = contents[i];
                contents[i] = null;
                currHousingSpace -= cur.getSize();
            }
        }
    }

    /**
     * getter for the Item at a particular index
     * @param index the index of the Item to return
     * @return the Item at index
     */
    public Item getItem(int index) {
        return contents[index];
    }

    /**
     * getter for the currHousingSpace instance var
     * @return the int value of currHousingSpace
     */
    public int getCurrHousingSpace() {
        return currHousingSpace;
    }

    /**
     * setter for currHousingSpace instance var
     * @param number the int value to be assigned
     */
    public void setCurrHousingSpace(int number) {
        currHousingSpace = number;
    }

    /**
     * getter for the contents instance var
     * @return the Item array pointed to by contents
     */
    public Item[] getContents() {
        return contents;
    }
}
