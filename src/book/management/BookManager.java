package book.management;

import java.time.LocalDate;
import java.util.Scanner;

public class BookManager {
	private static final int BOOK_ADD_SIZE = 100; // 도서관에서 보관할수있는 총 책의 권수
	private static final int MEMBER_ADD_SIZE = 100; // 프로그램에 저장할수있는 총 회원수

	private static final String[] ID = { "김지희", "박정은", "이수지" };
	private static final String[] PASSWORD = { "0104", "1111", "2222" };

	private Book[] books; // 책들을 배열로 관리함, 책장같은 개념!
	private int bookSize; // 가지고있는 책 수

	private Member[] members; // 멤버들을 배열로 관리함,회원장부같은 개념!
	private int memberSize; // 가지고있는 회원 수
	Scanner sc;
	LocalDate now;

	public BookManager() {
		this.books = new Book[BOOK_ADD_SIZE]; // 도서관에서 보관할수있는 총 책의 권수로 책 배열 초기값 할당
		this.bookSize = 0;// 프로그램상에 입력되어 가지고있는 책의 수, 초기 0
		this.members = new Member[MEMBER_ADD_SIZE]; // 프로그램에 저장할수있는 총 회원수로 멤버 배열 초기값 할당
		this.memberSize = 0;// 프로그램상에 입력되어 가지고있는 회원의 수, 초기 0
	}

	public int login() { // 사서 계정 & 비번 입력받고 맞으면 TRUE 리턴, 아아디 틀리면 -1, 비번 틀리면 -2 리턴하여 로그인 결과가 음수인경우 로그인 실패
		System.out.print("ID: ");
		String id = sc.nextLine();
		System.out.print("PASSWORD: ");
		String password = sc.nextLine();

		int index = -1; 
		for (int i = 0; i < ID.length; i++) { // 맞으면 해당 인덱스를 리턴, 0:김지희, 1:박정은, 2:이수지
			if (id.equals(ID[i])) {
				index = i;
			}
		}
		if (index < 0)
			return -1; // 아이디가 틀리면 -1 리턴
		if (password.equals(PASSWORD[index])) // 아이디에 해당하는 인덱스를 비밀번호 인덱스랑 대조하여 맞으면 해당 인덱스 리턴
			return index;
		return -2; // 아이디에 해당하는 인덱스랑 비교해서 비밀번호가 틀리면 -2 리턴
	}

	public void init() {
		// 초기 책 넣기 (책이름,작가,위치,누적대여수,대출상태) 또는  (책이름,작가,위치,누적대여수,대출중 ~~~) 이렇게 두가지 형태임
		add(new Book("봄봄", "김유정", "바.1.11", 1, "대출중(손석구/1월", 18, "일 반납예정)"));
		add(new Book("봄봄", "김유정", "바.1.12", 2, "대출중(이나연/1월", 20, "일 반납예정)"));
		add(new Book("봄봄", "김유정", "바.1.13", 1, "대출가능"));
		add(new Book("러브다이브", "아이브", "라.0.10", 11, "대출중(장원영/1월", 27, "일 반납예정)"));
		add(new Book("하입보이", "뉴진스", "하.1.13", 21, "대출가능"));
		add(new Book("더글로리", "송혜교", "다.1.13", 25, "대출중(박연진/1월", 10, "일 반납예정)"));
		// ...
		// ...

		// 초기 회원 넣기(회원명,미납횟수(msg),미납횟수 숫자)
		add(new Member("손석구", "미납횟수: ", 1));
		add(new Member("장원영", "미납횟수: ", 0));
		add(new Member("박연진", "미납횟수: ", 5));
		add(new Member("문동은", "미납횟수: ", 0));
		add(new Member("주여정", "미납횟수: ", 3));
		add(new Member("이나연", "미납횟수: ", 0));
		add(new Member("장도연", "미납횟수: ", 5));
		// ...

	}

	private int getBookCount(String bookName) { // 파라미터로 받아온 책 이름과 같은게있는지 카운팅하는 메소드
		int count = 0; // 카운팅 초기값은 0
		for (int i = 0; i < bookSize; ++i) // 책장에 담겨있는 책들을 인덱스 0부터 끝까지 찾아봄 
			if (books[i].name.equals(bookName)) // 해당 인덱스를 가지는 책 이름이 파라미터로 받아온 책이름과 같다면
				count++; // 카운팅 추가
		return count; // 카운팅값 리턴
	}
	
	public Book[] findBook(String bookName) { // 파라미터로 받아온 책이 있는지 찾아보는 메소드
		int count = getBookCount(bookName); // 위에있는 getBookCount를 통해 찾아보려는 책이 기존 책장에 몇권이있는지 그 수를 받아옴
		Book[] books = new Book[count]; // 그 수를 가지고 배열을 생성함

		int index = 0;
		for (int i = 0; i < bookSize; ++i) {
			if (this.books[i].name.equals(bookName))
				books[index++] = this.books[i];
		}
		return books;

	}
	
