package book.management;

public class Main {
	public static void main(String[] args) {
		System.out.println("-----------------------------------------");
		System.out.println("***************�������� ���α׷�*************");
		System.out.println("-----------------------------------------");
		
		
		BookManager bookManager = new BookManager();
		int managerIndex = bookManager.login();
		if(managerIndex>=0) {
			//������ �ʱ�ȭ, �⺻å���� �Է�, ȸ������ �Է�
			bookManager.init();
			
			//����
			bookManager.run(managerIndex);			
		} else {
			System.out.println("�α��� ����");
		}
		System.out.println("���α׷� ����");
	}
}
