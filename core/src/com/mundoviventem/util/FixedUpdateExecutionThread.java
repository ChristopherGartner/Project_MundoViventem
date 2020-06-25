package com.mundoviventem.util;

import com.mundoviventem.game.ManagerMall;

/**
 * Thread which performs the fixed updateUniforms steps
 */
public class FixedUpdateExecutionThread extends Thread
{
    private double millisecondsPerTick;
    private UpdateExecutor updateExecutor;

    /**
     * Constructs the thread
     */
    public FixedUpdateExecutionThread()
    {
        super();

        // 1000 milliseconds / number of ticks per second
        millisecondsPerTick = ((double) 1000) / ((double) UpdateExecutor.NUMBER_OF_TICKS);
        this.updateExecutor = ManagerMall.getUpdateExecutor();
    }

    /**
     * Returns the milliseconds that are allowed per tick
     *
     * @return double
     */
    public double getMillisecondsPerTick()
    {
        return this.millisecondsPerTick;
    }

    @Override
    public void run()
    {
        Printer.print("Starting Thread 'FixedUpdateExecutionThread'", Printer.Printing_State.RAW);
        while (true) {
            long start = System.currentTimeMillis();
            this.updateExecutor.fixedUpdate();
            long elapsedTime = System.currentTimeMillis() - start;

            Printer.print("Fixed updateUniforms calculation took " + elapsedTime + " milliseconds", Printer.Printing_State.DEBUG);
            if(elapsedTime < this.millisecondsPerTick) {
                try {
                    long timeToSleep = ((long) this.millisecondsPerTick) - elapsedTime;
                    Printer.print("Thread will sleep " + timeToSleep + " milliseconds because execution time was under " + this.getMillisecondsPerTick() + " milliseconds", Printer.Printing_State.DEBUG);
                    Thread.sleep(timeToSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if(elapsedTime > this.millisecondsPerTick) {
                try {
                    long timeToSleep = (((long) this.millisecondsPerTick) * 2) - elapsedTime;
                    Printer.print("Thread will sleep " + timeToSleep + " milliseconds because execution time was above " + this.getMillisecondsPerTick() + " milliseconds", Printer.Printing_State.DEBUG);
                    Thread.sleep(timeToSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
