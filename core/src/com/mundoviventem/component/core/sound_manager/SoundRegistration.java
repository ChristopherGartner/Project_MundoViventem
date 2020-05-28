package com.mundoviventem.component.core.sound_manager;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

/**
 * Class for registration definitions of sounds
 * W.I.P
 */
public class SoundRegistration implements Disposable
{

    private Sound sound;

    /**
     * Sets the sound object for the given registration.
     * For creating the sound object, the best way is to
     * do it with "Gdx.audio.newSound(Gdx.files.internal(<path of sound element>))"
     *
     * @param sound = The sound of the given registration
     */
    public void setSound(Sound sound)
    {
        this.sound = sound;
    }

    /**
     * Returns the sound object of the given registration
     *
     * @return Sound
     */
    public Sound getSound()
    {
        return this.sound;
    }

    @Override
    public void dispose()
    {
        this.sound.dispose();
    }
}
