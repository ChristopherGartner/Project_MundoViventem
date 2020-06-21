package com.mundoviventem.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Printer
{
    /**
     * The different printing states:
     *
     * RAW: only for generic prints that should get printed every time
     * DETAILED: For more detailed prints. Should't get printed every time
     * DEBUG: For very detailed prints.
     * OFF: Prints are deactivated
     */
     public static enum  Printing_State {
         RAW, DETAILED, DEBUG, OFF
     }

     private static Printing_State currentPrintingState = Printing_State.DETAILED;

     public static Printing_State getCurrentPrintingState()
     {
         return Printer.currentPrintingState;
     }

     public static void setCurrentPrintingState(Printing_State printingState)
     {
         Printer.currentPrintingState = printingState;
     }

    /**
     * Method for printing the given text (if the printing state is achieved)
     *
     * @param stringToPrint = String that should be printed
     * @param printingState = The printing state that is required
     */
     public static void print(String stringToPrint, Printing_State printingState)
     {
         if(isPrintingStateAchieved(printingState)) {
             String printingText = "";

             DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
             LocalDateTime now                   = LocalDateTime.now();
             printingText += "[" + dateTimeFormatter.format(now) + "] ";

             String className = Thread.currentThread().getStackTrace()[2].getClassName();
             String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

             printingText += "[" + className + "." + methodName + "()] " + stringToPrint;

             System.out.println(printingText);
         }
     }

    /**
     * Determines whether the print is allowed to be printed at the current state or not
     *
     * @param printingStateToDetermine = The printing state that should be checked
     *
     * @return boolean
     */
     private static boolean isPrintingStateAchieved(Printing_State printingStateToDetermine)
     {
         if(!(Printer.currentPrintingState == Printing_State.OFF)) {
             if(Printer.currentPrintingState == Printing_State.DEBUG || printingStateToDetermine == Printing_State.RAW) {
                 // all from here is allowed for printing.
                 return true;
             }

             // If yes, allowed, if not, then the print is in raw mode and not allowed to print
             return Printer.currentPrintingState == Printing_State.DETAILED;
         }
         return false;
     }
}
