package project;

import java.util.ArrayList;
import java.util.List;

public class MemberPractice {
	public static void main(String[] args) {
		List<Member> list = new ArrayList<Member>();
		list.add(new Member("abcd", "dbca", "910111"));
		list.add(new Member("qetd", "ztadt", "871224"));
		list.add(new Member("qetw", "hqet", "940708"));
		list.add(new Member("ctqe", "qetc", "950908"));
		list.add(new Member("qeyz", "ztqe", "970331"));
		list.add(new Member("qwew", "dtqwe", "790117"));
		
		for (int i = 0; i < list.size(); i++) {
			Member str = list.get(i);
			System.out.println(str);
		}
	}
}
