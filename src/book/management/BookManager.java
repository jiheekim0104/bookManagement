package book.management;

import java.time.LocalDate;
import java.util.Scanner;

public class BookManager {

	private static final String[] ID = { "������", "������", "�̼���" };
	private static final String[] PASSWORD = { "0104", "1111", "2222" };

	private Book[] books; // å���� �迭�� ������, å�尰�� ����!
	private int bookSize; // �������ִ� å ��

	private Member[] members; // ������� �迭�� ������,ȸ����ΰ��� ����!
	private int memberSize; // �������ִ� ȸ�� ��
	Scanner sc;
	LocalDate now; //�̰� ��¥ ���������� ����߾��, ���� import�� ���� ���˴ϴ�. 

	public BookManager() {
		this.books = new Book[100]; // ���������� �����Ҽ��ִ� �� å�� 100��
		this.bookSize = 0;// ���α׷��� �ԷµǾ� �������ִ� å�� ��, �ʱ� 0
		this.members = new Member[100]; // ���α׷��� �����Ҽ��ִ� �� ȸ������ 100��
		this.memberSize = 0;// ���α׷��� �ԷµǾ� �������ִ� ȸ���� ��, �ʱ� 0
	}

	public int login() { // �缭 ���� & ��� �Է¹ް� ������ TRUE ����, �ƾƵ� Ʋ���� -1, ��� Ʋ���� -2 �����Ͽ� �α��� ����� �����ΰ�� �α��� ����
		sc=new Scanner(System.in);
		System.out.print("ID: ");
		String id = sc.nextLine();
		System.out.print("PASSWORD: ");
		String password = sc.nextLine();

		int index = -1; 
		for (int i = 0; i < ID.length; i++) { // ������ �ش� �ε����� ����, 0:������, 1:������, 2:�̼���
			if (id.equals(ID[i])) {
				index = i;
			}
		}
		if (index < 0)
			return -1; // ���̵� Ʋ���� -1 ����
		if (password.equals(PASSWORD[index])) // ���̵� �ش��ϴ� �ε����� ��й�ȣ �ε����� �����Ͽ� ������ �ش� �ε��� ����
			return index;
		return -2; // ���̵� �ش��ϴ� �ε����� ���ؼ� ��й�ȣ�� Ʋ���� -2 ����
	}

	public void init() {
		// �ʱ� å �ֱ� (å�̸�,�۰�,��ġ,�����뿩��,�������) �Ǵ�  (å�̸�,�۰�,��ġ,�����뿩��,������ ~~~) �̷��� �ΰ��� ������
		add(new Book("����", "������", "��.1.11", 1, "������(�ռ���/1��", 18));
		add(new Book("����", "������", "��.1.12", 2, "������(�̳���/1��", 20));
		add(new Book("����", "������", "��.1.13", 1, "���Ⱑ��"));
		add(new Book("������̺�", "���̺�", "��.0.10", 11, "������(�����/1��", 27));
		add(new Book("���Ժ���", "������", "��.1.13", 21, "���Ⱑ��"));
		add(new Book("���۷θ�", "������", "��.1.13", 25, "������(�ڿ���/1��", 10));
		// ...
		// ...

		// �ʱ� ȸ�� �ֱ�(ȸ����,�̳�Ƚ�� ����)
		add(new Member("�ռ���",1));
		add(new Member("�����",0));
		add(new Member("�ڿ���",5));
		add(new Member("������",0));
		add(new Member("�ֿ���",3));
		add(new Member("�̳���",0));
		add(new Member("�嵵��",5));
		// ...

	}

	private int getBookCount(String bookName) { // �Ķ���ͷ� �޾ƿ� å �̸��� �������ִ��� ī�����ϴ� �޼ҵ�
		int count = 0; // ī���� �ʱⰪ�� 0
		for (int i = 0; i < bookSize; ++i) // å�忡 ����ִ� å���� �ε��� 0���� ������ ã�ƺ� 
			if (books[i].name.equals(bookName)) // �ش� �ε����� ������ å �̸��� �Ķ���ͷ� �޾ƿ� å�̸��� ���ٸ�
				count++; // ī���� �߰�
		return count; // ī���ð� ���� , �� ã������ å�� �ߺ��� å���ڸ� �����Ѵٴ¼Ҹ�
	}
	
