package com.tlglearning.wordcount;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class WordCounter {

  private final Map<String, Integer> counts;

  public WordCounter(String text) {
    String[] words = splitWords(text);
    counts = Collections.unmodifiableMap(countWords(words));
  }

  public Set<String> words() {
    return counts.keySet();
  }

  @Override
  public String toString() {
    return counts.toString();
  }

  public int getCount(String word) {  //if someone searches a word that doesnt exist, return 0
    return counts.getOrDefault(word, 0);
  }

  public Map<String, Integer> getCounts() {
    return counts;
  }

  String[] splitWords(String text) {
    return text
        .toLowerCase() //changes all letters to lowercase to prevent double word count (the/The)
        .split("[\\W_]+"); //replaces non-word character or _ with a space
  }

  Map<String, Integer> countWords(String[] words) {
    Map<String, Integer> counts = new HashMap<>();
    for (String word : words) {
      // DONE check if word is already present as a key in counts;
      //  if it's not present, add it to counts with a value of 1.
      //  otherwise, get the current value, add 1 to it, and update the map with the new value.
      if (!counts.containsKey(word)) {
        counts.put(word, 1);
      } else {
        int previousCount = counts.get(word);
        counts.put(word, previousCount + 1);
      }
    }
    return counts;
  }

}
