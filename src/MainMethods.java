import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map.Entry;

public class MainMethods {

	public static int methods=0;
	public static int classes=0;
	public static int members=0;
	public static int public_meth=0;
	public static int param_passed=0;
	
	public static StringBuilder statsForOneClass(String name,ClassQueue cq){
		StringBuilder a=new StringBuilder();
  		try {
  			Class<?> cls = Class.forName(name);
  			ClassStats s=new ClassStats(cls);
  			
  			a.append("***********************************Class Name***************************************\n");
  			a.append("Name of the class: "+s.class_name+"\n");
  			a.append("************************************************************************************\n");
  			a.append(" This class contains:\n");
  			a.append("  "+s.num_of_methods+" methods\n");
  			a.append("  "+s.num_of_members+" members\n");
  			a.append("  "+s.public_methods+" public methods\n");
  			
  			MainMethods.methods+=s.num_of_methods;
  			MainMethods.classes++;
  			MainMethods.members+=s.num_of_members;
  			MainMethods.public_meth+=s.public_methods;
  			MainMethods.param_passed+=s.total_param_passed;
  			
  			a.append("--------------------------------PARAMETERS PASSED------------------------------------\n");
  			for (Entry<String,Integer> entry:s.param_per_method.entrySet()){
  				a.append(entry.getValue()+" parameters in "+entry.getKey()+"\n");
  				}
  			a.append("-------------------------------------------------------------------------------------\n");

  			if(cq!=null)
  				cq.addArray(s.getReferredClasses());
  			}
  		catch(Exception e){
  			//e.printStackTrace();
  			//System.err.append("");
  		} return a;
	}
	
	public static StringBuilder statsForSetOfClasses(String filepath){
		String[] list=loadNamesToArray(filepath);
		StringBuilder a=new StringBuilder();
		
		
		for(int i=0;i<list.length;i++)
			a.append(statsForOneClass(list[i],null));
		
		return a;
		}
	
	public static StringBuilder statsForOneClassRecursion(String name,ClassQueue cq){
		StringBuilder a=new StringBuilder();
		
		if(cq==null) cq=new ClassQueue();
		a.append(statsForOneClass(name,cq));
		
		while(!cq.isEmpty()){
			Class<?> c=cq.pull();
			a.append(statsForOneClass(c.getName(),cq));
			}
		return a;
		}
	
	public static StringBuilder statsForSetOfClassesRecursion(String filepath){
		ClassQueue cq=new ClassQueue();
		String[] list=loadNamesToArray(filepath);
		StringBuilder a=new StringBuilder();
		
		for(int i=0;i<list.length;i++)
			a.append(statsForOneClassRecursion(list[i],cq));
		return a;
		}
	
	public static String[] loadNamesToArray(String filename){
		try{
			String classname;
            ArrayList<String> class_name_list = new ArrayList<String>();
            BufferedReader br=null;
			br = new BufferedReader(new FileReader(filename));
			
            while((classname = br.readLine()) != null)
              class_name_list.add(classname);
			return class_name_list.toArray(new String[class_name_list.size()]);
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
