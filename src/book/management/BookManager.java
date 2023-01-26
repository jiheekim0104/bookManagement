package book.management;

import java.time.LocalDate;
import java.util.Scanner;

public class BookManager {
	private static final int BOOK_ADD_SIZE = 100; // ���������� �����Ҽ��ִ� �� å�� �Ǽ�
	private static final int MEMBER_ADD_SIZE = 100; // ���α׷��� �����Ҽ��ִ� �� ȸ����

	private static final String[] ID = { "������", "������", "�̼���" };
	private static final String[] PASSWORD = { "0104", "1111", "2222" };

	private Book[] books; // å���� �迭�� ������
	private int bookSize;

	private Member[] members;
	private int memberSize;
	Scanner sc;
	LocalDate now;

	public BookManager() {
		this.books = new Book[BOOK_ADD_SIZE]; // ���������� �����Ҽ��ִ� �� å�� �Ǽ��� å �迭 �ʱⰪ �Ҵ�
		this.bookSize = 0;// ���α׷��� �ԷµǾ� �������ִ� å�� ���� ǥ���ϴ� ����, �ʱ⿡�� å�� ������ 0���� �Ҵ�
		this.members = new Member[MEMBER_ADD_SIZE]; // ���α׷��� �����Ҽ��ִ� �� ȸ������ ��� �迭 �ʱⰪ �Ҵ�
		this.memberSize = 0;// ���α׷��� �ԷµǾ� �������ִ� ȸ���� ���� ǥ���ϴ� ����, �ʱ⿡�� ȸ���� ������ 0���� �Ҵ�
		sc = new Scanner(System.in);
	}

	public int login() { // �缭 ���� & ��� �Է¹ް� ������ TRUE ����
		System.out.print("ID: ");
		String id = sc.nextLine();
		System.out.print("PASSWORD: ");
		String password = sc.nextLine();

		int index = -1;
		for (int i = 0; i < ID.length; i++) {
			if (id.equals(ID[i])) {
				index = i;
			}
		}
		if (index < 0)
			return -1;
		if (password.equals(PASSWORD[index]))
			return index;
		return -2;
	}

	public void init() {
		// �ʱ� å �ֱ� (å�̸�,�۰�,��ġ,�������)
		add(new Book("����", "������", "��.1.11", 1, "������(�ռ���/1��", 18, "�� �ݳ�����)"));
		add(new Book("����", "������", "��.1.12", 2, "������(�̳���/1��", 20, "�� �ݳ�����)"));
		add(new Book("����", "������", "��.1.13", 1, "���Ⱑ��"));
		add(new Book("������̺�", "���̺�", "��.0.10", 11, "������(�����/1��", 27, "�� �ݳ�����)"));
		add(new Book("���Ժ���", "������", "��.1.13", 21, "���Ⱑ��"));
		add(new Book("���۷θ�", "������", "��.1.13", 25, "������(�ڿ���/1��", 10, "�� �ݳ�����)"));
		// ...
		// ...

		// �ʱ� ȸ�� �ֱ�(ȸ����)
		add(new Member("�ռ���", "�̳�Ƚ��: ", 1));
		add(new Member("�����", "�̳�Ƚ��: ", 0));
		add(new Member("�ڿ���", "�̳�Ƚ��: ", 5));
		add(new Member("������", "�̳�Ƚ��: ", 0));
		add(new Member("�ֿ���", "�̳�Ƚ��: ", 3));
		add(new Member("�̳���", "�̳�Ƚ��: ", 0));
		add(new Member("�嵵��", "�̳�Ƚ��: ", 5));
		// ...

	}

	public void add(Book book) {
		Book[] olds = findBook(book.name);
		if (olds.length > 0) {
			book.duplicateNumber = olds.length + 1;
			if (olds.length == 1)
				olds[0].duplicateNumber = 1;
		}
		if (books.length == bookSize) {
			Book[] newBooks = new Book[bookSize + BOOK_ADD_SIZE];
			for (int i = 0; i < bookSize; ++i)
				newBooks[i] = books[i];
		}
		books[bookSize++] = book;
	}

	public void add(Member member) {
		Member[] oldsmembers = findMember(member.name);
		if (oldsmembers.length > 0) {
			member.duplicateNumber = oldsmembers.length + 1;
			if (oldsmembers.length == 1)
				oldsmembers[0].duplicateNumber = 1;
		}
		if (members.length == memberSize) {
			Member[] newmembers = new Member[memberSize + MEMBER_ADD_SIZE];
			for (int i = 0; i < memberSize; ++i)
				newmembers[i] = members[i];
		}
		members[memberSize++] = member;
	}

	public Book[] findBook(String bookName) { // å ã��, (String name)�� �˻��� å ������ �Ǿ����! ���⼭ ���� �̿� ���߱⶧���� �߰� �۾��ؾߵ�.
		int count = getBookCount(bookName);
		Book[] books = new Book[count];

		int index = 0;
		for (int i = 0; i < bookSize; ++i) {
			if (this.books[i].name.equals(bookName))
				books[index++] = this.books[i];
		}
		return books;

	}

