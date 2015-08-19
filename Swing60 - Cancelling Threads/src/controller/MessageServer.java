package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import model.Message;

/* this is a simulated message server */

public class MessageServer implements Iterable<Message>{
	
	private Map<Integer, List<Message>> messages;
	
	private List<Message> selected;
	
	public MessageServer() {
		selected = new ArrayList<Message>();
		messages = new TreeMap<Integer, List<Message>>();
		
		List<Message> list0 = new ArrayList<Message>();
		list0.add(new Message("The cat is missing", "Have you seen Felix?"));
		list0.add(new Message("See you later?", "Are we meeting still in the pub?"));
		messages.put(0, list0);
		
		List<Message> list1 = new ArrayList<Message>();
		list1.add(new Message("How about dinner later?", "Are you doing anything later on?"));
		list1.add(new Message("That's done then", "See you at the restaurant"));
		messages.put(1, list1);
		
	}
	
	public void setSelectedServers (Set<Integer> servers) {
		
		selected.clear();
		
		for (Integer id : servers) {
			if (messages.containsKey(id)) {
				selected.addAll(messages.get(id));
			}
		}
	}
	
	public int getMessageCount() {
		return selected.size();
	}

	@Override
	public Iterator<Message> iterator() {
		return new MessageIterator(selected);
	}
}

class MessageIterator implements Iterator {

	private Iterator<Message> iterator;
	
	public MessageIterator(List<Message> messages) {
		iterator = messages.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Object next() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			//say nothing
		}
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}
	
}
