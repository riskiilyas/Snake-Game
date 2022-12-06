package screens;

import sound.SoundManager;

import javax.swing.*;

public abstract class Screen extends JPanel {
    protected SoundManager soundManager;

    public Screen(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    public abstract void stopScreen();

    public abstract void startScreen();
}