	public Book[] findBook(String bookName) { // �Ķ���ͷ� �޾ƿ� å�� �ִ��� ã�� å�� �����ִ� �޼ҵ�
		int count = getBookCount(bookName); // �����ִ� getBookCount�� ���� ã�ƺ����� å�� ���� å�忡 ������ִ��� �� ���� �޾ƿ�
		Book[] books = new Book[count]; // �� ���� ������ ���ο� �迭�� ������

		int index = 0;
		for (int i = 0; i < bookSize; ++i) { 
			if (this.books[i].name.equals(bookName)) // å�忡 �ִ� å �̸��� ã������ å�̸��� ���ٸ�
				books[index++] = this.books[i]; //���θ��� �迭���ٰ� �� å�� �־���
		}
		return books; // ���ο� �迭�� ������, count�� 0�̸� null ����

	}
	
	public void add(Book book) { // å�忡 å�� �߰���!
		Book[] olds = findBook(book.name); //���� å���ִ��� ã�ƺ��� ������ �� �迭�� ���Ϲް� , ������ null �迭�� ���Ϲ޾ƿ�
		if (olds.length > 0) { // �������� �ִٸ� �迭�� ���̰� 0���� Ŭ�״�,
			book.duplicateNumber = olds.length + 1; //�������� å�� �ߺ����� �����ִ�å�� +1�� ��������, ������� ������ 3���ε� �ѱǴ������� �������� ������ å�� �ߺ����� 4����
			if (olds.length == 1) // ����å�� �ѱ��̾��ٸ�
			olds[0].duplicateNumber = 1; //�����ִ�å�� �ߺ����� 1�̵ǵ�����. �� ������ ������ �ߺ��� å�� �˻������� ���� -> ����(2) -> ����(3) �̷������� �̸��� ��µ�
		}
		
		books[bookSize++] = book; //�������ִ� å�忡�� ���� �ε����� �� å�� �߰���
	}

	public void add(Member member) { // ȸ����ο� ȸ���� �߰���! ��ó�� ���� å�� ������ ������������, �������ִ� å�̳� �ߺ��� ���䶧���� å�� �ڵ尡 �� �������
		members[memberSize++] = member;
	}
	 

