package screens;

import components.BackButton;
import sound.SoundManager;
import state.LevelState;
import state.ScreenState;
import state.SoundState;
import utils.DataStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static utils.Commons.SCREEN_HEIGHT;
import static utils.Commons.SCREEN_WIDTH;

public class LevelScreen extends Screen implements MouseListener {
    private final Image backgroundImg;
    private final Button backBtn;
    private final Button casualBtn = new Button(SCREEN_WIDTH / 2 - 88, 80,
            SCREEN_WIDTH / 2 + 88, 145, "assets/level_casualbtn.png");
    private final Button[] levelbtns = new Button[10];
    private int currentLevel = 1;

    public LevelScreen(ScreenRouter screenRouter, SoundManager soundManager, DataStore dataStore) {
        super(screenRouter, soundManager);
        backgroundImg = new ImageIcon("assets/stagebg.png").getImage();
        backBtn = new BackButton();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        dataStore.getCurrentLevelObservable().observe((level) -> {
            currentLevel = level;
            createLevelButtons();
        });
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(backgroundImg, 0, 0, null);
        g2d.drawImage(backBtn.getImage(), backBtn.getStartX(), backBtn.getStartY(), null);
        g2d.drawImage(casualBtn.getImage(), casualBtn.getStartX(), casualBtn.getStartY(), null);
        for (int i = 0; i < 10; i++) {
            g2d.drawImage(levelbtns[i].getImage(), levelbtns[i].getStartX(), levelbtns[i].getStartY(), null);
        }
    }

    private void createLevelButtons() {
        int casualXStart = casualBtn.getStartX();
        int casualYEnd = casualBtn.getEndY();
        for (int i = 0; i < 5; i++) {
            String path = (i < currentLevel) ? "assets/level_level" + (i + 1) + "btn.png" : "assets/level_locked.png";
            levelbtns[i] = new Button(casualXStart - 175, casualYEnd + 10,
                    casualXStart, casualYEnd + 85, path);

            casualYEnd += 80;
        }

        int casualXEnd = casualBtn.getEndX();
        casualYEnd = casualBtn.getEndY();
        for (int i = 5; i < 10; i++) {
            String path = (i < currentLevel) ? "assets/level_level" + (i + 1) + "btn.png" : "assets/level_locked.png";
            levelbtns[i] = new Button(casualXEnd, casualYEnd + 10,
                    casualXEnd + 175, casualYEnd + 85, path);

            casualYEnd += 80;
        }

        repaint();
    }

    @Override
    public void stopScreen() {
        screenRouter.getPanel().removeMouseListener(this);
    }

    @Override
    public void startScreen() {
        screenRouter.getPanel().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (backBtn.checkClicked(x, y)) {
            soundManager.playSound(SoundState.BUTTON_CLICKED);
            screenRouter.route(ScreenState.MENU);
        } else if (casualBtn.checkClicked(x, y)) {
            soundManager.playSound(SoundState.BUTTON_CLICKED);
            screenRouter.routeToGame(LevelState.CASUAL);
        } else {
            for (int i = 0; i < 10; i++) {
                if (levelbtns[i].checkClicked(x, y)) {
                    if (i < currentLevel) {
                        screenRouter.routeToGame(LevelState.valueOf("L" + (i + 1)));
                        soundManager.playSound(SoundState.BUTTON_CLICKED);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
