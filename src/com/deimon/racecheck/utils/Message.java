package com.deimon.racecheck.utils;

/**
 * Holds a queue of messages for inter-thread communication
 * Producer and Consumer should <b>not</b> be the same thread!
 * 
 * @param <T> type of the messages
 */
public class Message{
	private String msg;
	
	public Message(){
		
	}
	
//	public Message(String str){
//        this.msg=str;
//    }
	
	public void setMsg(String str) {
        this.msg=str;
    }
	
    public String getMsg() {
        return msg;
    }
}