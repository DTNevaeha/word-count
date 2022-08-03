package com.tlglearning.wordcount;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordCounter {

  private static final Set<String> BORING_WORDS = Set.of("and", "of", "in", "on", "i", "then",
      "than", "out", "a", "to", "if", "the");
  private final Map<String, Integer> counts = new HashMap<>();

  private int totalWords;

  public Set<String> words() {
    return counts.keySet();
  }

  public int get(String word) {  //if someone searches a word that doesnt exist, return 0
    return counts.getOrDefault(word, 0);
  }

  public Map<String, Integer> getCounts() {
    return Collections.unmodifiableMap(counts);
  }

  public void add(String text) { //tries to see if lines are just blank and skip it
    String trimmedLine = text.trim();
    if (!trimmedLine.isEmpty()) {
      String[] words = splitWords(trimmedLine);
      countWords(words);
    }
  }

  public int size() {
    return counts.size();
  }

  public int total() { //gets the total words within the article
    return totalWords;
  }

  @Override
  public String toString() {
    return counts.toString();
  }

  String[] splitWords(String text) {
    return text
        .toLowerCase() //changes all letters to lowercase to prevent double word count (the/The)
        .split("[\\W_]+"); //replaces non-word character or _ with a space
  }

  void countWords(String[] words) { //this is return type
    Arrays
        .stream(words) //converts the Array to a stream
        .map(String::trim) //detects blank lines
        .filter((word) -> !word.isEmpty())
        .filter((word) -> word.length() > 5) //filters out words that are less than 5 characters
        .filter((s) -> !BORING_WORDS.contains(s)) //filters out these words
//        .filter(Predicate.not(String::isEmpty))     This is the same as the above
        .forEach((word) -> counts.put(word,
            1 + counts.getOrDefault(word, 0))); //adds to total word count or puts 0
  }
}

