package components;

import static utils.Commons.SCREEN_WIDTH;

public class MenuButton extends Button {

    public MenuButton(String imgSource, int startY) {
        super(SCREEN_WIDTH / 2 - 110, startY, SCREEN_WIDTH / 2 + 110, startY + 80, imgSource);
    }
}