	public void add(Book book) {
		Book[] olds = findBook(book.name); //같은 책이있는지 찾아봄, 없으면 빈 배열이됨. --> 바로 추가 & 배열내용이 있다면 배열길이에따라 중복수 부여
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

	private int getMemberCount(String name) {
		int count = 0;
		for (int i = 0; i < memberSize; ++i)
			if (members[i].name.equals(name))
				count++;
		return count;
	}
	
	public Member[] findMember(String name) { // 멤버 찾기, 위에 책 찾기와 방식이 같음. 도전!!
		int count = getMemberCount(name);
		Member[] members = new Member[count];

		int index = 0;
		for (int i = 0; i < memberSize; ++i) {
			if (this.members[i].name.equals(name))
				members[index++] = this.members[i];
		}
		return members;

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
	 

	public void run(int managerIndex) {
		System.out.println(ID[managerIndex] + "사서님 환영합니다.");
		now = LocalDate.now();
		int month = now.getMonthValue();
		int today = now.getDayOfMonth();
		System.out.println("오늘은 " + month + "월 " + today + "일 독서퀴즈대회가 있는 날입니다.");
		int borrowCnt = 0;
		for (int i = 0; i < bookSize; i++) {
			if (!(this.books[i].state).equals("대출가능")) {
				borrowCnt++;
			}
		}
		System.out.println("오늘의 미납도서는 총 " + borrowCnt + "권 입니다.");
		boolean flage = true;
		while (flage) {
			System.out.println("-----------------------------------------");
			System.out.println("1. 검색 | 2. 신규도서추가 | 3. 회원관리 | 4. 미납도서확인");
			System.out.println("5. 주간인기도서 | 6. 행사관리 | 7. 종료 ");
			System.out.println("-----------------------------------------");
			System.out.print("메뉴선택>");
			String menu = sc.nextLine();
			switch (menu) {
			case "1":
				do {
					System.out.print("책이름입력>");
					String bookName = sc.nextLine();
					Book[] books = findBook(bookName);
					if (books.length == 0) {
						System.out.println("검색 결과가 없습니다.");
					} else {
						for (Book book : books) {
							if (!book.state.equals("대출가능")) {
								System.out.println(
										(book.duplicateNumber > 0 ? book.name + "(" + book.duplicateNumber + ")": book.name) + "[" + book.author + "] / " + book.position + " / "+ book.state + " " + book.date + book.msg);
							} else {
								System.out.println(
										(book.duplicateNumber > 0 ? book.name + "(" + book.duplicateNumber + ")": book.name) + "[" + book.author + "] / " + book.position + " / "+ book.state);
							}
						}
					}
					System.out.println("추가 검색 하시겠습니까? (Y/N)");
					String input = (sc.nextLine()).toUpperCase();
					if (input.equals("N")) {
						break;
					}
				} while (true);
				break;

			case "2":
				do {
					System.out.print("책이름입력>");
					String name = sc.nextLine();
					System.out.print("저자>");
					String author = sc.nextLine();
					System.out.print("위치>");
					String position = sc.nextLine();
					add(new Book(name, author, position, 0, "대출가능"));
					System.out.println("신간도서 '" + name + "[" + author + "]" + " / " + position + "' 추가되었습니다.");
					System.out.println("추가 입력 하시겠습니까? (Y/N)");
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
								members[i].name + " " + members[i].msg + members[i].lateCnt + " >> 대출이 중단된 회원입니다.");
					} else {
						System.out.println(members[i].name + " " + members[i].msg + members[i].lateCnt);
					}
				}
				break;
				
			case "4":
				System.out.println("오늘은 " + month + "월 " + today + "일 대출기한을 넘긴 목록입니다.");
				for (int i = 0; i < bookSize; i++) {
					if (!(this.books[i].state).equals("대출가능")) {
						now = LocalDate.now();
						int lateDayCnt = today - this.books[i].date;
						if (lateDayCnt >= 7) {
							System.out.println(this.books[i].name + "[" + this.books[i].author + "] / "
									+ this.books[i].state + " " + this.books[i].date + "일) " + lateDayCnt + "일째 미반납중");
						}
					}
				}

				break;

			case "5": 
				// 이 기능을쓰려면 책을 최소 책을 빌리는 기능이 필요 -> 빌린 기록 누적 -> 해당기간동안 누적순위 확인
				// 오늘날짜 -7부터 -1까지 누적 대여한 카운팅 순위로 출력, 이름이 같은경우에는 합산
				Book[] collectedBooks = new Book[bookSize];
				int collectedBookIndex = 0;
				Book sameNameBook;
				for (int i = 0; i < bookSize; i++) {
					//책검사;
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
					System.out.println((i + 1) + "위: " + sortedBooks[i].name + "[" + sortedBooks[i].author + "] / "
							+ sortedBooks[i].cnt);
				}
				break;

			case "6": //추가한다음에 다시 또 추가하겠냐는 물음 --> 메뉴로이동하는 기능 추가
				do {
					System.out.print("날짜(월)>");
					String month1 = sc.nextLine();
					System.out.print("날짜(일)>");
					int inputDay = Integer.parseInt(sc.nextLine());
					System.out.print("행사이름>");
					String eventName = sc.nextLine();

					System.out.println(month1 + "월 " + inputDay + "일 에 " + eventName + "를(을) 추가할까요?(Y/N)");
					String input = sc.nextLine().toUpperCase();
					;
					if (input.equals("Y")) {
						System.out.println(month1 + "월 " + inputDay + "일 에 " + eventName + "를(을) 알려드리겠습니다.");
					}
					
					System.out.println("추가 입력 하시겠습니까? (Y/N)");
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
				System.out.println("메뉴를 다시 선택해주세요. ");
			}

		}
	}

}
