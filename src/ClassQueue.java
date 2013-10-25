import java.util.HashMap;
import java.util.LinkedList;

public class ClassQueue {
	
	private HashMap<String,Class<?>> h=new HashMap<String,Class<?>>();
	private LinkedList<Class<?>> q=new LinkedList<Class<?>>();
	
	ClassQueue(){}
	
	public boolean isEmpty(){
		return q.isEmpty();
	}
	
	public Class<?> pull(){
		return q.poll();
	}
	
	public void add(Class<?> c){
		if(c!=null)if(!h.containsKey(c.getName())){
			h.put(c.getName(),c);
			q.add(c);
		}
	}
	
	public void addArray(Class<?>[] ca){
		for(int i=0;i<ca.length;i++)
			this.add(ca[i]);
	}
}
