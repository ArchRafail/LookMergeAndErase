package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WordsRemover implements Runnable {
    private final Set<String> wordsCollection;
    private static final String fileName = "bad_words.txt";
    private static StringBuilder finalText;

    private WordsRemover(Set<String> words) {
        this.wordsCollection = words;
    }

    public static StringBuilder getFinalText() {
        return finalText;
    }

    public Set<String> getWordsCollection() {
        return wordsCollection;
    }

    public static WordsRemover wordsRemoverInstance() {
        Set<String> words = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DirectoryCreate.getPathDir() +
                File.separator+fileName))){
            String line;
            while((line = bufferedReader.readLine()) != null) {
                words.addAll(Arrays.stream(line.split("[\\s\\t\\n.,]+")).collect(Collectors.toSet()));
            }
        } catch (IOException e) {
            throw new RuntimeException("File with bad words can not be reached. It is absence!");
        }
        return new WordsRemover(words);
    }

    @Override
    public void run() {
        if (WordLooker.getResultText() == null) {
            System.out.println("No any bad words were removed. File are empty!");
            return;
        }
        String filePath =  WordLooker.getResultFilePath();
        finalText = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                for (String word : line.trim().split("[\\s\\t\\n]+")) {
                    boolean isDelimiter = false;
                    char delimiter = ' ';
                    if (word.length() > 1) {
                        delimiter = word.charAt(word.length() - 1);
                        switch (delimiter) {
                            case ',':
                            case '.':
                            case '!':
                            case ';':
                            case ':':
                            case '\\':
                            case '/':
                            case '_':
                            case '-':
                                isDelimiter = true;
                                word = word.substring(0, word.length() - 1);
                        }
                    }
                    if (!(wordsCollection.contains(word)))
                        finalText.append(word + " ");
                    if (isDelimiter) {
                        finalText.deleteCharAt(finalText.lastIndexOf(" "));
                        finalText.append(delimiter + " ");
                    }
                }
                finalText.deleteCharAt(finalText.lastIndexOf(" "));
                finalText.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("File with results can not be read! An issue there.");
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false))) {
            bufferedWriter.write(String.valueOf(finalText));
        } catch (IOException e) {
            throw new RuntimeException("Can't rewrite the file with results!");
        }
        System.out.println("Bad words were removed from file!");
    }
}