package com.mundoviventem.io;

import java.io.BufferedReader;
import java.io.File;

/**
 * Basic class for generic file management operations
 */
public class FileManager
{

    /**
     * Returns content of given file. If an error occurs, null gets returned and a error message gets printed.
     *
     * @param file = File, whose content should get returned
     * @return String
     */
    public static String getContent(String file)
    {
        try(BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file))) {
            StringBuilder stringBuilder   = new StringBuilder();
            String line                   = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            System.err.println("Could not read content of file '" + file + "'");
        }

        return null;
    }

    /**
     * Calculates the path of the current Project.
     */
    public static String determineProjectPath()
    {
        // Create temp file and get path of the file minus the name of the file.
        String fileName      = "temp";
        File currentDirFile  = new File(fileName);
        return (currentDirFile.getAbsolutePath()).replace("\\" + fileName, "");
    }

}
