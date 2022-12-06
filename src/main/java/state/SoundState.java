package state;

public enum SoundState {
    APPLE_EATEN("assets/apple.wav"),
    BUTTON_CLICKED("assets/button_clicked.wav"),
    GAME_MUSIC("assets/gameplay.wav"),
    GAME_OVER("assets/game_over.wav"),
    GAME_SUCCESS("assets/win.wav");

    private final String filepath;

    SoundState(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }
}
