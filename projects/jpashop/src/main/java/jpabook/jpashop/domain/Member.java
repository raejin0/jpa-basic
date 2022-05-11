package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
//public class Member extends BaseEntity{
public class Member{
	@Id @GeneratedValue() // default AUTO
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Embedded
	private Address address;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	/*@Embedded
	private Period workPeriod;

	@Embedded
	private Address homeAddress;

	@Embedded
	@AttributeOverrides({
              @AttributeOverride(name="city",
		              column=@Column(name = "work_city")),
              @AttributeOverride(name="street",
		              column=@Column(name = "work_street")),
			@AttributeOverride(name="zipcode",
		              column=@Column(name = "work_zipcode"))
	})
	private Address workAddress;

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
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

	public Period getWorkPeriod() {
		return workPeriod;
	}

	public void setWorkPeriod(Period workPeriod) {
		this.workPeriod = workPeriod;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public void changeHomeAddress(Address newAddress) {
		this.homeAddress = newAddress;
	}*/

	/*private String city;
	private String street;
	private String zipcode;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}*/
}