package com.mundoviventem.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Basic class for generic file management operations
 */
public class FileManager
{

    public static int BUFFER_ALLOCATION = 32768;

    /**
     * Returns content of given file. If an error occurs, null gets returned and a error message gets printed.
     *
     * @param file = File, whose content should get returned
     * @return String
     */
    public static String getContent(String file)
    {
        try(BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file), BUFFER_ALLOCATION)) {
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
     * Creates a file by the given content
     *
     * @param content = The content that should be in the file
     * @param file    = The file name
     */
    public static void createFile(ArrayList<String> content, String file)
    {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(file)), BUFFER_ALLOCATION)) {
            StringBuilder stringBuilder = new StringBuilder();

            for (String line: content) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            bufferedWriter.write(stringBuilder.toString());
        } catch (Exception exception) {
            System.err.println("Could not write content in file '" + file + "'");
        }
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
