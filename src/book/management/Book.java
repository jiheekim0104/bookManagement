package book.management;

public class Book {
	public String name;
	public String author;
	public String position;
	public int cnt; // 빌려간 누적수
	public String state;
	public int date;
	public String msg;
	public int duplicateNumber;
	
	
	public Book(String name, String author, String position, int cnt,String state) {
		this.name=name;
		this.author=author;
		this.position=position;
		this.cnt =cnt;
		this.state=state;
		
		
	}
	public Book(String name, String author, String position, int cnt, String state,int date,String msg) {
		this.name=name;
		this.author=author;
		this.position=position;
		this.cnt =cnt;
		this.state=state;
		this.date=date;
		this.msg=msg;
		
	}
	public Book(Book book) {
		this.name=book.name;
		this.author=book.author;
		this.position=book.position;
		this.cnt =book.cnt;
		this.state=book.state;
		this.date=book.date;
		this.msg=book.msg;
		this.duplicateNumber=book.duplicateNumber;
	}
}
