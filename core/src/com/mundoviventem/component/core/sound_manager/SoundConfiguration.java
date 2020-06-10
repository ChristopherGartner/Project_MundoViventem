package com.mundoviventem.component.core.sound_manager;

/**
 * Resembles a specific sound configuration used to build sounds
 */
public class SoundConfiguration
{

    private boolean looping = false;
    private float volume    = 1.0f;
    private float pan       = 0f;
    private float pitch     = 1f;

    /**
     * Returns whether the sound is looping or not
     *
     * @return boolean
     */
    public boolean isLooping()
    {
        return this.looping;
    }

    /**
     * Sets whether the sound should loop or not
     *
     * @param looping = should the sound loop or not
     */
    public void setLooping(boolean looping)
    {
        this.looping = looping;
    }

    /**
     * Returns the volume of the sound
     *
     * @return float
     */
    public float getVolume()
    {
        return this.volume;
    }

    /**
     * Sets the volume of the sound
     *
     * @param volume = The volume of the sound
     */
    public void setVolume(float volume)
    {
        this.volume = volume;
    }

    /**
     * Returns the pan of the sound
     *
     * @return float
     */
    public float getPan()
    {
        return this.pan;
    }

    /**
     * Sets the pan of the sound
     *
     * @param pan = the pan of the sound
     */
    public void setPan(float pan)
    {
        this.pan = pan;
    }

    /**
     * Returns the pitch of the sound
     *
     * @return float
     */
    public float getPitch()
    {
        return this.pitch;
    }

    /**
     * Sets the pitch of the sound
     *
     * @param pitch = The pitch of the sound
     */
    public void setPitch(float pitch)
    {
        this.pitch = pitch;
    }
}
