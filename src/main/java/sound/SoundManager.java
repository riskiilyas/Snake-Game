package sound;

import state.SoundState;
import utils.DataStore;

import java.util.HashMap;

import static state.SoundState.*;

public class SoundManager {
    private final HashMap<SoundState, Sound> sounds = new HashMap<>();
    protected DataStore dataStore;
    protected boolean isSoundOn;


    public SoundManager(DataStore dataStore) {
        this.dataStore = dataStore;
        sounds.put(GAME_MUSIC, new GameMusic());
        sounds.put(GAME_OVER, new Sound(GAME_OVER));
        sounds.put(GAME_SUCCESS, new Sound(GAME_SUCCESS));
        sounds.put(BUTTON_CLICKED, new Sound(BUTTON_CLICKED));
        sounds.put(APPLE_EATEN, new Sound(APPLE_EATEN));
        observeSound();
    }

    public void playSound(SoundState soundState) {
        if (!isSoundOn) return;
        Sound sound = sounds.get(soundState);
        sound.play();
    }

    private void observeSound() {
        GameMusic gameMusic = (GameMusic) sounds.get(GAME_MUSIC);
        dataStore.getSfxObservable().observe((isSoundOn) -> this.isSoundOn = isSoundOn);

        dataStore.getMusicObservable().observe((isMusicOn) -> {
            if (isMusicOn) {
                gameMusic.play();
            } else {
                gameMusic.stop();
            }
        });
    }
}
