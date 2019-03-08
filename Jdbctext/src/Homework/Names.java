package Homework;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Random;
 
public class Names {
 
	private static String xings = "�� Ǯ �� �� �� �� ֣ �� �� �� �� �� " + "�� �� �� �� �� �� �� �� �� �� ʩ �� " + "�� �� �� �� �� κ �� �� �� л �� �� "		+ "�� ˮ � �� �� �� �� �� �� �� �� �� " + "��ٹ ˾�� �Ϲ� ŷ�� �ĺ� ��� ���� ���� ���� �ʸ� ξ�� ����";
	private static String mings = "�̷������ա����㡢���̡����Ρ����̡��������黱��ƽ������䡢��硢���ɡ����ơ�" + "���ɡ��������ΰء����ס��������������桢�ûܡ����ࡢЦ�������䡢���ء���ѩ��"
			+ "�ַ㡢��ޱ�����㡢Ѱ������ɽ���Ӻ������㡢�ٲ����������������²������桢���١�" + "������ܡ���ɽ��ǧ�١����졢��ܽ����ɽ�����������������������ϡ����ס����ޡ�"
			+ "��˪�����ۡ����㡢���ơ�Ѱ�ġ���ѩ�����桢���ա����������١��໱�����������Ρ�" + "ϧѩ���𺣡�֮�ᡢ����";
	private static Random r = new Random();
	/**
	 * ʹ��ָ�������ϣ���xings������֣������������
	 * @param xing		ָ������
	 * @param length	ָ�������ܳ���
	 * @return
	 */
	public static String build(String xing, int length) {
		// ��������
		String xingming = xing;
 
		//�����������
		Random r = new Random();
 
		while (xingming.length() < length) {
			// �����ַ��������ȡ��һ���ַ��ı��
			int index = r.nextInt(mings.length());
			// �������ַ�����ȡһ����
			String s = mings.substring(index, index + 1);
			// ���s�Ƕٺţ���������ȡһ�Σ�ʹ�ó����ȱ����������Ƽ��ķ�ʽ��
			if ("��".equals(s)) {
				continue;
			} else {
				//���� �ӵ� ������ȡ
				xingming += s;
			}
		}
		return xingming;
	}
 
	/**
	 * ʹ��xings�������ϣ���xings������֣������������
	 * @param length ָ�������ܳ���
	 * @return
	 */
	public static String build(int length) {
		// �ж������ĳ��ȱ������ 2
		if (length < 2) {
			System.out.println("������������2���ַ�");
			return null;
		}
		/**
		 * ��Ϊ��Щ���ϲ��ܲ�֣��磺���գ������ѡ���ϲ�����ѡ��������һ��һ����ѡ
		 * ���Ҫ��xingsת������
		 */
		// �����ѡ����
		Random r = new Random();
		String[] xingArr = xings.split(" ");
		int index = r.nextInt(xingArr.length);
		String xing = xingArr[index];
 
		// �������ϣ��ٵ��������ѡ���ֵķ�������OK��
		return build(xing, length);
	}
 
	/**
	 * �������2~3���ֵ�����
	 * @return
	 */
	public static String build() {
		int length = r.nextInt(2) + 2;
		return build(length);
	}
	/**
	 * ������ɳ�������
	 * @return
	 */
	public static String randomDate(){
        Random rndYear=new Random();
        int year=rndYear.nextInt(18)+2000;  //����[2000,2017]����������
        Random rndMonth=new Random();
        int month=rndMonth.nextInt(12)+1;   //����[1,12]����������
        Random rndDay=new Random(); 
        int Day=rndDay.nextInt(30)+1;       //����[1,30)����������
        Random rndHour=new Random();
        int hour=rndHour.nextInt(23);       //����[0,23)��������Сʱ
        Random rndMinute=new Random(); 
        int minute=rndMinute.nextInt(60);   //���ɷ���
        Random rndSecond=new Random();
        int second=rndSecond.nextInt(60);   //��
      return year+"-"+month+"-"+Day+"  "+hour+":"+minute+":"+second;
    }
	/**
	 * ��������ȡ�ı�����
	 */
	public static String nations(String text){
		String str=null;
		//����Դ
		File src =new File(text);
		//ѡ����
		BufferedReader reader =null;
		try {
			reader =new BufferedReader(new BufferedReader(new BufferedReader(new BufferedReader(new BufferedReader(new BufferedReader(new BufferedReader(new BufferedReader(new FileReader(src)))))))));
			//��ȡ����
			char[] flush =new char[1024];
			int len =0;
			while(-1!=(len=reader.read(flush))){
				//�ַ�����ת�� �ַ���
				str =new String(flush,0,len);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Դ�ļ�������");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("�ļ���ȡʧ��");
		}finally{
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (Exception e2) {
			}
		}
		
		return str;
	}
	/**
	 * �����������
	 * @return
	 */
	public static String nation() {
		String xing=null;
		String str=nations("nation.txt");
		Random r = new Random();
		String[] xingArr = str.split(" ");
		int index = r.nextInt(xingArr.length);
		xing = xingArr[index];
		return xing;
		
	}
	/**
	 * �����������
	 */
	public static String city() {
		String xing=null;
		String str=nations("city.txt");
		Random r = new Random();
		String[] xingArr = str.split(" ");
		int index = r.nextInt(xingArr.length);
		xing = xingArr[index];
		return xing;
		
	}

}
