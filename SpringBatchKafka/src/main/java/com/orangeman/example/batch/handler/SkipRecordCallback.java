package com.orangeman.example.batch.handler;

import org.springframework.batch.item.file.LineCallbackHandler;

public class SkipRecordCallback implements LineCallbackHandler {

	@Override
	public void handleLine(String s) {
		// TODO Auto-generated method stub
		System.out.println("##### First record data ####" + s);
	}

}
