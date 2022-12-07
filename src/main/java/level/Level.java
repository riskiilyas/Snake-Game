package level;

import utils.Pair;

public abstract class Level {
    protected final int delay;
    protected final Pair<Integer, Integer>[] obstaclesXY;
    protected final int targetApples;
    protected final int level;

    public Level(int level, int delay, int targetApples, Pair<Integer, Integer>[] obstacles) {
        this.level = level;
        this.obstaclesXY = obstacles;
        this.targetApples = targetApples;
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public int getTargetApples() {
        return targetApples;
    }

    public int getLevel() {
        return level;
    }

    public Pair<Integer, Integer>[] getObstaclesXY() {
        return obstaclesXY;
    }
}
