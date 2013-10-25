import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Hashtable;

public class ClassStats {
	
	public Class<?> cl;
	public String class_name;
	public int public_methods;
	public Hashtable<String, Integer> param_per_method;
	public int num_of_methods;
	public int num_of_members;
	public int num_of_fields;
	public int total_param_passed;
	Class<?>[] referedclasses;
	
	public ClassStats(Class<?> cl){
		
		this.cl=cl;
		Method methlist[] = cl.getDeclaredMethods();
		Field fieldlist[] = cl.getDeclaredFields();
		
		class_name=cl.getName();
		num_of_methods=methlist.length;
		num_of_fields=fieldlist.length;
		num_of_members=num_of_fields+num_of_methods;
	
		public_methods=0;
		total_param_passed=0;
		param_per_method=new Hashtable<String,Integer>();
		for (int i = 0; i < methlist.length;i++){
			param_per_method.put(methlist[i].toString(), methlist[i].getParameterTypes().length);
			total_param_passed+=methlist[i].getParameterTypes().length;
			if(Modifier.isPublic(methlist[i].getModifiers()))
				public_methods++;
		}
		
		ArrayList<Class<?>> rc=new ArrayList<Class<?>>();
		rc.add(cl.getSuperclass());
		
		if(!rc.contains(cl.getDeclaringClass()))
			rc.add(cl.getDeclaringClass());
		
		Class<?>[] t=cl.getInterfaces();
		for(int i=0;i<t.length;i++)
			if(!rc.contains(t[i]))
				rc.add(t[i]);
		
		t=cl.getDeclaredClasses();
		for(int i=0;i<t.length;i++)
			if(!rc.contains(t[i]))
				rc.add(t[i]);
		
		//do zmienienia
		/*for(int i=0;i<methlist.length;i++){
			if(methlist[i].getReturnType()!=null)
				if(!rc.contains(methlist[i].getReturnType()))
					rc.add(methlist[i].getReturnType());
		}*/
		//do zmienienia
		referedclasses=rc.toArray(new Class[rc.size()]);
		
		
	}
	public Class<?>[] getReferredClasses(){
		return referedclasses.clone();
	}
}

