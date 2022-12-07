package screens;

import components.*;
import components.Button;
import sound.SoundManager;
import state.ScreenState;
import state.SoundState;
import utils.DataStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static utils.Commons.SCREEN_HEIGHT;
import static utils.Commons.SCREEN_WIDTH;

public class MenuScreen extends Screen {
    private final Image backgroundImg;
    private final MenuButton playBtn;
    private final MenuButton settingsBtn;
    private final MenuButton highScoreBtn;
    private final MenuButton quitBtn;
    private final Button infoBtn;
    private final DataStore dataStore;
    private final MouseListener menuMouseListener = new MenuMouseListener(this);

    public MenuScreen(ScreenRouter screenRouter, SoundManager soundManager, DataStore dataStore) {
        super(screenRouter, soundManager);
        this.dataStore = dataStore;
        backgroundImg = new ImageIcon("assets/bg.png").getImage();
        playBtn = new MenuButton("assets/playbtn.png", 100);
        infoBtn = new Button(SCREEN_WIDTH - 70, 10, SCREEN_WIDTH, 60, "assets/info.png");
        settingsBtn = new MenuButton("assets/settingsbtn.png", 200);
        highScoreBtn = new MenuButton("assets/highscorebtn.png", 300);
        quitBtn = new MenuButton("assets/quitbtn.png", 400);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(backgroundImg, 0, 0, null);
        g2d.drawImage(playBtn.getImage(), playBtn.getStartX(), playBtn.getStartY(), null);
        g2d.drawImage(settingsBtn.getImage(), settingsBtn.getStartX(), settingsBtn.getStartY(), null);
        g2d.drawImage(highScoreBtn.getImage(), highScoreBtn.getStartX(), highScoreBtn.getStartY(), null);
        g2d.drawImage(quitBtn.getImage(), quitBtn.getStartX(), quitBtn.getStartY(), null);
        g2d.drawImage(infoBtn.getImage(), infoBtn.getStartX(), infoBtn.getStartY(), null);
    }

    @Override
    public void stopScreen() {
        screenRouter.getPanel().removeMouseListener(menuMouseListener);
    }

    @Override
    public void startScreen() {
        screenRouter.getPanel().addMouseListener(menuMouseListener);
    }

    class MenuMouseListener implements MouseListener {
        private final MenuScreen screen;

        public MenuMouseListener(MenuScreen screen) {
            this.screen = screen;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (playBtn.checkClicked(x, y)) {
                soundManager.playSound(SoundState.BUTTON_CLICKED);
                screenRouter.route(ScreenState.LEVEL);
            } else if (settingsBtn.checkClicked(x, y)) {
                soundManager.playSound(SoundState.BUTTON_CLICKED);
                screenRouter.route(ScreenState.SETTINGS);
            } else if (highScoreBtn.checkClicked(x, y)) {
                soundManager.playSound(SoundState.BUTTON_CLICKED);
                JOptionPane.showMessageDialog(screen, "Your current High Score is " + dataStore.getHighScore(),
                        "High Score", JOptionPane.INFORMATION_MESSAGE);
            } else if (quitBtn.checkClicked(x, y)) {
                soundManager.playSound(SoundState.BUTTON_CLICKED);
                screenRouter.route(ScreenState.QUIT);
            } else if (infoBtn.checkClicked(x, y)) {
                JOptionPane.showMessageDialog(screen,
                        "Use Keyboar Arrow Buttons to change direction.The objective of the game is to eat the apples by approaching it. Game is over when" +
                                "\nplayer get out of the map, eat the snake's body, or crash into obstacle. You can\n" +
                                "also boost by holding SPACE BAR.\n\nvisit:\n" +
                                "www.riskiilyas.com\nhttps://github.com/riskiilyas/FP-OOP-Snake-Game",
                        "How To Play", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
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
}
