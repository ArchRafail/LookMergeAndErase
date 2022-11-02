package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Thread lookerThread = new Thread(new WordLooker());
        lookerThread.start();
        try {
            lookerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WordsRemover wordsRemover = WordsRemover.wordsRemoverInstance();
        Thread removerThread = new Thread(wordsRemover);
        removerThread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        showResult(wordsRemover);
    }

    public static void showResult(WordsRemover wordsRemover) {
        System.out.println("\nWords that have to be removed from consolidated file:");
        System.out.println("-------------------------------------------------");
        System.out.println(wordsRemover.getWordsCollection());
        System.out.println("\nText in consolidated file, before removing words:");
        System.out.println("-------------------------------------------------");
        if (WordLooker.getResultText() == null) {
            System.out.println("Consolidated file is empty, because your search word was not found!");
            return;
        }
        System.out.println(WordLooker.getResultText());
        System.out.println("\nText in consolidated file, before removing words:");
        System.out.println("-------------------------------------------------");
        System.out.println(WordsRemover.getFinalText());
        System.out.print("Results can be reached here -> ");
        System.out.println(WordLooker.getResultFilePath().replace("\\\\", "\\"));
    }
}