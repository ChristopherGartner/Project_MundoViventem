package com.mundoviventem.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mundoviventem.component.core.input.KeyActionBinding;
import com.mundoviventem.game.ManagerMall;

import java.util.ArrayList;

/**
 * Handles the game updates
 */
public class UpdateExecutor
{

    /**
     * Declares how many ticks are allowed per second
     */
    public final static int NUMBER_OF_TICKS = 120;

    /**
     * Starts the fixed updateUniforms thread
     */
    public void startFixedUpdateThread()
    {

        FixedUpdateExecutionThread fixedUpdateExecutionThread = new FixedUpdateExecutionThread();
        fixedUpdateExecutionThread.start();

    }

    /**
     * Performs an fixed updateUniforms. A fixed updateUniforms has a specified execute-sleep algorithm determined
     * by the numbers of ticks. This method should only get called by the FixedUpdateExecutionThread
     */
    public void fixedUpdate()
    {
        Printer.print("Performing fixed updateUniforms", Printer.Printing_State.DEBUG);
    }

    /**
     * Performs a single updateUniforms. This updates are dependent on the frames per second
     */
    public void update()
    {
        Printer.print("Performing updateUniforms", Printer.Printing_State.DEBUG);

        ArrayList<KeyActionBinding> keyActionBindings = ManagerMall.getKeyActionBindingRepository().getKeyActionBindings();

        for(KeyActionBinding keyActionBinding : keyActionBindings) {
            if(keyActionBinding.isActive()) {
                if(keyActionBinding.getAction().equals("Leon von hinten nehmen")) {
                    Printer.print("(Action: Leon von hinten nehmen) Leon schreit wie eine wild gewordene Stute!", Printer.Printing_State.DETAILED);
                }
                if(keyActionBinding.getAction().equals("Leon kastrieren")) {
                    Printer.print("(Action: Leon kastrieren) FATAL_ERROR - NULL_POINTER_EXCEPTION: Kein Schwanz gefunden!", Printer.Printing_State.DETAILED);
                }
            }
        }

        // Calls updateUniforms and render of the GameStateManager
        ManagerMall.getGameStateManager().updateTopState();
    }

    /**
     * Gets performed after each updateUniforms call
     */
    public void lateUpdate()
    {
        Printer.print("Performing late updateUniforms", Printer.Printing_State.DEBUG);

        ManagerMall.getGameStateManager().renderTopState();
    }

    /**
     * Returns the current delta time
     *
     * @return float
     */
    public static float getDeltaTime()
    {
        return Gdx.graphics.getDeltaTime();
    }
}
