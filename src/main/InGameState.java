package main;

import javafx.scene.input.KeyEvent;

public class InGameState {

    private InGameState(StateManager manage) {
        //not implemented in case of StateManager being useless
    }

    //To do: Actual movement: Do we want a Player Pos variable in terms of
    //Array or in terms of x/y coords?

    //Find a way to feed key into reader. Idea below
    scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
        keyReader(key);
    });

    private void keyReader(int key) {
        switch (key.getCode()) {
            case (A || LEFT):
                //Move left
                break;
            case (D || RIGHT):
                //Move right
                break;
            case (W || UP):
                //Move up
                break;
            case (S || DOWN):
                //Move down
                break;
        }
        //further keys can be added to this list for other actions in GameState
        //ex. attack, menu, interact
    }
}