	public Member[] findMember(String name) { // ��� ã��, ���� å ã��� ����� ����. ����!!
		int count = getMemberCount(name);
		Member[] members = new Member[count];

		int index = 0;
		for (int i = 0; i < memberSize; ++i) {
			if (this.members[i].name.equals(name))
				members[index++] = this.members[i];
		}
		return members;

	}

	private int getBookCount(String bookName) {
		int count = 0;
		for (int i = 0; i < bookSize; ++i)
			if (books[i].name.equals(bookName))
				count++; // �˻��� å�� �̸��� �������ִ��� ã�ƺ���, ������ å ���� ���Ϲ޾ƿ�
		return count; // ������ ������ ���� ���� ����
	}

	private int getMemberCount(String name) {
		int count = 0;
		for (int i = 0; i < memberSize; ++i)
			if (members[i].name.equals(name))
				count++;
		return count;
	}



	public void run(int managerIndex) {
		// �ݺ��� �̿��ؼ� �޴� ���, ��ȣ �Է��ϸ� �ش� ������ ����ɼ��ֵ��� �۾��������!
		System.out.println(ID[managerIndex] + "�缭�� ȯ���մϴ�.");
		now = LocalDate.now();
		int month = now.getMonthValue();
		int today = now.getDayOfMonth();
		System.out.println("������ " + month + "�� " + today + "�� ���������ȸ�� �ִ� ���Դϴ�.");
		int borrowCnt = 0;
		for (int i = 0; i < bookSize; i++) {
			if (!(this.books[i].state).equals("���Ⱑ��")) {
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
					Book[] books = findBook(bookName);
					if (books.length == 0) {
						System.out.println("�˻� ����� �����ϴ�.");
					} else {
						for (Book book : books) {
							if (!book.state.equals("���Ⱑ��")) {
								System.out.println(
										(book.duplicateNumber > 0 ? book.name + "(" + book.duplicateNumber + ")"
												: book.name) + "[" + book.author + "] / " + book.position + " / "
												+ book.state + " " + book.date + book.msg);
							} else {
								System.out.println(
										(book.duplicateNumber > 0 ? book.name + "(" + book.duplicateNumber + ")"
												: book.name) + "[" + book.author + "] / " + book.position + " / "
												+ book.state);
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
					add(new Book(name, author, position, 0, "���Ⱑ��"));
					System.out.println("�Ű����� '" + name + "[" + author + "]" + " / " + position + "' �߰��Ǿ����ϴ�.");
					System.out.println("�߰� �Է� �Ͻðڽ��ϱ�? (Y/N)");
					String input = (sc.nextLine()).toUpperCase();
					if (input.equals("N")) {
						break;
					}
				} while (true);
				break;

			case "3":
				for (int i = 0; i < memberSize; i++) {
					if (members[i].lateCnt >= 5) {
						System.out.println(
								members[i].name + " " + members[i].msg + members[i].lateCnt + " >> ������ �ߴܵ� ȸ���Դϴ�.");
					} else {
						System.out.println(members[i].name + " " + members[i].msg + members[i].lateCnt);
					}
				}
				break;
			case "4":
				for (int i = 0; i < bookSize; i++) {
					if (!(this.books[i].state).equals("���Ⱑ��")) {
						now = LocalDate.now();
						int lateDayCnt = today - this.books[i].date;
						if (lateDayCnt >= 7) {
							System.out.println(this.books[i].name + "[" + this.books[i].author + "] / "
									+ this.books[i].state + " " + this.books[i].date + "��) " + lateDayCnt + "��° �̹ݳ���");
						}
					}
				}

				break;

			case "5": // �� ����������� å�� �ּ� å�� ������ ����� �ʿ� -> ���� ��� ���� -> �ش�Ⱓ���� �������� Ȯ��
				// ���ó�¥ -7���� -1���� ���� �뿩�� ī���� ������ ���, �̸��� ������쿡�� �ջ�
				Book[] collectedBooks = new Book[bookSize];
				int collectedBookIndex = 0;
				Book sameNameBook;
				for (int i = 0; i < bookSize; i++) {
					//å�˻�;
					sameNameBook = null;
					for(int j=0;j<collectedBookIndex;++j)
						if(collectedBooks[j].name.equals(this.books[i].name)) {
							sameNameBook = collectedBooks[j];
							break;
						}
					if(sameNameBook==null) {
						collectedBooks[collectedBookIndex++] = new Book(this.books[i]);
					} else {
						sameNameBook.cnt+=this.books[i].cnt;
					}
				}
				Book[] sortedBooks = new Book[collectedBookIndex];
				for(int i=0;i<collectedBookIndex;++i)
					sortedBooks[i]=collectedBooks[i];
				Book temp;
				for (int i = 0; i < collectedBookIndex; i++) {
					for (int j = i + 1; j < collectedBookIndex; j++) {
						if (sortedBooks[i].cnt <= sortedBooks[j].cnt) {
							temp = sortedBooks[j];
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