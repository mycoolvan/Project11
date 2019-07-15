package dao;

import java.util.List;

import model.Message;

public interface IMessage {
	
	List<Message> getAllMessages();
	boolean sendMsg(Message m);

}
