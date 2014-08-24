
public interface IQueue<E> {
	
	// Interface of my Queue.
	public E dequeue();
	public void enqueue(E elem);
	public E peek();
	public boolean isEmpty();
	public int size();
	
}
