package com.mundoviventem.component.core;

import com.badlogic.gdx.audio.Sound;
import com.mundoviventem.component.core.sound_manager.SoundConfiguration;
import com.mundoviventem.component.core.sound_manager.SoundRegistration;

import java.util.ArrayList;

/**
 * component for sound management
 * W.I.P (DO NOT USE CURRENTLY)
 */
public class SoundManager extends BaseComponent
{

    private ArrayList<SoundRegistration> soundRegistrations = new ArrayList<>();

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

    @Override
    public void gameObjectStartsSleeping()
    {
        this.soundRegistrations.forEach((soundRegistration -> {
            soundRegistration.getSound().stop();
        }));
    }

    @Override
    public void gameObjectAwakens()
    {
    }

    /**
     * Registers a new sound registration
     *
     * @param sound = the sound that should be registered as sound registration
     * @param name  = the name of the sound registration (should be unique. Otherwise errors can occur)
     * @param soundConfiguration = the sound configuration
     */
    public void registerNewSound(Sound sound, String name, SoundConfiguration soundConfiguration)
    {
        SoundRegistration soundRegistration = new SoundRegistration();
        soundRegistration.setSound(sound);
        soundRegistration.setName(name);
        soundRegistration.setSoundConfiguration(soundConfiguration);

        this.addDisposableResource(soundRegistration);
        this.soundRegistrations.add(soundRegistration);
    }

    /**
     * Plays the sound with the given configuration
     *
     * @param soundRegistration = The sound registration holding the sound and sound configuration
     */
    public void playSound(SoundRegistration soundRegistration)
    {

        long id = soundRegistration.getSound().play();
        soundRegistration.setSoundId(id);
        soundRegistration.getSound().setLooping(id, soundRegistration.getSoundConfiguration().isLooping());
        soundRegistration.getSound().setVolume(id, soundRegistration.getSoundConfiguration().getVolume());
        soundRegistration.getSound().setPan(id, soundRegistration.getSoundConfiguration().getPan(), soundRegistration.getSoundConfiguration().getVolume());
        soundRegistration.getSound().setPitch(id, soundRegistration.getSoundConfiguration().getPitch());
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
     * Returns specific sound registration. If registration cannot be found, null gets returned
     *
     * @param name = The name of the specific sound registration
     *
     * @return SoundRegistration
     */
    public SoundRegistration getSoundRegistration(String name)
    {
        for (SoundRegistration soundRegistration : this.getSoundRegistrations()) {
            if (soundRegistration.getName().equals(name)) {
                return soundRegistration;
            }
        }
        return null;
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
