package book.management;

import java.time.LocalDate;
import java.util.Scanner;

public class BookManager {

	private static final String[] ID = { "김지희", "박정은", "이수지" };
	private static final String[] PASSWORD = { "0104", "1111", "2222" };

	private Book[] books; // 책들을 배열로 관리함, 책장같은 개념!
	private int bookSize; // 가지고있는 책 수

	private Member[] members; // 멤버들을 배열로 관리함,회원장부같은 개념!
	private int memberSize; // 가지고있는 회원 수
	Scanner sc;
	LocalDate now; //이건 날짜 가져오려고 사용했어요, 위에 import랑 같이 사용됩니다. 

	public BookManager() {
		this.books = new Book[100]; // 도서관에서 보관할수있는 총 책은 100권
		this.bookSize = 0;// 프로그램상에 입력되어 가지고있는 책의 수, 초기 0
		this.members = new Member[100]; // 프로그램에 저장할수있는 총 회원수는 100명
		this.memberSize = 0;// 프로그램상에 입력되어 가지고있는 회원의 수, 초기 0
	}

	public int login() { // 사서 계정 & 비번 입력받고 맞으면 TRUE 리턴, 아아디 틀리면 -1, 비번 틀리면 -2 리턴하여 로그인 결과가 음수인경우 로그인 실패
		sc=new Scanner(System.in);
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
		add(new Book("봄봄", "김유정", "바.1.11", 1, "대출중(손석구/1월", 18));
		add(new Book("봄봄", "김유정", "바.1.12", 2, "대출중(이나연/1월", 20));
		add(new Book("봄봄", "김유정", "바.1.13", 1, "대출가능"));
		add(new Book("러브다이브", "아이브", "라.0.10", 11, "대출중(장원영/1월", 27));
		add(new Book("하입보이", "뉴진스", "하.1.13", 21, "대출가능"));
		add(new Book("더글로리", "송혜교", "다.1.13", 25, "대출중(박연진/1월", 10));
		// ...
		// ...

		// 초기 회원 넣기(회원명,미납횟수 숫자)
		add(new Member("손석구",1));
		add(new Member("장원영",0));
		add(new Member("박연진",5));
		add(new Member("문동은",0));
		add(new Member("주여정",3));
		add(new Member("이나연",0));
		add(new Member("장도연",5));
		// ...

	}

	private int getBookCount(String bookName) { // 파라미터로 받아온 책 이름과 같은게있는지 카운팅하는 메소드
		int count = 0; // 카운팅 초기값은 0
		for (int i = 0; i < bookSize; ++i) // 책장에 담겨있는 책들을 인덱스 0부터 끝까지 찾아봄 
			if (books[i].name.equals(bookName)) // 해당 인덱스를 가지는 책 이름이 파라미터로 받아온 책이름과 같다면
				count++; // 카운팅 추가
		return count; // 카운팅값 리턴 , 즉 찾으려는 책과 중복된 책숫자를 리턴한다는소리
	}
	
	public Book[] findBook(String bookName) { // 파라미터로 받아온 책이 있는지 찾고 책을 돌려주는 메소드
		int count = getBookCount(bookName); // 위에있는 getBookCount를 통해 찾아보려는 책이 기존 책장에 몇권이있는지 그 수를 받아옴
		Book[] books = new Book[count]; // 그 수를 가지고 새로운 배열을 생성함

		int index = 0;
		for (int i = 0; i < bookSize; ++i) { 
			if (this.books[i].name.equals(bookName)) // 책장에 있는 책 이름과 찾으려는 책이름이 같다면
				books[index++] = this.books[i]; //새로만든 배열에다가 그 책을 넣어줌
		}
		return books; // 새로운 배열을 리턴함, count가 0이면 null 리턴

	}
	
