package com.mundoviventem.component.core.sound_manager;

import com.badlogic.gdx.audio.Sound;

/**
 * Data class for sound builds of registrations build for new sounds
 */
public class SoundBuild
{

    private String registrationName;
    private SoundConfiguration soundConfiguration;
    private Sound sound;

    /**
     * Constructs SoundBuild
     *
     * @param registrationName  = The name of the registration build
     * @param soundConfiguration = The configuration build options
     * @param sound             = The sound of the registration build
     */
    public SoundBuild(String registrationName, SoundConfiguration soundConfiguration, Sound sound)
    {
        this.registrationName   = registrationName;
        this.soundConfiguration = soundConfiguration;
        this.sound              = sound;
    }

    /**
     * Sets the registration build name
     *
     * @param registrationName = The name of the registration build
     */
    public void setRegistrationName(String registrationName)
    {
        this.registrationName = registrationName;
    }

    /**
     * Returns the registration build name
     *
     * @return String
     */
    public String getRegistrationName()
    {
        return this.registrationName;
    }

    /**
     * Sets the sound registration options
     *
     * @param soundConfiguration = The sound registration options
     */
    public void setSoundConfiguration(SoundConfiguration soundConfiguration)
    {
        this.soundConfiguration = soundConfiguration;
    }

    /**
     * Returns the sound registration options
     *
     * @return SoundConfiguration
     */
    public SoundConfiguration getSoundConfiguration()
    {
        return this.soundConfiguration;
    }

    /**
     * Sets the sound of the registration build
     *
     * @param sound = Sound of the registration
     */
    public void setSound(Sound sound)
    {
        this.sound = sound;
    }

    /**
     * Returns the sound of the registration build
     *
     * @return Sound
     */
    public Sound getSound()
    {
        return this.sound;
    }

}
