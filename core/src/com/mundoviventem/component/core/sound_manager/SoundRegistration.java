package com.mundoviventem.component.core.sound_manager;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

/**
 * Class for registration definitions of sounds
 * Can be disposed
 * W.I.P
 */
public class SoundRegistration implements Disposable
{

    private String name;
    private Sound sound;
    private float soundId;
    SoundConfiguration soundConfiguration;
    private boolean playing = true;
    private boolean performedPlayingChange = true;

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

    /**
     * sets the name of the sound registration
     *
     * @param name =
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the name of the sound registration
     *
     * @return String
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Sets the id of the sound
     *
     * @param soundId = The id of the sound
     */
    public void setSoundId(float soundId)
    {
        this.soundId = soundId;
    }

    /**
     * Returns the id of the sound
     *
     * @return float
     */
    public float getSoundId()
    {
        return this.soundId;
    }

    /**
     * Sets the sound configuration
     *
     * @param soundConfiguration = The sound configuration
     */
    public void setSoundConfiguration(SoundConfiguration soundConfiguration)
    {
        this.soundConfiguration = soundConfiguration;
    }

    /**
     * Returns the sound configuration
     *
     * @return SoundConfiguration
     */
    public SoundConfiguration getSoundConfiguration()
    {
        return this.soundConfiguration;
    }

    /**
     * Sets whether the sound should play or not
     *
     * @param playing = Should the sound continue playing
     */
    public void setPlaying(boolean playing)
    {
        this.performedPlayingChange = false;
        this.playing = playing;
    }

    /**
     * Sets whether the playing change of the sound was performed or not
     *
     * @param performedPlayingChange = was the playing change performed
     */
    public void setPerformedPlayingChange(boolean performedPlayingChange)
    {
        this.performedPlayingChange = performedPlayingChange;
    }

    /**
     * Was the playing change performed?
     *
     * @return boolean
     */
    public boolean isPerformedPlayingChange()
    {
        return this.performedPlayingChange;
    }

    /**
     * Is the sound playing or not
     *
     * @return boolean
     */
    public boolean isPlaying()
    {
        return this.playing;
    }

    @Override
    public void dispose()
    {
        this.sound.dispose();
    }
}
