package org.example;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DirectoryCreate {
    private static String pathDir;

    private DirectoryCreate(String path) {
        pathDir = path;
    }

    public static DirectoryCreate directoryInstanceCreate() {
        String path = pathToDir();
        File dir = new File(path);
        if (dir.isFile())
            throw new RuntimeException("Directory is not exist!");
        if (!dir.exists())
            throw new RuntimeException("Directory is not exist!");
        return new DirectoryCreate(path);
    }

    public static String getPathDir() {
        return pathDir;
    }

    /**
     When program ask you to input the path to the directory, enter the next:
     YourPath\LookMergeAndErase\src\main\resources
     For example D:\Projects\Java\LookMergeAndErase\src\main\resources.
     There is all needed resources for testing program.
     */
    private static String pathToDir() {
        System.out.print("Enter the folder path: ");
        Scanner scanner = new Scanner(System.in);
        String prePath = scanner.nextLine();
        try {
            Paths.get(prePath);
        } catch(InvalidPathException | NullPointerException ex) {
            throw new RuntimeException("Incorrect path to directory.");
        }
        return prePath.replace("\\","\\\\");
    }
}