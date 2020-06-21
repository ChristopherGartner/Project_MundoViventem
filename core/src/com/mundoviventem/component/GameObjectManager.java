package com.mundoviventem.component;

import com.mundoviventem.component.core.SoundManager;
import com.mundoviventem.component.core.sound_manager.SoundRegistration;
import com.mundoviventem.component.game_objects.GameObject;
import com.mundoviventem.game.ManagerMall;
import com.mundoviventem.states.GameState;

import java.util.ArrayList;

/**
 * Manages all instantiated game objects
 */
public class GameObjectManager
{
    /**
     * Handles sound playing for the game objects
     */
    private void playSounds(ArrayList<GameObject> instantiatedGameObjects)
    {
        instantiatedGameObjects.forEach((gameObject -> {
            if(!gameObject.isGameObjectSleeping()) {
                if(gameObject.hasComponent(SoundManager.class)) {
                    SoundManager soundManager = (SoundManager) gameObject.getComponentFromClass(SoundManager.class);
                    for (SoundRegistration soundRegistration : soundManager.getSoundRegistrations()
                         ) {
                        // If sound is playing, but the playing change wasn't performed yet
                        if(soundRegistration.isPlaying() && !(soundRegistration.isPerformedPlayingChange())) {
                            soundManager.playSound(soundRegistration);
                            soundRegistration.setPerformedPlayingChange(true);
                        // If sound isn't playing, but the playing change wasn't performed yet
                        } else if(!(soundRegistration.isPlaying()) && !(soundRegistration.isPerformedPlayingChange())) {
                            soundRegistration.getSound().stop();
                            soundRegistration.setPerformedPlayingChange(true);
                        }
                    }
                }
            }
        }));
    }

    /**
     * performs updates per tick
     */
    public void updateInstantiatedGameObjects(ArrayList<GameObject> instantiatedGameObjects)
    {
        instantiatedGameObjects.forEach((gameObject) -> {
            if(!gameObject.isGameObjectSleeping()) {
                gameObject.updateGameObject();
                //System.out.println("Update successfully for GameObject with the name '" + gameObject.getName() + "' and the UUID '" + gameObject.getGameObjectUUID() + "'");
            }
        });

        this.playSounds(instantiatedGameObjects);
    }

    /**
     * Passthrough method to call rendering
     *
     * @param gameState = The game state whose game state renderer should do the job
     */
    public void callRender(GameState gameState){
        ManagerMall.getRenderManager().renderObjects(gameState.getGameStateRenderer().getRenderSequence());
    }
}
