package 泛型;
/**
 * 泛型父类
 * 1.如果父类是泛型类
 * 保留父类的泛型--->泛型子类
 * 子类方法里面的泛型随父类的泛型
 * 
 * @author 瑞文
 *
 */

public abstract class Father <T1,T2>{

}
//保留 --->泛型子类
//(1)全部保留
class C1<T1, T2> extends Father<T1, T2>{}
//(2)部分保留
class C2<T2,A,C> extends Father<Integer, T2>{
	
}

//不保留 -->按需实现
//1)具体类型
class C3 extends Father<Integer, Integer>{}
//2)没有类型 擦除 object
class C4 extends Father{}

