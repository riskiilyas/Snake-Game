package screens;

import level.*;
import sound.SoundManager;
import state.Direction;
import state.LevelState;
import state.ScreenState;
import state.SoundState;
import utils.DataStore;
import utils.Pair;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.*;

import static utils.Commons.*;

public class GameScreen extends Screen implements ActionListener {
    Direction direction = Direction.RIGHT;
    private final Queue<Pair<Pair<Integer, Integer>, Direction>> snake = new ArrayDeque<>();
    private Pair<Pair<Integer, Integer>, Direction> head = new Pair<>(new Pair<>(5, 1), direction);
    private final Queue<Direction> pendingDirection = new LinkedList<>();
    private final MyKeyAdapter gameKeyAdapter = new MyKeyAdapter();
    private final Image backgroundImg;
    private int bodyParts = 6;
    private int applesEaten;
    int appleX;
    int appleY;
    boolean running = false;
    Timer timer;
    Random random;
    private Level playingLevel;
    private final DataStore dataStore;
    private int gameDelay = DELAY;

    public GameScreen(ScreenRouter screenRouter, SoundManager soundManager, DataStore dataStore) {
        super(screenRouter, soundManager);
        this.dataStore = dataStore;
        random = new Random();
        backgroundImg = new ImageIcon("assets/bg3.png").getImage();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);
    }

    public void startGame() {
        snake.clear();
        bodyParts = 6;
        applesEaten = 0;
        direction = Direction.RIGHT;
        for (int i = 0; i < bodyParts - 1; i++) {
            snake.add(new Pair<>(new Pair<>(i, 1), direction));
        }
        head.first.first = 5;
        head.first.second = 1;
        snake.add(head);
        newApple();
        this.grabFocus();
        this.setFocusable(true);
        running = true;
        gameDelay = (playingLevel == null) ? DELAY : playingLevel.getDelay();
        timer = new Timer(gameDelay, this);
        informObjective();
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, null);

        g.drawImage(new ImageIcon("assets/apple.png").getImage(), appleX * UNIT_SIZE, appleY * UNIT_SIZE, null);

        int i = 0;
        int tailX = 0, tailY = 0;
        for (var xy : snake) {
            if (i == 0) {
                tailX = xy.first.first;
                tailY = xy.first.second;
            } else if (i == 1) {
                g.drawImage(new ImageIcon("assets/tail_" + xy.second.name().toLowerCase() + ".png").getImage(),
                        tailX * UNIT_SIZE, tailY * UNIT_SIZE, null
                );
                g.drawImage(new ImageIcon("assets/body" + (i % 2) + ".png").getImage(),
                        xy.first.first * UNIT_SIZE, xy.first.second * UNIT_SIZE, null);
            } else if (xy.compareWith(head)) {
                g.drawImage(new ImageIcon("assets/head_" + xy.second.name().toLowerCase() + ".png").getImage(),
                        xy.first.first * UNIT_SIZE, xy.first.second * UNIT_SIZE, null
                );
            } else {
                g.drawImage(new ImageIcon("assets/body" + (i % 2) + ".png").getImage(),
                        xy.first.first * UNIT_SIZE, xy.first.second * UNIT_SIZE, null);
            }
            i++;
        }

        if (playingLevel != null) {
            for (var xy : playingLevel.getObstaclesXY()) {
                g.drawImage(new ImageIcon("assets/obstacle.png").getImage(), xy.first * UNIT_SIZE,
                        xy.second * UNIT_SIZE, null);
            }
        }
    }

    public void newApple() {
        appleX = random.nextInt(UNIT_SIZE - 2);
        appleY = random.nextInt(UNIT_SIZE - 2);
        if (appleX == 0) appleX++;
        if (appleY == 0) appleY++;

        if (playingLevel != null) {
            for (var xy : playingLevel.getObstaclesXY()) {
                if (appleX == xy.first && appleY == xy.second) {
                    newApple();
                    return;
                }
            }
        }

        for (var part : snake) {
            if (part.first.first == appleX && part.first.second == appleY) {
                newApple();
                return;
            }
        }
    }

    public void move() {
        if (snake.size() == bodyParts) snake.poll();
        Direction newDirection = pendingDirection.poll();
        if (newDirection != null) direction = newDirection;

        switch (direction) {
            case UP -> head = new Pair<>(new Pair<>(head.first.first, head.first.second - 1), direction);
            case DOWN -> head = new Pair<>(new Pair<>(head.first.first, head.first.second + 1), direction);
            case LEFT -> head = new Pair<>(new Pair<>(head.first.first - 1, head.first.second), direction);
            case RIGHT -> head = new Pair<>(new Pair<>(head.first.first + 1, head.first.second), direction);
            default -> {
            }
        }
        snake.add(head);
        checkWin();
    }

    public void checkApple() {
        if (head.first.first == appleX && head.first.second == appleY) {
            bodyParts++;
            applesEaten++;
            soundManager.playSound(SoundState.APPLE_EATEN);
            newApple();
        }
    }

    public void checkCollision() {
        int i = 0;
        if (playingLevel != null) {
            for (var xy : playingLevel.getObstaclesXY()) {
                if (Objects.equals(head.first.first, xy.first) && Objects.equals(head.first.second, xy.second)) {
                    running = false;
                    break;
                }
            }
        }

        for (var part : snake) {
            if (i < bodyParts - 2 && part.first.compareWith(head.first)) {
                running = false;
            }
            i++;
        }
        if (head.first.first < 1) {
            running = false;
        }

        if (head.first.first > UNIT_SIZE - 3) {
            running = false;
        }

        if (head.first.second < 1) {
            running = false;
        }

        if (head.first.second > UNIT_SIZE - 3) {
            running = false;
        }

        if (!running) {
            timer.stop();
            repaint();
            if (playingLevel == null && dataStore.isHighScore(applesEaten)) {
                soundManager.playSound(SoundState.GAME_SUCCESS);
                JOptionPane.showMessageDialog(this, "Your score is " + applesEaten,
                        "New High Score!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                soundManager.playSound(SoundState.GAME_OVER);
                JOptionPane.showMessageDialog(this, "Score = " + applesEaten,
                        "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }
            screenRouter.route(ScreenState.LEVEL);
        }
    }

    @Override
    public void stopScreen() {
        screenRouter.getPanel().removeKeyListener(gameKeyAdapter);
    }

    @Override
    public void startScreen() {
    }

    public void startScreen(LevelState levelState) {
        getLevel(levelState);
        screenRouter.getPanel().addKeyListener(gameKeyAdapter);
        startGame();
    }

    private void getLevel(LevelState levelState) {
        switch (levelState) {
            case L1 -> playingLevel = new Level1();
            case L2 -> playingLevel = new Level2();
            case L3 -> playingLevel = new Level3();
            case L4 -> playingLevel = new Level4();
            case L5 -> playingLevel = new Level5();
            case L6 -> playingLevel = new Level6();
            case L7 -> playingLevel = new Level7();
            case L8 -> playingLevel = new Level8();
            case L9 -> playingLevel = new Level9();
            case L10 -> playingLevel = new Level10();
            case CASUAL -> playingLevel = null;
        }
    }

    private void informObjective() {
        if (playingLevel == null) return;
        JOptionPane.showMessageDialog(this, "Get score " + playingLevel.getTargetApples() + " to win!",
                "LV. " + playingLevel.getLevel() + " Game Objective", JOptionPane.INFORMATION_MESSAGE);
    }

    private void checkWin() {
        if (playingLevel == null) return;
        if (playingLevel.getTargetApples() == applesEaten) {
            timer.stop();
            soundManager.playSound(SoundState.GAME_SUCCESS);
            JOptionPane.showMessageDialog(this, "Level " + playingLevel.getLevel() + " Completed!",
                    "Congrats " + dataStore.getPlayerNameObservable().getValue() + "!", JOptionPane.INFORMATION_MESSAGE);
            screenRouter.route(ScreenState.LEVEL);
            dataStore.upLevel(playingLevel.getLevel());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (!running) return;
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                timer.setDelay(gameDelay);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (!running) return;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != Direction.RIGHT) {
                        pendingDirection.add(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != Direction.LEFT) {
                        pendingDirection.add(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != Direction.DOWN) {
                        pendingDirection.add(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != Direction.UP) {
                        pendingDirection.add(Direction.DOWN);
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    timer.setDelay(gameDelay / 2);
            }


        }


    }
}
