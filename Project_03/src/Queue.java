import java.util.LinkedList;


public class Queue<E> implements IQueue<E>{
	
	// Basic queue implementation, benefiting all properties of built-in linked list. 
	private LinkedList<E> queueStorage = new LinkedList<E>();

	@Override
	public E dequeue() {
		E temp = queueStorage.getFirst();
		queueStorage.removeFirst();
		return temp;
	}

	@Override
	public void enqueue(E elem) {
		queueStorage.add(elem);
	}

	@Override
	public boolean isEmpty() {
		return queueStorage.isEmpty();
	}

	@Override
	public E peek() {
		return queueStorage.getLast();
	}

	@Override
	public int size() {
	
		return queueStorage.size();
	}
	
}