	public void add(Book book) { // 책장에 책을 추가함!
		Book[] olds = findBook(book.name); //같은 책이있는지 찾아보고 있으면 그 배열을 리턴받고 , 없으면 null 배열을 리턴받아옴
		if (olds.length > 0) { // 같은색이 있다면 배열의 길이가 0보다 클테니,
			book.duplicateNumber = olds.length + 1; //넣으려는 책의 중복수를 원래있던책수 +1로 설정해줌, 예를들어 봄봄이 3권인데 한권더넣으면 마지막에 들어오는 책의 중복수가 4가됨
			if (olds.length == 1) // 같은책이 한권이었다면
			olds[0].duplicateNumber = 1; //원래있던책의 중복수가 1이되도록함. 이 두줄이 빠지면 중복된 책을 검색했을때 봄봄 -> 봄봄(2) -> 봄봄(3) 이런식으로 이름이 출력됨
		}
		
		books[bookSize++] = book; //가지고있던 책장에서 다음 인덱스에 이 책을 추가함
	}

	public void add(Member member) { // 회원장부에 회원을 추가함! 이처럼 위에 책도 간단히 넣을수있으나, 기존에있던 책이나 중복수 개념때문에 책은 코드가 더 길어졌음
		members[memberSize++] = member;
	}
	 

