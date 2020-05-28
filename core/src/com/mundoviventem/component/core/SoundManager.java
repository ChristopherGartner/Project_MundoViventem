package com.mundoviventem.component.core;

import com.badlogic.gdx.audio.Sound;
import com.mundoviventem.component.core.sound_manager.SoundRegistration;

import java.util.ArrayList;

/**
 * component for sound management
 * W.I.P (DO NOT USE CURRENTLY)
 */
public class SoundManager extends BaseComponent
{

    private ArrayList<SoundRegistration> soundRegistrations;

    @Override
    public void onEnable()
    {
        this.soundRegistrations = new ArrayList<>();
    }

    @Override
    public void onDisable()
    {

    }

    @Override
    public void update()
    {

    }

    /**
     * Registers a new sound registration
     *
     * @param sound = the sound that should be registered as sound registration
     */
    public void registerNewSound(Sound sound)
    {
        SoundRegistration soundRegistration = new SoundRegistration();
        soundRegistration.setSound(sound);

        this.addDisposableResource(soundRegistration);
        this.soundRegistrations.add(soundRegistration);
    }

    /**
     * Returns the list of sound registrations
     *
     * @return ArrayList<SoundRegistration>
     */
    public ArrayList<SoundRegistration> getSoundRegistrations()
    {
        return this.soundRegistrations;
    }

    /**
     * Removes the given sound registration from the list of sound registrations
     *
     * @param soundRegistrationToRemove = Sound registration that should get removed
     */
    public void removeRegistration(SoundRegistration soundRegistrationToRemove)
    {
        this.soundRegistrations.removeIf(soundRegistration -> soundRegistration.equals(soundRegistrationToRemove));
    }
}
