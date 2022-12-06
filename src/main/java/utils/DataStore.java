package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataStore {

    private final static String IS_MUSIC_ON = "ismusicon";
    private final static String IS_SFX_ON = "issfxon";
    private final static String PLAYER_NAME = "playername";
    private final static String CURRENT_LEVEL = "currentlevel";
    private final static String HIGHSCORE = "highscore";
    private final static String FILENAME = "data.txt";

    private final Observable<Boolean> isMusicOn = new Observable<>(true);
    private final Observable<Boolean> isSfxOn = new Observable<>(true);
    private final Observable<String> playerName = new Observable<>("Player 1");
    private final Observable<Integer> currentLevel = new Observable<>(1);
    private final Observable<Integer> highscores = new Observable<>(0);
    private final File data = new File(FILENAME);

    public DataStore() {
        getData();
    }

    private void getData() {
        try {
            Scanner dataScanner = new Scanner(data);
            while (dataScanner.hasNextLine()) {
                String[] keyvalue = dataScanner.nextLine().split(":");
                switch (keyvalue[0]) {
                    case IS_MUSIC_ON -> isMusicOn.setValue((Integer.parseInt(keyvalue[1]) == 1));
                    case IS_SFX_ON -> isSfxOn.setValue((Integer.parseInt(keyvalue[1]) == 1));
                    case PLAYER_NAME -> playerName.setValue(keyvalue[1]);
                    case CURRENT_LEVEL -> currentLevel.setValue(Integer.valueOf(keyvalue[1]));
                    case HIGHSCORE -> highscores.setValue(Integer.valueOf(keyvalue[1]));
                }
            }
            dataScanner.close();
        } catch (Exception ignored) {}
    }

    // Write data using new Thread
    private void writeData() {
        new Thread(() -> {
            try {
                FileWriter fileWriter = new FileWriter(data);
                PrintWriter printWriter = new PrintWriter(fileWriter, false);

                printWriter.println(IS_MUSIC_ON + ":" + (isMusicOn.getValue() ? 1 : 0));
                printWriter.println(IS_SFX_ON + ":" + (isSfxOn.getValue() ? 1 : 0));
                printWriter.println(PLAYER_NAME + ":" + playerName.getValue());
                printWriter.println(CURRENT_LEVEL + ":" + currentLevel.getValue());
                printWriter.println(HIGHSCORE + ":" + highscores.getValue());
                printWriter.close();
            } catch (IOException ignored) {
            }
        }).start();
    }

    public Observable<Boolean> getMusicObservable() {
        return isMusicOn;
    }

    public void toggleMusic() {
        isMusicOn.setValue(!isMusicOn.getValue());
        writeData();
    }

    public Observable<Boolean> getSfxObservable() {
        return isSfxOn;
    }

    public void toggleSfx() {
        isSfxOn.setValue(!isSfxOn.getValue());
        writeData();
    }

    public Observable<String> getPlayerNameObservable() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName.setValue(playerName);
        writeData();
    }

    public Observable<Integer> getCurrentLevelObservable() {
        return currentLevel;
    }

    public void upLevel(int level) {
        int levelNow = currentLevel.getValue();
        if (level < levelNow) return;
        currentLevel.setValue(levelNow + 1);
        writeData();
    }

    public boolean isHighScore(int score) {
        if (highscores.getValue() < score) {
            highscores.setValue(score);
            writeData();
            return true;
        }
        return false;
    }

    public int getHighScore() {
        return highscores.getValue();
    }

    public void resetData() {
        isMusicOn.setValue(true);
        isSfxOn.setValue(true);
        playerName.setValue("Player 1");
        currentLevel.setValue(1);
        highscores.setValue(0);
        writeData();
    }
}
