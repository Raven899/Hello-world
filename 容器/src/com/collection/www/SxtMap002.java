package com.collection.www;

import java.util.LinkedList;

public class SxtMap002 {
	LinkedList[] arr=new LinkedList[990];
	int size;
	
	
	public void put(Object key,Object value) {
		//System.out.println(key.hashCode());
		
		SxtEntry e=new SxtEntry(key, value);
		int a=key.hashCode()%arr.length;
		if(arr[a]==null) {
			LinkedList list=new LinkedList();
			arr[a]=list;
			list.add(e);	
		}else {
			if(arr[a]==null) {
				LinkedList list=arr[a];
				for(int i=0;i<list.size();i++) {
					SxtEntry e2=(SxtEntry) list.get(i);
					if(e.key.equals(key)) {
						e2.value=value;
						return ;
					}
					
				}
			arr[a].add(e);
		}
	}
	}
		
	public Object get(Object key) {
		int a=key.hashCode()%arr.length;
		if(arr[a]!=null) {
			LinkedList list=arr[a];
			for(int i=0;i<list.size();i++) {
				SxtEntry e=(SxtEntry) list.get(i);
				if(e.key.equals(key)) {
					return e.value;
				}
				
			}
			
		}
		return null;		
	}
	
	public static void main(String[] args) {
		SxtMap002 m=new SxtMap002();
		m.put("高琪", new Wife("马士斌"));
		m.put("raven", new Wife("张曼玉"));
		m.put("raven", new Wife("ada"));
		Wife w=(Wife) m.get("raven");
		System.out.println(w.name);
	}

}


