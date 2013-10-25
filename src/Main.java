import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1 : Generate statistics for one class");
        System.out.println("2 : Generate statistics for classes listed in .txt file");
        System.out.println("3 : Generate statistics for one class with recursion");
        System.out.println("4 : Generate statistics for classes listed in .txt file with recursion");
        System.out.println("5 : Close program");
        
        while(true){
        String yourchoice = bufferRead.readLine();
        int choice;
		
        try {
			choice = Integer.parseInt(yourchoice);
		} catch (NumberFormatException e) {
			choice = 6;
	}
        
        switch (choice) {
        case 1:
            System.out.println("type the name of a class/n");
            String classname = bufferRead.readLine();
            StringBuilder sb1=MainMethods.statsForOneClass(classname,null);
            System.out.println(sb1.toString());
            System.out.println("SUMMARY\n");
			System.out.println(MainMethods.methods+" methods\n"+MainMethods.classes+" classes\n"+MainMethods.members+" members\n"+MainMethods.public_meth+" public methods\n"+MainMethods.param_passed+" parameters passed\n");
            break;
        case 2:
            System.out.println("type the name of .txt file/n");
            String filename = bufferRead.readLine();
            StringBuilder sb2;
			sb2 = MainMethods.statsForSetOfClasses(filename);
			System.out.println(sb2.toString());
			System.out.println("SUMMARY\n");
			System.out.println(MainMethods.methods+" methods\n"+MainMethods.classes+" classes\n"+MainMethods.members+" members\n"+MainMethods.public_meth+" public methods\n"+MainMethods.param_passed+" parameters passed\n");
            break;
        case 3:
        	System.out.println("type the name of a class/n");
        	String classnamerec = bufferRead.readLine();
        	StringBuilder sb3=MainMethods.statsForOneClassRecursion(classnamerec,null);
    		System.out.println(sb3.toString());
    		System.out.println("SUMMARY\n");
			System.out.println(MainMethods.methods+" methods\n"+MainMethods.classes+" classes\n"+MainMethods.members+" members\n"+MainMethods.public_meth+" public methods\n"+MainMethods.param_passed+" parameters passed\n");
            break;
        case 4:
        	System.out.println("type the name of .txt file/n");
        	String filenamerec = bufferRead.readLine();
        	StringBuilder sb4=MainMethods.statsForSetOfClassesRecursion(filenamerec);
			System.out.println(sb4.toString());
			System.out.println("SUMMARY\n");
			System.out.println(MainMethods.methods+" methods\n"+MainMethods.classes+" classes\n"+MainMethods.members+" members\n"+MainMethods.public_meth+" public methods\n"+MainMethods.param_passed+" parameters passed\n");
            break;
        case 5:
        	System.exit(0); 
        	break;
        default:
        	System.out.println("You have chosen the bad option. Try again.Choose from 1 to 5.");
        	}
        
        }
	}
}
