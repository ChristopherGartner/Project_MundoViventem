package com.mundoviventem.game;

import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

/**
 * Handles disposing of classes
 * until "The Last of Us" get disposed
 */
public class DisposingManager
{

    private ArrayList<Disposable> disposables = new ArrayList<>();

    /**
     * Adds a new object that should get disposed.
     *
     * "Listen to me - if i get in trouble down there, you make every shot count. Yeah?"
     * - Joel -
     *
     * @param disposable = The objects that should get disposed
     */
    public void addNewDisposableObject(Disposable disposable)
    {
        this.disposables.add(disposable);
    }

    /**
     * Returns all disposable objects.
     *
     * "Everyone I have cared about for has either died, or left me. Everyone - fucking except you! So don't tell me i would
     * be safer with somebody else, because the truth is, I would be more scared."
     * - Ellie -
     *
     * @return ArrayList<Disposable>
     */
    public ArrayList<Disposable> getDisposableObjects()
    {
        return this.disposables;
    }

    /**
     * "I'm gonna find, and I'm gonna kill, every last one of them."
     * - Ellie -
     */
    public void killThemAll()
    {
        for (Disposable disposable : this.disposables) {
            disposable.dispose();
        }
    }
}
