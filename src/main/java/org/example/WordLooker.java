package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class WordLooker implements Runnable {
    private static String resultFilePath;
    private static StringBuilder resultText;

    public static String getResultFilePath() {
        return resultFilePath;
    }

    public static StringBuilder getResultText() {
        return resultText;
    }

    /**
    When program ask you to input the word, enter the next: lock.
     The result.txt file will be empty.
     Or you can input: tutorial. This word presents in few files.
     */
    private String wordCreate() {
        System.out.print("Input the word that we will look for: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // text was picked up here - https://dev.java/learn/getting-started-with-java/
    // text was divided by parts and putted into few files
    private boolean fileContainsWord(File file, String word) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.getPath()))).contains(word);
    }

    private void contentClone(File sourceFile, FileWriter fileWriter) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                fileWriter.write(line+System.lineSeparator());
            }
            fileWriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException("Can't open the file " + sourceFile.getAbsolutePath());
        }
    }

    private void textCopy(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            resultText = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                resultText.append(line+System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("An issue while reading consolidated file with results.");
        }
    }

    /**
     Don't give your files the name results.txt! This consolidated file will have this name.
     Accepted file for searching word may have extension *.txt, *.dat, *.dll.
     */
    @Override
    public void run() {
        DirectoryCreate.directoryInstanceCreate();
        String dirPath = DirectoryCreate.getPathDir();
        File folder = new File(dirPath);
        String word = wordCreate();
        resultFilePath = dirPath+File.separator+"results.txt";
        File file = new File(resultFilePath);
        if (file.exists())
            file.delete();
        boolean flag = false;
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            for (File eachFile: folder.listFiles()) {
                if (fileContainsWord(eachFile, word)) {
                    contentClone(eachFile, fileWriter);
                    flag = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Сan not composed consolidated file.");
        }
        System.out.println();
        System.out.println(flag ? "Content from files that contain your word was already copied!" :
                "Nothing to copy. Word was not found.");
        if(flag) textCopy(file);
    }
}
