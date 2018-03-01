package efs.service;

import java.util.List;

import efs.domain.WordCount;

public class MultiThreadWordCounterService implements Runnable{

	private List<WordCount> wordList;
	
	public MultiThreadWordCounterService(List<WordCount> wordList){
		this.wordList = wordList;
	}
	
	@Override
	public void run() {
		
	}

}
