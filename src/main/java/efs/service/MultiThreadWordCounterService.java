package efs.service;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import efs.domain.WordCount;
import efs.exception.EndOfStreamException;
import efs.reader.CharacterReader;
import efs.reader.SlowCharacterReader;

public class MultiThreadWordCounterService implements Runnable{

	private CharacterReader characterReader;
	
	public MultiThreadWordCounterService(CharacterReader characterReader){
		this.characterReader = characterReader;
	}

	public static void main(String[] args){
		//create 10 threads to run 
		for(int i = 0; i < 10; i++){
			Thread thread = new Thread(new MultiThreadWordCounterService(new SlowCharacterReader()));
			thread.start();
		}
		//create a timer and task to run every 10 seconds
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("***********Outputting words**********");
				WordCollector collector = WordCollector.getInstance();
				for(WordCount wordCount: collector.getSortedWords()){
					System.out.println(wordCount.toString());
				}
			}
		}, 0, 10000);
	}
	
	@Override
	public void run() {
		boolean endOfText = false;
		String currentWord = "";
		WordCollector wordCollector = WordCollector.getInstance();
		
		while(!endOfText){
			try{
				char nextCharacter = characterReader.getNextChar();
				//if the character is non alphas we need to ignore it until we get an alpha or a space
				while(!Character.isLetter(nextCharacter) && nextCharacter != ' '){
					nextCharacter = characterReader.getNextChar();
				}
				//if a space is found it signals the end of a word has been reached
				if(nextCharacter == ' '){
					wordCollector.addWord(currentWord);
					
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
	}
}
