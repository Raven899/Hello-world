package ����;
/**
 * ���͸���
 * 1.��������Ƿ�����
 * ��������ķ���--->��������
 * ���෽������ķ����游��ķ���
 * 
 * @author ����
 *
 */

public abstract class Father <T1,T2>{

}
//���� --->��������
//(1)ȫ������
class C1<T1, T2> extends Father<T1, T2>{}
//(2)���ֱ���
class C2<T2,A,C> extends Father<Integer, T2>{
	
}

//������ -->����ʵ��
//1)��������
class C3 extends Father<Integer, Integer>{}
//2)û������ ���� object
class C4 extends Father{}

