package book.management;

public class Main {
	public static void main(String[] args) {
		System.out.println("-----------------------------------------");
		System.out.println("***************도서관리 프로그램*************");
		System.out.println("-----------------------------------------");
		
		
		BookManager bookManager = new BookManager(); // BookManager 클래스 메소드 사용을 위해 생성자만듬
		int managerIndex = bookManager.login(); // BookManager 클래스에서 login 메소드를 실행해서 받아온값을 매니저인덱스라고함
		if(managerIndex>=0) { // 리턴받아온 매니저인덱스가 0보다 크거나 같으면 로그인 성공			
			bookManager.init(); //BookManager 클래스에서 init 메소드를 실행 ==> 초기화, 기본책정보 입력, 회원정보 입력
			bookManager.run(managerIndex);	//BookManager 클래스에서 run 메소드를 실행
		} else {
			System.out.println("로그인 실패"); // 리턴받아온 매니저인덱스가 0보다 작으면 로그인 실패
		}
		System.out.println("프로그램 종료");
	}
}
