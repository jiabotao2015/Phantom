package Phantom.Data.Initiation.Service;

import org.springframework.stereotype.Service;

@Service
public class SpringInjectService {

	private static String wenurl = "aaaaaaaaaaaaaa";
	public void sayHello(){
		System.out.println("this is a injected service");
		System.out.println(wenurl);
	}
}
