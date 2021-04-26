package main;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.control.RadioButton;

public class CustomItemButton extends RadioButton {
    private Item crucial;

    public void setItem(Item crucial) {
        this.crucial = crucial;
    }

    public Item getItem() {
        return crucial;
    }
}
