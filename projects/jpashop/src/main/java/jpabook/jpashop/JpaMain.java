package jpabook.jpashop;

import jpabook.jpashop.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();


		try {
			/*Order order = em.find(Order.class, 1L);

			Order order = new Order();
			order.addOrderItem(new OrderItem());*/

			// 양방향으로 하지 않아도 아래와 같이 로직 처리를 할 수 있다. 중요한건 단방향 먼저 잘 구성하는 것!
			/*Order order = new Order();
			em.persist(order);

			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);

			em.persist(orderItem);*/

			/*System.out.println("--------------------");
			Book book = new Book();
			book.setName("JPA");
			book.setAuthors("김영한");

			em.persist(book);
			System.out.println("--------------------");*/

			// 임베디드 타입
			/*Member member = new Member();
			member.setUsername("hello");
			member.setHomeAddress(new Address("city", "street", "10000"));
			member.setWorkPeriod(new Period());

			em.persist(member);*/

			// 값 타입과 불변 객체
			/*Address address = new Address("city", "street", "10000");
			Member member = new Member();
			member.setUsername("member1");
			member.setHomeAddress(address);
			em.persist(member);

			Member member2 = new Member();
			member2.setUsername("member2");
			member2.setHomeAddress(address);
			em.persist(member2);

			// 같은 address 객체를 참조하고 있기 때문에 member, member2 둘다 바뀜
			member.getHomeAddress().setCity("newCity");

			Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

			Member member3 = new Member();
			member3.setUsername("member3");
			member3.setHomeAddress(copyAddress);  // 원하는 엔티티의 요소만 교체 가능
			em.persist(member3);*/

			// setter 삭제, 생성자로만 객체 생성
			/*Address address = new Address("city", "street", "10000");
			Member member = new Member();
			member.setUsername("member1");
			member.setHomeAddress(address);
			em.persist(member);

			Address newAddress = new Address("newCity", address.getStreet(), address.getZipcode());
			member.changeHomeAddress(newAddress);*/

			// 값 타입 비교: equals() hashCode() Override 후 사용
			Address address1 = new Address("city", "street", "10000");
			Address address2 = new Address("city", "street", "10000");

			System.out.println("address1 == address2: " + (address1 ==  address2));
			System.out.println("address1.equals(address2): " + (address1.equals(address2)));

			tx.commit(); // This is the point that queries are spent
		}catch (Exception  e) {
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
	}
}
