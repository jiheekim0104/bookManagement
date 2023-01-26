package book.management;

public class Main {
	public static void main(String[] args) {
		System.out.println("-----------------------------------------");
		System.out.println("***************도서관리 프로그램*************");
		System.out.println("-----------------------------------------");
		
		
		BookManager bookManager = new BookManager();
		int managerIndex = bookManager.login();
		if(managerIndex>=0) {			
			bookManager.init(); //데이터 초기화, 기본책정보 입력, 회원정보 입력
			bookManager.run(managerIndex);	//실행		
		} else {
			System.out.println("로그인 실패");
		}
		System.out.println("프로그램 종료");
	}
}
