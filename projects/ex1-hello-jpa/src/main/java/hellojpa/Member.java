package hellojpa;

import javax.persistence.*;
import javax.xml.ws.soap.Addressing;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
//@Entity(name="Member")
//@Table(name="Member")
//@Table(name="MBR")   // find() 시 Member 테이블이 아닌 MBR 테이블에서 조회
//@SequenceGenerator(name="member_seq_generator", sequenceName = "member_seq")
//public class Member extends BassEntity{
public class Member {

/*	@Id
	private Long id;
	private String name;
	private int age;

	public Member() { }

	public Member(Long id, String name) {
		this.id = id;
		this.name = name;
	}

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
	}*/

	// 엔티티 매핑 begin ------------------
	/*@Id
	private Long id;

	@Column(name = "name")
	private String username;

	private Integer age;

	@Enumerated(EnumType.STRING)
	private RoleType roleType;

	@Temporal(TemporalType.TIMESTAMP)  // Java offer 3 temporal type(DATE ONLY, TIME ONLY, TIMESTAMP: DATE & TIME )
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	// Use the patter below in the newer JAVA version.
//	private LocalDate testLocalDate;
//	private LocalDateTime testLocalDateTime;

	@Lob    // For long contents over varchar range
	private String description;

	public Member() { }*/
	// 엔티티 매핑 end ------------------

	// 기본 매핑 begin ------------------
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "member_seq_generator")
	private Long id;

	@Column(name="name", nullable = false)
	private String username;

	public Member() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}*/

	// 기본 매핑 end ------------------

	// 단방향 연관관계 / 일대일 관계 매핑 begin ------------------
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

//	@Column(name="USERNAME")
	private String username;

	@Embedded
	private Address homeAddress;

	/*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "MEMBER_ID")
	private List<AddressEntity> addressHistory = new ArrayList<>();*/
		/*@ElementCollection
	@CollectionTable(name = "ADDRESS_HISTORY", joinColumns =
		@JoinColumn(name = "MEMBER_ID")
	)
	private List<Address> addressHistory = new ArrayList<>();*/

	/*@ElementCollection
	@CollectionTable(name = "FAVORITE_FOOD", joinColumns =
		@JoinColumn(name = "MEMBER_ID")
	)
	@Column(name = "FOOD_NAME")
	private Set<String> favoriteFoods = new HashSet<>();*/

	/*public Set<String> getFavoriteFoods() {
		return favoriteFoods;
	}

	public void setFavoriteFoods(Set<String> favoriteFoods) {
		this.favoriteFoods = favoriteFoods;
	}


	public List<AddressEntity> getAddressHistory() {
		return addressHistory;
	}

	public void setAddressHistory(List<AddressEntity> addressHistory) {
		this.addressHistory = addressHistory;
	}*/

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
