

import java.util.ArrayList;

public class Set<E>{
	
	ArrayList<E> set;
	
	public Set() {
		
		set = new ArrayList<E>();
		
	}
	
	public Set(E elem) {
		
		set = new ArrayList<E>();
		set.add(elem);
		
	}
	
	public void insert(E elem){
		set.add(elem);
	}

}
