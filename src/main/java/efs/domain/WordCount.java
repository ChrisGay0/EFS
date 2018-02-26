package efs.domain;

public class WordCount {
	private String word;
	private int numberOfOccurences;
	
	public WordCount(String word, int occurences){
		this.word = word;
		this.numberOfOccurences = occurences;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getNumberOfOccurences() {
		return numberOfOccurences;
	}
	public void setNumberOfOccurences(int numberOfOccurences) {
		this.numberOfOccurences = numberOfOccurences;
	}
	public void incrementOccurences(){
		this.numberOfOccurences++;
	}
	
	@Override
	public String toString(){
		return this.word + " - " + this.numberOfOccurences;
	}
	
}
