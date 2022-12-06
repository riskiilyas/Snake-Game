package sound;

import state.SoundState;

import javax.sound.sampled.Clip;

public class GameMusic extends Sound {

    private boolean isPlaying = false;

    public GameMusic() {
        super(SoundState.GAME_MUSIC);
    }

    @Override
    public void play() {
        if (isPlaying) return;
        isPlaying = true;
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        super.play();
    }

    @Override
    public void stop() {
        if (!isPlaying) return;
        super.stop();
        isPlaying = false;
    }
}
