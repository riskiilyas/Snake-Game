package screens;

import components.MenuButton;
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

public class SettingsScreen extends Screen implements MouseListener {
    private final Image backgroundImg;
    private final MenuButton cpButton;
    private MenuButton sfxButton;
    private MenuButton musicButton;
    private final MenuButton backButton;
    private final MenuButton resetBtn;
    private final DataStore dataStore;

    public SettingsScreen(ScreenRouter screenRouter, SoundManager soundManager, DataStore dataStore) {
        super(screenRouter, soundManager);
        this.dataStore = dataStore;
        backgroundImg = new ImageIcon("assets/settingsbg.png").getImage();
        cpButton = new MenuButton("assets/cpbtn.png", 70);
        sfxButton = new MenuButton("assets/sfxonbtn.png", 170);
        musicButton = new MenuButton("assets/musiconbtn.png", 270);
        resetBtn = new MenuButton("assets/resetbtn.png", 370);
        backButton = new MenuButton("assets/backbtn.png", 470);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        dataStore.getMusicObservable().observe((isOn) -> {
            if (isOn) {
                musicButton = new MenuButton("assets/musiconbtn.png", 270);
            } else {
                musicButton = new MenuButton("assets/musicoffbtn.png", 270);
            }
            repaint();
        });

        dataStore.getSfxObservable().observe((isOn) -> {
            if (isOn) {
                sfxButton = new MenuButton("assets/sfxonbtn.png", 170);
            } else {
                sfxButton = new MenuButton("assets/sfxoffbtn.png", 170);
            }
            repaint();
        });
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(backgroundImg, 0, 0, null);
        g2d.drawImage(cpButton.getImage(), cpButton.getStartX(), cpButton.getStartY(), null);
        g2d.drawImage(sfxButton.getImage(), sfxButton.getStartX(), sfxButton.getStartY(), null);
        g2d.drawImage(musicButton.getImage(), musicButton.getStartX(), musicButton.getStartY(), null);
        g2d.drawImage(resetBtn.getImage(), resetBtn.getStartX(), resetBtn.getStartY(), null);
        g2d.drawImage(backButton.getImage(), backButton.getStartX(), backButton.getStartY(), null);
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
        int x = e.getX();
        int y = e.getY();
        if (backButton.checkClicked(x, y)) {
            soundManager.playSound(SoundState.BUTTON_CLICKED);
            screenRouter.route(ScreenState.MENU);
        } else if (sfxButton.checkClicked(x, y)) {
            dataStore.toggleSfx();
            soundManager.playSound(SoundState.BUTTON_CLICKED);
        } else if (musicButton.checkClicked(x, y)) {
            dataStore.toggleMusic();
            soundManager.playSound(SoundState.BUTTON_CLICKED);
        } else if (cpButton.checkClicked(x, y)) {
            soundManager.playSound(SoundState.BUTTON_CLICKED);
            String playername = JOptionPane
                    .showInputDialog("Current name: " + dataStore.getPlayerNameObservable().getValue() + "\nChange Playername:");
            if (playername != null && !playername.contains(":")) {
                dataStore.setPlayerName(playername);
            }
        } else if (resetBtn.checkClicked(x, y)) {
            soundManager.playSound(SoundState.BUTTON_CLICKED);
            dataStore.resetData();
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
