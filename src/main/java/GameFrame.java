import utils.DataStore;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        DataStore dataStore = new DataStore();
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        dataStore.getPlayerNameObservable().observe((name) -> {
            this.setTitle("Snake Game " + ((name.isBlank()) ? "" : ("(" + name + ")")));
        });
    }
}
