package com.collection.www;
import java.util.*;
/**
 * 
 * @author ����
 *
 */

public class TestMap {

	public static void main(String[] args) {
		Map map=new HashMap();
		map.put("raven", new Wife("������"));
		map.put("ravenone", new Wife("ada"));
		int s=map.size();
		//map.remove("raven");
		Wife w=(Wife) map.get("raven");
		//map.remove("raven");
		System.out.println(w.name);
		System.out.println(s);
		
	}

}
class Wife{
	String name;
	public Wife(String name) {
		this.name=name;
	}
}