	public void run(int managerIndex) { // �Ŵ��� �ε����� �Ķ���ͷ� �Ѱܿ� 
		System.out.println(ID[managerIndex] + "�缭�� ȯ���մϴ�."); // �޾ƿ� �Ŵ��� �ε����� ���� ���⼭ �ߴ� �̸��� �ٸ��� �Ϸ���!
		now = LocalDate.now(); // ����ð� ���ϴ³��� 
		int month = now.getMonthValue();
		int today = now.getDayOfMonth();
		System.out.println("������ " + month + "�� " + today + "�� ���������ȸ�� �ִ� ���Դϴ�."); // ��¥�� ���� ��纯���� ��������
		int borrowCnt = 0; // ������ å���� 0���� �ʱⰪ�� 
		for (int i = 0; i < bookSize; i++) { // å�忡 �߰��� å���� �ݺ����� ���� ���鼭
			if (!(this.books[i].state).equals("���Ⱑ��")) { // state�� ���Ⱑ���� �ƴ� �ֵ��� ī������! �ʱ⿡ å init �Ҷ� å���� �Է¹���� ũ�� 2������ ����..!
				borrowCnt++;
			}
		}
		System.out.println("������ �̳������� �� " + borrowCnt + "�� �Դϴ�."); 
		
		boolean flage = true;
		while (flage) {
			System.out.println("-----------------------------------------");
			System.out.println("1. �˻� | 2. �űԵ����߰� | 3. ȸ������ | 4. �̳�����Ȯ��");
			System.out.println("5. �ְ��α⵵�� | 6. ������ | 7. ���� ");
			System.out.println("-----------------------------------------");
			System.out.print("�޴�����>");
			String menu = sc.nextLine();
			switch (menu) {
			case "1":
				do {
					System.out.print("å�̸��Է�>");
					String bookName = sc.nextLine();
					Book[] books = findBook(bookName); //�Է��� ������ �Ķ���͸� ���� findBook �޼ҵ�� �ѱ�� ã�� å�� �迭�� ��������
					if (books.length == 0) { // �������� �迭�� ���̰� 0�̸� å�� ���ٴ¼Ҹ�
						System.out.println("�˻� ����� �����ϴ�.");
					} else { // �迭�� 0�� �ƴ϶�� ���� å�� �ִٴ¼Ҹ�, �� ����ؼ� ������ å���� �ִٴ� �Ҹ�
						for (Book book : books) { //findBook �޼ҵ忡�� ���Ϲ��� �迭�߿���
							if (!book.state.equals("���Ⱑ��")) { // state�� ���Ⱑ���� �ƴ� å�� ������ ������, 
								System.out.println( //~ book.name)������ å�̸� �ϳ� �߰��ϴ� ���๮�ε�, ���׿����� ����߾��. å�� �ߺ����� 0���� ũ�� å�̸� ���� �ߺ����� ���� ���������ϰ�, �װ� �ƴϸ� �׳� å�̸��� ������.
										(book.duplicateNumber > 0 ? book.name + "(" + book.duplicateNumber + ")": book.name) + "[" + book.author + "] / " + book.position + " / "+ book.state + " " + book.date + "�� �ݳ�����)");
							} else { // state�� �������� å�� ������ ����!  
								System.out.println( //���� ���ϰ� �����ƿ�!
										(book.duplicateNumber > 0 ? book.name + "(" + book.duplicateNumber + ")": book.name) + "[" + book.author + "] / " + book.position + " / "+ book.state);
							}
						}
					}
					System.out.println("�߰� �˻� �Ͻðڽ��ϱ�? (Y/N)");
					String input = (sc.nextLine()).toUpperCase();
					if (input.equals("N")) {
						break;
					}
				} while (true);
				break;

			case "2":
				do {
					System.out.print("å�̸��Է�>");
					String name = sc.nextLine();
					System.out.print("����>");
					String author = sc.nextLine();
					System.out.print("��ġ>");
					String position = sc.nextLine();
					add(new Book(name, author, position, 0, "���Ⱑ��")); // ������ �Է¹��� �͵��� �� å���� �߰���. �� ��ü�� �����ϳ�, ������ ������ getBookCount -> findBook -> add ������ ���๮�� �Ϻ��ϰ� �����Ͻñ� ��õ�����!
					System.out.println("�Ű����� '" + name + "[" + author + "]" + " / " + position + "' �߰��Ǿ����ϴ�.");
					System.out.println("�߰� �Է� �Ͻðڽ��ϱ�? (Y/N)");
					String input = (sc.nextLine()).toUpperCase();
					if (input.equals("N")) {
						break;
					}
				} while (true);
				break;

			case "3":
				for (int i = 0; i < memberSize; i++) { //����� �������ŭ �ݺ����� �����ϰ�
					if (members[i].lateCnt >= 5) { // ����� �̳�Ƚ��(�׵��� �ʰԳ� Ƚ��)�� 5������ ũ�ų� ������
						System.out.println( // �Ʒ� �޽����� �����
								members[i].name + " " + "�̳�Ƚ��: " + members[i].lateCnt + " >> ������ �ߴܵ� ȸ���Դϴ�.");
					} else { // �׷��������� �׳� �̸��� �̳��ϼ��� ����ϰ���.
						System.out.println(members[i].name + " " + "�̳�Ƚ��: " + members[i].lateCnt);
					}
				}
				break;
				
			case "4":
				System.out.println("������ " + month + "�� " + today + "�� ��������� �ѱ� ����Դϴ�.");
				for (int i = 0; i < bookSize; i++) { // �������ִ� å����ŭ �ݺ����� ���鼭
					if (!(books[i].state).equals("���Ⱑ��")) { // ���Ⱑ�� ���°� �ƴ϶��, �� �������ξֵ��� �θ��¸�
						now = LocalDate.now(); //��¥ �ִ� �ſ���
						int lateDayCnt = today - books[i].date; // �ݳ� �������� ����� ( ���ó�¥ - ������ ��¥)
						if (lateDayCnt >= 7) { // �ݳ� �������� 7���� ũ�ų� ������ �Ʒ� �޼��� ���
							System.out.println(books[i].name + "[" + books[i].author + "] / "
									+ books[i].state + " " + books[i].date + "��) " + lateDayCnt + "��° �̹ݳ���");
						}
					}
				}

				break;

			case "5": 
				// �� ����������� å�� �ּ� å�� ������ ����� �ʿ� -> ���� ��� ���� -> �ش�Ⱓ���� �������� Ȯ�� �ٵ� ���� �Ƿ��� �ȵǾ.. 100�� ������ ����
				// �̸��� ������쿡�� �뿩���� �ջ��ϰ� ���� �뿩�� ī���� ������ ����ϴ� �������
				
				// ���� �ؿ��� ���� å�� �����뿩���� �����ִ� ���๮�Դϴ�!
				Book[] collectedBooks = new Book[bookSize]; // å���ִ� å�̶� ���� ���� �� �迭�� �����
				int collectedBookIndex = 0;
				Book sameNameBook; // '���� �̸��� ���� å���� ���� �뿩���� ��ģ å'���� ������� �����Դϴ�. 
				for (int i = 0; i < bookSize; i++) { 
					sameNameBook = null; // i��° å�� ���� å�� ã���� �� ����å�� ã������ �� ������ null�� �ʱ�ȭ �������.
					for(int j=0;j<collectedBookIndex;++j)
						if(collectedBooks[j].name.equals(books[i].name)) { //j��°å�� �̸��� i��° å�� �̸��� ���ٸ� 
							sameNameBook = collectedBooks[j]; // ���� �̸��� ���� å�� j��� ��.
							break;
						}
					if(sameNameBook==null) { // ���� �̸��� ���� å�� ���ٸ� 
						collectedBooks[collectedBookIndex++] = new Book(books[i]); // �÷��Ǻ��̶�� �迭�� �� å�� �־���
					} else { // ���� �̸��� ���°� �ִٸ�
						sameNameBook.cnt+=books[i].cnt; //���� �̸��� ���� å�� �����뿩�����ٰ� ��å�� �뿩���� ������ 
					}
				}
				
				//����� ���� �뿩���� ���߾� ��迭�ϴ� �����Դϴ�!
				Book[] sortedBooks = new Book[collectedBookIndex];
				for(int i=0;i<collectedBookIndex;++i)
					sortedBooks[i]=collectedBooks[i];
				Book temp;
				for (int i = 0; i < collectedBookIndex; i++) {
					for (int j = i + 1; j < collectedBookIndex; j++) {
						if (sortedBooks[i].cnt <= sortedBooks[j].cnt) { // ���� �뿩���� ���߾�
							temp = sortedBooks[j]; // �迭��ü ������ �ٲ�!
							sortedBooks[j] = sortedBooks[i];
							sortedBooks[i] = temp;
						}
					}
				}

				for (int i = 0; i < collectedBookIndex; i++) {
					System.out.println((i + 1) + "��: " + sortedBooks[i].name + "[" + sortedBooks[i].author + "] / "
							+ sortedBooks[i].cnt);
				}
				break;

			case "6": //�߰��Ѵ����� �ٽ� �� �߰��ϰڳĴ� ���� --> �޴����̵��ϴ� ��� �߰�
				do {
					System.out.print("��¥(��)>");
					String month1 = sc.nextLine();
					System.out.print("��¥(��)>");
					int inputDay = Integer.parseInt(sc.nextLine());
					System.out.print("����̸�>");
					String eventName = sc.nextLine();

					System.out.println(month1 + "�� " + inputDay + "�� �� " + eventName + "��(��) �߰��ұ��?(Y/N)");
					String input = sc.nextLine().toUpperCase();
					;
					if (input.equals("Y")) {
						System.out.println(month1 + "�� " + inputDay + "�� �� " + eventName + "��(��) �˷��帮�ڽ��ϴ�.");
					}
					
					System.out.println("�߰� �Է� �Ͻðڽ��ϱ�? (Y/N)");
					input = sc.nextLine().toUpperCase();
					if (input.equals("N")) { 
						break;
					}
					
				} while (true);

				break;

			case "7":
				flage = false;
				break;

			default:
				System.out.println("�޴��� �ٽ� �������ּ���. ");
			}

		}
	}

}
