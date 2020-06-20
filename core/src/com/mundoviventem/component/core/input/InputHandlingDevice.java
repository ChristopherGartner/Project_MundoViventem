package com.mundoviventem.component.core.input;

import com.badlogic.gdx.InputProcessor;
import com.mundoviventem.game.ManagerMall;

public class InputHandlingDevice implements InputProcessor
{
    @Override
    public boolean keyDown(int keycode)
    {
        KeyActionBinding keyActionBinding = ManagerMall.getKeyActionBindingRepository().getKeyActionBindingByKey(keycode);

        if(keyActionBinding != null) {
            keyActionBinding.setIsActive(true);
            System.out.println("Current Action: " + keyActionBinding.getAction());
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        KeyActionBinding keyActionBinding = ManagerMall.getKeyActionBindingRepository().getKeyActionBindingByKey(keycode);

        if(keyActionBinding != null) {
            keyActionBinding.setIsActive(false);
            System.out.println("Ending Action: " + keyActionBinding.getAction());
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}