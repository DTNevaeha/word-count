package com.tlglearning.wordcount;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordCounter {

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
    for (String word : words) {
      // DONE check if word is already present as a key in counts;
      //  if it's not present, add it to counts with a value of 1.
      //  otherwise, get the current value, add 1 to it, and update the map with the new value.
      counts.put(word, get(word) + 1);
      totalWords++;
    }
  }

}
