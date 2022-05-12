package todaypig.member;

public class MemberVO {
	private String memberId;
	private String memberPw;
	private String memberBday;
	private String memberGender;
	private String memberDelete;

	public MemberVO(String memberId, String memberPw, String memberBday, String memberGender, String memberDelete) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberBday = memberBday;
		this.memberGender = memberGender;
		this.memberDelete = memberDelete;
	}

	public MemberVO(String memberId, String memberBday, String memberGender) {
		this.memberId = memberId;
		this.memberBday = memberBday;
		this.memberGender = memberGender;
	}
	
	public MemberVO(String memberId, String memberPw) {
		this.memberId = memberId;
		this.memberPw = memberPw;
	}
	
	public MemberVO(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberBday() {
		return memberBday;
	}

	public void setMemberBday(String memberBday) {
		this.memberBday = memberBday;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberDelete() {
		return memberDelete;
	}

	public void setMemberDelete(String memberDelete) {
		this.memberDelete = memberDelete;
	}

}
