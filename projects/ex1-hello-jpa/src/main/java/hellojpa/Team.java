package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	private String name;

//	@OneToMany(mappedBy = "team") // Member 클래스에 정의된 필드명
//	private List<Member> members = new ArrayList<Member>(); // 이렇게 초기화해서 선언해야 처음 값 넣을 때 null pointer가 안뜸

	/*public void addMember(Member member) {
		member.setTeam(this);
		members.add(member);
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}*/
}
