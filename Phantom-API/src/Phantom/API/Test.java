package Phantom.API;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<String> namelist = new ArrayList<String>();
		namelist.add("123");
		namelist.add("234");
		String[] names = namelist.toArray(new String[namelist.size()]); 
		System.out.println(names);

	}

}
