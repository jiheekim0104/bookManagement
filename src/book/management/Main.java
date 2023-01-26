package book.management;

public class Main {
	public static void main(String[] args) {
		System.out.println("-----------------------------------------");
		System.out.println("***************�������� ���α׷�*************");
		System.out.println("-----------------------------------------");
		
		
		BookManager bookManager = new BookManager(); // BookManager Ŭ���� �޼ҵ� ����� ���� �����ڸ���
		int managerIndex = bookManager.login(); // BookManager Ŭ�������� login �޼ҵ带 �����ؼ� �޾ƿ°��� �Ŵ����ε��������
		if(managerIndex>=0) { // ���Ϲ޾ƿ� �Ŵ����ε����� 0���� ũ�ų� ������ �α��� ����			
			bookManager.init(); //BookManager Ŭ�������� init �޼ҵ带 ���� ==> �ʱ�ȭ, �⺻å���� �Է�, ȸ������ �Է�
			bookManager.run(managerIndex);	//BookManager Ŭ�������� run �޼ҵ带 ����
		} else {
			System.out.println("�α��� ����"); // ���Ϲ޾ƿ� �Ŵ����ε����� 0���� ������ �α��� ����
		}
		System.out.println("���α׷� ����");
	}
}
