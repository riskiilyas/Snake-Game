package level;

import utils.Pair;

public class Level2 extends Level {

    public Level2() {
        super(2, 150, 10, new Pair[]{
                new Pair<>(11, 2),
                new Pair<>(10, 10),
                new Pair<>(11, 11),
                new Pair<>(10, 11),
                new Pair<>(11, 10),
                new Pair<>(12, 11),
        });
    }
}