	public void run(int managerIndex) { // 매니저 인덱스를 파라미터로 넘겨옴 
		System.out.println(ID[managerIndex] + "사서님 환영합니다."); // 받아온 매니저 인덱스에 따라 여기서 뜨는 이름이 다르게 하려고!
		now = LocalDate.now(); // 현재시간 구하는내용 
		int month = now.getMonthValue();
		int today = now.getDayOfMonth();
		System.out.println("오늘은 " + month + "월 " + today + "일 독서퀴즈대회가 있는 날입니다."); // 날짜에 따른 행사변경은 구현못함
		int borrowCnt = 0; // 빌려간 책수를 0으로 초기값줌 
		for (int i = 0; i < bookSize; i++) { // 책장에 추가된 책들을 반복문을 통해 돌면서
			if (!(this.books[i].state).equals("대출가능")) { // state가 대출가능이 아닌 애들을 카운팅함! 초기에 책 init 할때 책마다 입력방식이 크게 2가지인 이유..!
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
					Book[] books = findBook(bookName); //입력한 제목을 파라미터를 통해 findBook 메소드로 넘기고 찾은 책을 배열로 돌려받음
					if (books.length == 0) { // 돌려받은 배열의 길이가 0이면 책이 없다는소리
						System.out.println("검색 결과가 없습니다.");
					} else { // 배열이 0이 아니라면 같은 책이 있다는소리, 즉 출력해서 보여줄 책들이 있다는 소리
						for (Book book : books) { //findBook 메소드에서 리턴받은 배열중에서
							if (!book.state.equals("대출가능")) { // state가 대출가능이 아닌 책은 다음을 따르고, 
								System.out.println( //~ book.name)까지가 책이름 하나 뜨게하는 실행문인데, 삼항연산자 사용했어요. 책의 중복수가 0보다 크면 책이름 옆에 중복수가 같이 나오도록하고, 그게 아니면 그냥 책이름만 나오게.
										(book.duplicateNumber > 0 ? book.name + "(" + book.duplicateNumber + ")": book.name) + "[" + book.author + "] / " + book.position + " / "+ book.state + " " + book.date + "일 반납예정)");
							} else { // state가 대출중인 책은 다음을 따름!  
								System.out.println( //위위 줄하고 설명같아요!
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
					add(new Book(name, author, position, 0, "대출가능")); // 위에서 입력받은 것들을 새 책으로 추가함. 이 자체는 간단하나, 위에서 등장한 getBookCount -> findBook -> add 까지의 실행문을 완벽하게 이해하시길 추천드려요!
					System.out.println("신간도서 '" + name + "[" + author + "]" + " / " + position + "' 추가되었습니다.");
					System.out.println("추가 입력 하시겠습니까? (Y/N)");
					String input = (sc.nextLine()).toUpperCase();
					if (input.equals("N")) {
						break;
					}
				} while (true);
				break;

			case "3":
				for (int i = 0; i < memberSize; i++) { //저장된 멤버수만큼 반복문을 실행하고
					if (members[i].lateCnt >= 5) { // 멤버의 미납횟수(그동안 늦게낸 횟수)가 5번보다 크거나 같으면
						System.out.println( // 아래 메시지를 출력함
								members[i].name + " " + "미납횟수: " + members[i].lateCnt + " >> 대출이 중단된 회원입니다.");
					} else { // 그렇지않으면 그냥 이름과 미납일수를 출력하게함.
						System.out.println(members[i].name + " " + "미납횟수: " + members[i].lateCnt);
					}
				}
				break;
				
			case "4":
				System.out.println("오늘은 " + month + "월 " + today + "일 대출기한을 넘긴 목록입니다.");
				for (int i = 0; i < bookSize; i++) { // 가지고있는 책수만큼 반복문을 돌면서
					if (!(books[i].state).equals("대출가능")) { // 대출가능 상태가 아니라면, 즉 대출중인애들을 부르는말
						now = LocalDate.now(); //날짜 넣는 거에용
						int lateDayCnt = today - books[i].date; // 반납 지연일을 계산함 ( 오늘날짜 - 빌려간 날짜)
						if (lateDayCnt >= 7) { // 반납 지연일이 7보다 크거나 같으면 아래 메세지 출력
							System.out.println(books[i].name + "[" + books[i].author + "] / "
									+ books[i].state + " " + books[i].date + "일) " + lateDayCnt + "일째 미반납중");
						}
					}
				}

				break;

			case "5": 
				// 이 기능을쓰려면 책을 최소 책을 빌리는 기능이 필요 -> 빌린 기록 누적 -> 해당기간동안 누적순위 확인 근데 아직 실력이 안되어서.. 100퍼 구현은 못함
				// 이름이 같은경우에는 대여수를 합산하고 누적 대여한 카운팅 순위로 출력하는 방식을씀
				
				// 여기 밑에가 같은 책의 누적대여수를 합해주는 실행문입니다!
				Book[] collectedBooks = new Book[bookSize]; // 책에있는 책이랑 같은 수의 새 배열을 만들고
				int collectedBookIndex = 0;
				Book sameNameBook; // '같은 이름을 갖는 책들의 누적 대여수를 합친 책'으로 사용해줄 변수입니다. 
				for (int i = 0; i < bookSize; i++) { 
					sameNameBook = null; // i번째 책과 같은 책을 찾고나서 그 다음책을 찾을때는 이 변수가 null로 초기화 해줘야함.
					for(int j=0;j<collectedBookIndex;++j)
						if(collectedBooks[j].name.equals(books[i].name)) { //j번째책의 이름과 i번째 책의 이름이 같다면 
							sameNameBook = collectedBooks[j]; // 같은 이름을 갖는 책을 j라고 함.
							break;
						}
					if(sameNameBook==null) { // 같은 이름을 갖는 책이 없다면 
						collectedBooks[collectedBookIndex++] = new Book(books[i]); // 컬랙션북이라는 배열에 이 책을 넣어줌
					} else { // 같은 이름을 갖는게 있다면
						sameNameBook.cnt+=books[i].cnt; //같은 이름을 갖는 책의 누적대여수에다가 이책의 대여수를 더해줌 
					}
				}
				
				//여기는 누적 대여수에 맞추어 재배열하는 내용입니다!
				Book[] sortedBooks = new Book[collectedBookIndex];
				for(int i=0;i<collectedBookIndex;++i)
					sortedBooks[i]=collectedBooks[i];
				Book temp;
				for (int i = 0; i < collectedBookIndex; i++) {
					for (int j = i + 1; j < collectedBookIndex; j++) {
						if (sortedBooks[i].cnt <= sortedBooks[j].cnt) { // 누적 대여수에 맞추어
							temp = sortedBooks[j]; // 배열전체 순서를 바꿈!
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
