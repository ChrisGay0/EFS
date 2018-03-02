package efs.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import efs.domain.WordCount;
import efs.exception.EndOfStreamException;
import efs.reader.CharacterReader;

public class WordCounterService {
	private CharacterReader characterReader;

	public void setCharacterReader(CharacterReader characterReader) {
		this.characterReader = characterReader;
	}
	
	public void outputWordCounts(){
		Map<String, WordCount> wordMap = getMapOfWords();
		if(wordMap != null){
			for(WordCount word: sortWords(wordMap)){
				System.out.println(word.toString());
			}
		}
		else{
			System.out.println("No Words Found!!!");
		}
	}
	
	/**
	 * Converts the individual characters returned by CharacterReader into a Map of words
	 * @return Map<String, WordCount) containing the words
	 */
	protected Map<String, WordCount> getMapOfWords(){
		boolean endOfText = false;
		String currentWord = "";
		Map<String, WordCount> wordMap = new HashMap<String, WordCount>();
		
		//Continue looping until an EndOfStream exception is thrown
		while(!endOfText){
			try{
				char nextCharacter = characterReader.getNextChar();
				//if the character is non alphas we need to ignore it until we get an alpha or a space
				while(!Character.isLetter(nextCharacter) && nextCharacter != ' '){
					nextCharacter = characterReader.getNextChar();
				}
				//if a space is found it signals the end of a word has been reached
				if(nextCharacter == ' '){
					WordCount wordCount = wordMap.get(currentWord);
					//if the word has not been found before we need to add the WordCount object to the map
					if(wordCount == null){
						wordMap.put(currentWord, new WordCount(currentWord, 1));
					}
					else{
						wordCount.incrementOccurences();
						wordMap.replace(currentWord, wordCount);
					}
					
					currentWord = "";
				}
				else{
					currentWord += nextCharacter;
				}
			}
			catch(EndOfStreamException e){
				endOfText = true;
			}
		}
		
		return wordMap;
	}
	
	/**
	 * Converts the Map into a sorted List words by the number of times it occurs and then alphabetically
	 * For example 
	 * @param wordMap
	 * @return
	 */
	protected List<WordCount> sortWords(Map<String, WordCount> wordMap){
		List<WordCount> wordList = new ArrayList<WordCount>(wordMap.values());
		
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
