package efs.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import efs.domain.WordCount;

/**
 * Singleton class to store a list of words that multiple threads can access
 *
 */
public class WordCollector {
    
    private static WordCollector wordCollector;
    private Map<String, WordCount> wordMap = new TreeMap<String, WordCount>();
    private WordCollector(){
    	
    }
    
    public static WordCollector getInstance(){
        if(wordCollector == null){
        	wordCollector = new WordCollector();
        }
        return wordCollector;
    }
    
    //Either add a new word to the word map or increment the number of occurences
    public void addWord(String word){
    	WordCount wordCountFound = wordMap.get(word);
    	//if the word has not been found before we need to add the WordCount object to the map
		if(wordCountFound == null){
			wordMap.put(word, new WordCount(word, 1));
		}
		else{
			wordCountFound.incrementOccurences();
			wordMap.replace(word, wordCountFound);
		}
    }
    
    public List<WordCount> getSortedWords(){
    	List<WordCount> wordList = new ArrayList<WordCount>(this.wordMap.values());
		
		Collections.sort(wordList, new Comparator<WordCount>() {

	        public int compare(WordCount o1, WordCount o2) {

	            Integer count1 = o1.getNumberOfOccurences();
	            Integer count2 = o2.getNumberOfOccurences();
	            
	            if (count1 != count2) {
	               return count2.compareTo(count1);
	            } else {
	               String word1 = o1.getWord();
	               String word2 = o2.getWord();
	               
	               return word1.compareTo(word2);
	            }
	    }});
		
		return wordList;
    }
}
