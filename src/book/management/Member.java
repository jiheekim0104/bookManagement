package book.management;

public class Member {
	
	
	public String name; // 멤버 이름
	public String msg; // 미납횟수: 메세지
	public int lateCnt; // 늦게낸 횟수
	public int duplicateNumber; //같은 이름을 가진 회원을 찾는것, 안씀
	
	public Member(String name, String msg, int lateCnt) {
		this.name=name;
		this.msg=msg;
		this.lateCnt=lateCnt;
		
	}

	
	
}
