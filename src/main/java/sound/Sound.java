package sound;

import state.SoundState;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    protected Clip clip;
    protected SoundState soundState;

    public Sound(SoundState soundState) {
        this.soundState = soundState;
        File file = new File(soundState.getFilepath());
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception ignored) {
        }
    }

    public void play() {
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void stop() {
        clip.stop();
    }
}
