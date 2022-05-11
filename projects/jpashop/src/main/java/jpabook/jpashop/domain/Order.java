package jpabook.jpashop.domain;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name ="ORDERS")
public class Order extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

/*	@Column(name = "MEMBER_ID")
	private Long memberId;*/

	@ManyToOne(fetch = LAZY, cascade = ALL)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "DELIVERY_ID")
	private Delivery delivery;

	@OneToMany(mappedBy = "order", cascade = ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStattus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Long getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(Long memberId) {
//		this.memberId = memberId;
//	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStattus getStatus() {
		return status;
	}

	public void setStatus(OrderStattus status) {
		this.status = status;
	}

	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
}
