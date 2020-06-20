package com.mundoviventem.component.core;

import com.badlogic.gdx.Gdx;
import com.mundoviventem.component.core.input.InputHandlingDevice;

public class InputHandler extends BaseComponent
{
    private InputHandlingDevice inputHandlingDevice;

    @Override
    public void onEnable()
    {
        this.inputHandlingDevice = new InputHandlingDevice();
        Gdx.input.setInputProcessor(this.inputHandlingDevice);
    }

    @Override
    public void onDisable()
    {

    }

    @Override
    public void update()
    {
    }

    @Override
    public void gameObjectStartsSleeping()
    {

    }

    public InputHandlingDevice getInputHandlingDevice()
    {
        return this.inputHandlingDevice;
    }

    public void setInputHandlingDevice(InputHandlingDevice inputHandlingDevice)
    {
        this.inputHandlingDevice = inputHandlingDevice;
    }
}
