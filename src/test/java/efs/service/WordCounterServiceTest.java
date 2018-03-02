package efs.service;

import org.junit.Test;

import efs.reader.SimpleCharacterReader;
import efs.reader.SlowCharacterReader;

public class WordCounterServiceTest {

	@Test
	public void outputWordCounts(){
		WordCounterService wordCounterService = new WordCounterService();
		wordCounterService.setCharacterReader(new SimpleCharacterReader());
		
		wordCounterService.outputWordCounts();
	}
	
	@Test
	public void multiThreadedCharacterReader(){
		MultiThreadWordCounterService wordCounterService = new MultiThreadWordCounterService(new SlowCharacterReader());
		
		for(int i = 0; i < 10; i++){
			System.out.println("****Start****");
			Thread thread = new Thread(wordCounterService);
			thread.start();
			System.out.println("****DONE****");
		}
	}
}
