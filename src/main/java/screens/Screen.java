package screens;

import sound.SoundManager;

import javax.swing.*;

public abstract class Screen extends JPanel {
    protected ScreenRouter screenRouter;
    protected SoundManager soundManager;

    public Screen(ScreenRouter screenRouter, SoundManager soundManager) {
        this.screenRouter = screenRouter;
        this.soundManager = soundManager;
    }

    public abstract void stopScreen();

    public abstract void startScreen();
}
