package project;

public class Member {
	private String id;
	private String pw;
	private String bday;
	private String constellation;
	
	public Member(String id, String pw, String bday) {
		this.id = id;
		this.pw = pw;
		this.bday = bday;
		this.constellation = toconstellation(bday);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getBday() {
		return bday;
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public String getConstellation() {
		return constellation;
	}

	public String toconstellation(String bday) {
		int date = Integer.parseInt(bday.substring(2));
		if ((date>0&&date<=119)||(date>=1225&&date<=1231)) {
			return "염소자리";
		} else if(date>=120&&date<219) {
			return "물병자리";
		} else if(date>=219&&date<321) {
			return "물고기자리";
		} else if(date>=321&&date<421) {
			return "양자리";
		} else if(date>=420&&date<521) {
			return "황소자리";
		} else if(date>=521&&date<622) {
			return "쌍둥이자리";
		} else if(date>=622&&date<723) {
			return "게자리";
		} else if(date>=723&&date<823) {
			return "사자자리";
		} else if(date>=823&&date<924) {
			return "처녀자리";
		} else if(date>=924&&date<1023) {
			return "천칭자리";
		} else if(date>=1023&&date<1123) {
			return "전갈자리";
		} else if(date>=1123&&date<1225) {
			return "궁수자리";
		} else {
			return "생일값 입력이 잘못되었습니다.";
		}
	}
	@Override
	public String toString() {
		return String.format("Member [id=%s, pw=%s, bday=%s, constellation=%s]", id, pw, bday, constellation);
	}
}
