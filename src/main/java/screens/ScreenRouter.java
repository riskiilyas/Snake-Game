package screens;

import sound.SoundManager;
import state.LevelState;
import state.ScreenState;
import utils.DataStore;
import utils.Observable;

import javax.swing.*;
import java.awt.*;

public class ScreenRouter implements Observable.OnValueChangedListener<ScreenState> {
    private final Observable<ScreenState> screenState;
    private final SoundManager soundManager;
    private final JPanel panel;
    private final CardLayout layout;
    private GameScreen gameScreen;
    private MenuScreen menuScreen;
    private SettingsScreen settingsScreen;
    private LevelScreen levelScreen;
    private final DataStore dataStore;
    private LevelState levelState;

    public ScreenRouter(JFrame window, DataStore dataStore) {
        this.dataStore = dataStore;
        layout = new CardLayout();
        panel = new JPanel(layout);
        soundManager = new SoundManager(dataStore);
        window.add(panel);
        initScreens();
        screenState = new Observable<>(ScreenState.MENU);
        screenState.observe(this);
    }

    public void route(ScreenState state) {
        screenState.setValue(state);
    }

    public void route(LevelState levelState) {
        this.levelState = levelState;
        screenState.setValue(ScreenState.GAME);
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void onValueChanged(ScreenState value) {
        screenChange();
        switch (value) {
            case MENU -> {
                menuScreen.startScreen();
                layout.show(panel, ScreenState.MENU.name());
            }
            case GAME -> {
                gameScreen.startScreen(levelState);
                layout.show(panel, ScreenState.GAME.name());
            }
            case LEVEL -> {
                levelScreen.startScreen();
                layout.show(panel, ScreenState.LEVEL.name());
            }
            case SETTINGS -> {
                settingsScreen.startScreen();
                layout.show(panel, ScreenState.SETTINGS.name());
            }
            case QUIT -> System.exit(0);
        }
    }

    private void screenChange() {
        gameScreen.stopScreen();
        menuScreen.stopScreen();
        levelScreen.stopScreen();
        settingsScreen.stopScreen();
    }

    private void initScreens() {
        gameScreen = new GameScreen(this, soundManager, dataStore);
        menuScreen = new MenuScreen(this, soundManager, dataStore);
        levelScreen = new LevelScreen(this, soundManager, dataStore);
        settingsScreen = new SettingsScreen(this, soundManager, dataStore);
        panel.setFocusable(true);
        panel.add(menuScreen, ScreenState.MENU.name());
        panel.add(gameScreen, ScreenState.GAME.name());
        panel.add(levelScreen, ScreenState.LEVEL.name());
        panel.add(settingsScreen, ScreenState.SETTINGS.name());
    }
}
