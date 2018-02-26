package efs.service;

import org.junit.Test;

import efs.reader.SimpleCharacterReader;

public class WordCounterServiceTest {

	@Test
	public void outputWordCounts(){
		WordCounterService wordCounterService = new WordCounterService();
		wordCounterService.setCharacterReader(new SimpleCharacterReader());
		
		wordCounterService.outputWordCounts();
	}
}
