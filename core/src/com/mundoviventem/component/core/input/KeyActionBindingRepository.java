package com.mundoviventem.component.core.input;

import com.badlogic.gdx.Input;

import java.util.ArrayList;

/**
 * Repository class for all key action bindings
 */
public class KeyActionBindingRepository
{

    private ArrayList<KeyActionBinding> keyActionBindings = new ArrayList<>();

    public KeyActionBindingRepository()
    {
        KeyActionBinding leonVonHintenNehmen = new KeyActionBinding("key_f");
        leonVonHintenNehmen.setKey(Input.Keys.F);
        leonVonHintenNehmen.setAction("Leon von hinten nehmen");
        leonVonHintenNehmen.setGameState("in_game");

        KeyActionBinding leonKastrieren = new KeyActionBinding("key_w");
        leonKastrieren.setKey(Input.Keys.W);
        leonKastrieren.setAction("Leon kastrieren");
        leonKastrieren.setGameState("in_game");

        KeyActionBinding mouse = new KeyActionBinding("mouse");
        mouse.setGameState("in_game");

        this.keyActionBindings.add(leonVonHintenNehmen);
        this.keyActionBindings.add(leonKastrieren);
        this.keyActionBindings.add(mouse);

    }

    /**
     * Returns all key action bindings
     *
     * @return ArrayList<KeyActionBinding>
     */
    public ArrayList<KeyActionBinding> getKeyActionBindings()
    {
        return this.keyActionBindings;
    }

    /**
     * Adds a new key action binding
     *
     * @param keyActionBinding = new KeyActionBinding
     */
    public void addKeyActionBinding(KeyActionBinding keyActionBinding)
    {
        this.keyActionBindings.add(keyActionBinding);
    }

    /**
     * Removes given key action binding
     *
     * @param keyActionBinding = KeyActionBinding that should be removed
     */
    public void removeKeyActionBinding(KeyActionBinding keyActionBinding)
    {
        this.keyActionBindings.remove(keyActionBinding);
    }

    /**
     * Sets the key action bindings
     *
     * @param keyActionBindings = The new key action bindings
     */
    public void setKeyActionBindings(ArrayList<KeyActionBinding> keyActionBindings)
    {
        this.keyActionBindings = keyActionBindings;
    }

    /**
     * Returns the to the key associated binding. If the key is not bound, null gets
     * returned
     *
     * @param key = The pressed keyboard key
     *
     * @return KeyActionBinding
     */
    public KeyActionBinding getKeyActionBindingByKey(int key)
    {
        for (KeyActionBinding keyActionBinding : this.keyActionBindings) {
            if(keyActionBinding.getKey() == key) {
                return keyActionBinding;
            }
        }
        return null;
    }

    /**
     * Returns the to the id associated binding. If the id is not bound, null gets
     * returned
     *
     * @param id = The id of the binding
     *
     * @return KeyActionBinding
     */
    public KeyActionBinding getKeyActionBindingById(String id)
    {
        for(KeyActionBinding keyActionBinding : this.keyActionBindings) {
            if(keyActionBinding.getId().equals(id)) {
                return keyActionBinding;
            }
        }
        return null;
    }
}
