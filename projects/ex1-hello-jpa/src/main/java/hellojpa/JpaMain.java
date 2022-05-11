package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();


		try {
			// code

			// JPA 시작하기 begin ----------
			// 등록
			/*Member member = new Member();
//			member.setId(1L);
//			member.setName("HelloA");
			member.setId(2L);
			member.setName("HelloB");

			em.persist(member);*/


			// 단 건 조회
			/*Member findMember = em.find(Member.class, 1L);
			System.out.println("findMember.getId() = " + findMember.getId());
			System.out.println("findMember.getName() = " + findMember.getName());*/


			// 수정
			/*Member findMember = em.find(Member.class, 1L);
			findMember.setName("HelloJPA");*/

			// 삭제
			/*Member findMember = em.find(Member.class, 1L);
			em.remove(findMember);*/

			// 전체 조회
			/*List<Member> result = em.createQuery("select m from Member as m ", Member.class)
					.setFirstResult(1) // 1번부터
					.setMaxResults(10) // 10개 select
					.getResultList();

			for (Member member : result) {
				System.out.println("member.getName() = " + member.getName());
			}*/
			// JPA 시작하기 end ----------

			// Persistence context begin ----------
			// Non-ersistence
			/*Member member = new Member();
			member.setId(101L);
			member.setName("HelloJPA");*/

			// Persistence
			/*System.out.println("=== BEFORE ===");
			em.persist(member);
			System.out.println("=== AFTER ===");*/


			// Belows are printed before dispatched. It from PersistenceContext 'Cache'
			/*Member findMember = em.find(Member.class, 101L);
			System.out.println("findMember.getId() = " + findMember.getId());
			System.out.println("findMember.getName() = " + findMember.getName());*/

			/*Member findMember1 = em.find(Member.class, 101L);
			Member findMember2 = em.find(Member.class, 101L);

			System.out.println("result = " + (findMember1 == findMember2) );*/

			/*Member member1 = new Member(150L, "A");
			Member member2 = new Member(160L, "B");
			em.persist(member1);
			em.persist(member2);
			System.out.println("===========================");*/

			/*Member member = em.find(Member.class, 150L);
			member.setName("ZZZZ");
			System.out.println("===========================");*/

			// flush() 가 호출 되는 시점에 '쓰기 지연 sql저장소'에 저장된 DML 쿼리들이 날아간다.(select은 바로바로 호출)
			/*Member member = new Member(200L, "member200");
 			em.persist(member);
			em.flush();
			System.out.println("===========================");*/


			// Detach(): quasi-persistence
			// update query isn't dispatched
			/*Member member = em.find(Member.class, 150L);
			member.setName("AAAAA");
			em.detach(member);
			em.close();
			System.out.println("===========================");*/

			// Persistence context end ----------

			// basic mapping begin ----------
//			Member member = new Member();
////			member.setId("ID_A");
//			member.setUsername("C");
////			em.persist(member);
//
//			System.out.println("=================");
//			em.persist(member); // @GeneratedValue(strategy = GenerationType.IDENTITY) IDENTITY 일 경우 필요한 ID값을 모르기 때문에 이 시점에 바로 INSERT 하여 ID값을 가져온다.
//			System.out.println("member.getId() = " + member.getId());
//			System.out.println("=================");

			// basic mapping end ----------


			// 단방향 연관관계 begin ------------------
			/*Team team = new Team();
			team.setName("TeamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			member.setTeamId(team.getId());
			em.persist(member);

			Member findMember = em.find(Member.class, member.getId());

			Long findTeamId = findMember.getTeamId();
			Team findTeam = em.find(Team.class, findTeamId);*/


			/*Team team = new Team();
			team.setName("TeamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			member.setTeam(team);
			em.persist(member);


			em.flush(); // 위에서 만들어진 PersistenceContext 에 저장된 쿼리를 날리고
			em.clear(); // 완전히 비운다.

			Member findMember = em.find(Member.class, member.getId());

			Team findTeam = findMember.getTeam();
			System.out.println("findTeam.getName() = " + findTeam.getName());*/

			// 단방향 연관관계 end ------------------


			// 양방향 연관관계 begin ------------------
			/*Team team = new Team();
			team.setName("TeamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			member.setTeam(team);
			em.persist(member);


			em.flush(); // 위에서 만들어진 PersistenceContext 에 저장된 쿼리를 날리고
			em.clear(); // 완전히 비운다.

			Member findMember = em.find(Member.class, member.getId());

			List<Member> members = findMember.getTeam().getMembers();

			for (Member m : members) {
				System.out.println("m = " + m.getUsername());
			}*/
			// 양방향 연관관계 end ------------------

			// 연관관계의 주인 2 begin ------------------
			/*Member member = new Member();
			member.setUsername("member1");
			em.persist(member);

			Team team = new Team();
			team.setName("TeamA");
			team.getMembers().add(member);
			em.persist(team);*/

//			Team team = new Team();
//			team.setName("TeamA");
//			team.getMembers().add(member);
//			em.persist(team);

//			Member member = new Member();
//			member.setUsername("member1");
//			member.setTeam(team);
//			member.chageTeam(team);
//			em.persist(member);

			// changeTeam 대신 활용할 수 있음.
//			team.addMember(member);

			// 이 부분이 없어도 db에는 이상없이 값이 들어간다.단 2가지 문제가 발생할 수 있다.
			// 1. 하단의 flush, clear가 존재하지 않는다면 영속성 컨텍스트에 올라가지 않아 그 아래 로직 처리에 문제가 생긴다.
			// 2. 테스트케이스 작성 시에도 위와 같은 문제가 발생할 수 있다.
			// 즉, 양방향 연관관계 설정시 양쪽 다 값을 넣어주자!
			// member.setTeat()에서 한꺼번에 넣어주자, 단 이런식으로 로직이 들어갈 경우 단순한 setter 가 아니기 때문에 changeXxx등 과 같이 네이밍하여 중요도를 표시하자
//			team.getMembers().add(member);

//			em.flush(); // 위에서 만들어진 PersistenceContext 에 저장된 쿼리를 날리고
//			em.clear(); // 완전히 비운다.

//			Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
//			List<Member> members = findTeam.getMembers();
//			System.out.println("=============================");
//			for (Member m : members) {
//				System.out.println("m = " + m.getUsername());
//			}
//			System.out.println("=============================");


			// 연관관계의 주인 2 end ------------------


			/*Movie movie = new Movie();
			movie.setDirector("aaaa");
			movie.setActor("bbb");
			movie.setName("바람과 함께 사라지다.");
			movie.setPrice(10000);

			em.persist(movie);

			em.flush();
			em.clear();

			Movie findMovie = em.find(Movie.class, movie.getId());
			System.out.println("findMovie = " + findMovie);*/


			/*Member member = new Member();
			member.setCreatedBy("Kim");
			member.setUsername("user1");
			member.setCreatedDate(LocalDateTime.now());

			em.persist(member);*/

			/*Member member = new Member();
			member.setUsername("hello");

			em.persist(member);

			em.flush();
			em.clear();

			//
			//Member findMember = em.find(Member.class, member.getId());
			Member findMember = em.getReference(Member.class, member.getId());

			System.out.println("findMember.getClass() = " + findMember.getClass());
			System.out.println("findMember.getId() = " + findMember.getId());
			System.out.println("findMember.getUsername() = " + findMember.getUsername());
			System.out.println("findMember.getUsername() = " + findMember.getUsername());*/


			/*Member member1 = new Member();
			member1.setUsername("member1");

			Member member2 = new Member();
			member2.setUsername("member2");

			Member member3 = new Member();
			member3.setUsername("member3");


			em.persist(member1);
			em.persist(member2);
			em.persist(member3);

			em.flush();
			em.clear();*/

			/*Member m1 = em.find(Member.class, member1.getId());
			Member m2 = em.find(Member.class, member2.getId());
//			Member m2 = em.getReference(Member.class, member2.getId());

			// getReference로 가져 올 경우 == 비교시 안 맞기 때문에 instanceOf() 를 사용하자.
			System.out.println("m1 == m2" + (m1.getClass() == m2.getClass()));
			System.out.println("m1 instanceof m2: " + (m1 instanceof Member));

			Member reference1 = em.getReference(Member.class, m1.getId());
			System.out.println("m1.getClass() = " + m1.getClass());
			System.out.println("reference.getClass() = " + reference1.getClass());  // 이미 위에서 객체로 만들어 놨기 때문에 그걸 가져다 씀
			System.out.println("a == a: " + (m1 == reference1));
//			System.out.println("a == a: " + (m1 instanceof reference));


			System.out.println("---------------");
			Member m3 = em.getReference(Member.class, member3.getId());
			Member reference3 = em.getReference(Member.class, m3.getId());
			Member find3 = em.find(Member.class, m3.getId());
			System.out.println("m3 == reference3: " + (m3 == reference3));
			System.out.println("m3 == find3: " + (m3 == find3));*/

			/*Member ref1 = em.getReference(Member.class, member1.getId());
			System.out.println("ref1.getClass() = " + ref1.getClass()); //proxy
			em.detach(ref1); // em.close();
			ref1.getUsername();*/

			// 프록시 확인
			/*Member ref1 = em.getReference(Member.class, member1.getId());
			System.out.println("ref1.getClass() = " + ref1.getClass()); //proxy
			ref1.getUsername();
			System.out.println("isLoaded = "  + emf.getPersistenceUnitUtil().isLoaded(ref1));
			Hibernate.initialize(ref1); // 강제 초기화*/

			// 지연 로딩과 즉시 로딩
			/*Team team = new Team();
			team.setName("TeamA");
			em.persist(team);

			Team teamB = new Team();
			team.setName("teamB");
			em.persist(teamB);

			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setTeam(team);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("member2");
			member2.setTeam(teamB);
			em.persist(member2);

			em.flush();
			em.clear();

			*//*Member m = em.find(Member.class, member.getId());
			System.out.println("m1.getClass() = " + m.getClass());

			System.out.println("=================");
			System.out.println(m.getTeam().getName()); // 지연로딩 시 초기화, 즉시 로딩시에는 그냥 가져다 씀
			System.out.println("=================");*//*


			System.out.println("=================");
			// fetch = FetchType.EAGAR 일 경우 MEMBER 다 가져오고 연관된 TEAM도 다 겨져온다.
//			List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

			// fetch join: FetchType.LAZY 여도 한꺼번에 가져오도록 한다.
			List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();*/



			// 영속성 전이
			/*Child child1 = new Child();
			Child child2 = new Child();

			Parent parent = new Parent();
			parent.addChild(child1);
			parent.addChild(child2);

			// CascadeType.ALL 일 경우 부목객체만 persist 해주면 자식들도 따라감
			em.persist(parent);
			*//*em.persist(child1);
			em.persist(child2);*//*

			em.flush();
			em.clear();

			Parent findParent = em.find(Parent.class, parent.getId());
			findParent.getChildList().remove(0 );*/


			// 값타입 컬렉션
			/*Member member = new Member();
			member.setUsername("member1");
			member.setHomeAddress(new Address("homeCity", "street", "zipcode"));

			member.getFavoriteFoods().add("치킨");
			member.getFavoriteFoods().add("피자");
			member.getFavoriteFoods().add("초밥");

			// 값타입 컬렉션 활용
			*//*member.getAddressHistory().add(new Address("old1", "street", "zipcode"));
			member.getAddressHistory().add(new Address("old2", "street", "zipcode"));*//*

			// 값타입 컬렉션 대신 엔티티 이용
			member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
			member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));

			em.persist(member);
			
			em.flush();
			em.clear();

			System.out.println("===========================");
			Member findMember = em.find(Member.class, member.getId());*/

			// 값 타입 컬렉션 지연로딩 확인
			/*List<Address> addressHistory = findMember.getAddressHistory();
			for (Address address : addressHistory) {
				System.out.println("address.getCity() = " + address.getCity());

			}
			Set<String> favoriteFoods = findMember.getFavoriteFoods();
			for (String favoriteFood : favoriteFoods) {
				System.out.println("favoriteFood = " + favoriteFood);
			}*/

			//homeCity -> newCity
			/*Address a = findMember.getHomeAddress();
			findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));*/

			//치킨 -> 한식
			/*findMember.getFavoriteFoods().remove("치킨");
			findMember.getFavoriteFoods().add("한식");*/

			// Eaqual() & hashCode() 오버라이딩이 제대로 구현되어 있어야 한다.
			/*findMember.getAddressHistory().remove(new Address("old1", a.getStreet(), a.getZipcode()));
			findMember.getAddressHistory().add(new Address("newCity", a.getStreet(), a.getZipcode()));*/

			// 객체지향 쿼리 언어1
			/*List<Member> result = em.createQuery(
					"select m from Member m where m.username like '%kim%'",
					Member.class
			).getResultList();

			for (Member member : result) {
				System.out.println("member = " + member);
			}*/

			// Criteria 소개
			/*CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Member> query = cb.createQuery(Member.class);

			Root<Member> m = query.from(Member.class);

			CriteriaQuery<Member> cq = query.select(m);

			String username = "dafadf";

			if (username != null) {
				cq = cq.where(cb.equal(m.get("username"), "kim"));
			}

			List<Member> resultList = em.createQuery(cq).getResultList();*/


			tx.commit(); // This is the point that queries are spent
		}catch (Exception  e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
		emf.close();
	}
}
