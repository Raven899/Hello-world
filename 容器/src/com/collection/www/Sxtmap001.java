package com.collection.www;
/**
 * 自定义实现map的功能
 * Map:存放键值对，根据键对象找对应的值对象.键不能重复
 * @author 瑞文
 *
 */

public class Sxtmap001 {
	SxtEntry[] arr=new SxtEntry[990];
	int size;
	
	public void put(Object key,Object value) {
		SxtEntry e=new SxtEntry(key,value);
		
		for(int i=0;i<size;i++) {
			if(arr[i].key.equals(key)) {
				arr[i].value=value;
				return ;
			}
		}
		arr[size++]=e;
	}
	
	public Object get(Object key) {
		for(int i=0;i<size;i++) {
			if(arr[i].key.equals(key)) {
				return arr[i].value;
			}
		}
		return null;
		
	}
	public boolean containsKey(Object key) {
		for(int i=0;i<size;i++) {
			if(arr[i].key.equals(key)) {
				return true;
			}
		}
		return false;
	}
	public boolean containsValue(Object value) {
		for(int i=0;i<size;i++) {
			if(arr[i].key.equals(value)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Sxtmap001 m=new Sxtmap001();
		m.put("高琪", new Wife("马士斌"));
		m.put("raven", new Wife("张曼玉"));
		m.put("ravenone", new Wife("ada"));
		Wife w=(Wife) m.get("raven");
		System.out.println(w.name);
	} 

}
class SxtEntry{
	Object key;
	Object value;
	public SxtEntry(Object key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}
}
