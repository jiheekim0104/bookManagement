package book.management;

public class Member {
	
	
	public String name; // ��� �̸�
	public String msg; // �̳�Ƚ��: �޼���
	public int lateCnt; // �ʰԳ� Ƚ��
	public int duplicateNumber; //���� �̸��� ���� ȸ���� ã�°�, �Ⱦ�
	
	public Member(String name, String msg, int lateCnt) {
		this.name=name;
		this.msg=msg;
		this.lateCnt=lateCnt;
		
	}

	
	
}
