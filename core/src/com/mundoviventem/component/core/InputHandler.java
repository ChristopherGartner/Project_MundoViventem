package com.mundoviventem.component.core;

import com.badlogic.gdx.Gdx;
import com.mundoviventem.component.core.input.InputHandlingDevice;

/**
 * Component class for input handling
 */
public class InputHandler extends BaseComponent
{
    private InputHandlingDevice inputHandlingDevice;
    private boolean isActiveInputHandler = false;

    @Override
    public void onEnable()
    {
        this.inputHandlingDevice = new InputHandlingDevice();
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

    /**
     * Returns whether this InputHandler instance is the actively used one
     *
     * @return boolean
     */
    public boolean isActiveInputHandler()
    {
        return this.isActiveInputHandler;
    }

    /**
     * Returns the InputHandlingDevice of this InputHandler
     *
     * @return InputHandlingDevice
     */
    public InputHandlingDevice getInputHandlingDevice()
    {
        return this.inputHandlingDevice;
    }

    /**
     * Sets the InputHandlingDevice of this InputHandler
     *
     * @param inputHandlingDevice = new InputHandlingDevice
     */
    public void setInputHandlingDevice(InputHandlingDevice inputHandlingDevice)
    {
        this.inputHandlingDevice = inputHandlingDevice;
    }

    /**
     * Sets this InputHandler to the active InputHandler.
     * Note: Its extremely dangerous to work with multiple
     * InputHandlers. So it is important to set all other InputHandler's
     * to inactive before calling this method, as otherwise inconsistencies may happen
     */
    public void setToActiveInputHandler()
    {
        Gdx.input.setInputProcessor(this.inputHandlingDevice);
        this.isActiveInputHandler = true;
    }

    /**
     * Sets this InputHandler instance inactive
     */
    public void setToInactiveInputHandler()
    {
        if(Gdx.input.getInputProcessor().equals(this.getInputHandlingDevice())) {
            Gdx.input.setInputProcessor(null);
        }
        this.isActiveInputHandler = false;
    }
}
