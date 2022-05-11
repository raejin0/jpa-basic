package jpql;

import javax.persistence.*;
import java.util.List;

public class jpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {

			/*// 기본 문법과 쿼리 API begin ------------------------------
			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);

			em.persist(member);

			// 종류
			*//*TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
			TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
			Query query3 = em.createQuery("select m.username, m.age from Member m");*//*

			// resultList
			*//*TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
			List<Member> resultList = query.getResultList();
			for (Member member1 : resultList) {
				System.out.println("member1 = " + member1);
			}*//*

			// SingleResult: 반드시 결과가 하나일 경우에만 쓸 것
			*//*TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);

			TypedQuery<Member> queryS = em.createQuery("select m from Member m where m.id = '10'", Member.class);
			Member singleResult = queryS.getSingleResult();
			System.out.println("singleResult = " + singleResult);*//*

			// 파라미터 바인딩
			*//*Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
					.setParameter("username", "member1")
					.getSingleResult();
			System.out.println("result = " + result);*//*
			// 기본 문법과 쿼리 API end ------------------------------*/

			// 프로젝션(Select) begin ----------------------------------
			/*Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);

			em.flush();
			em.clear();

			*//*List<Member> result = em.createQuery("select m from Member m", Member.class)
					.getResultList();
			Member findMember = result.get(0);
			findMember.setAge(20);*//*

			// 1번도 join 쿼리가 나가지만 좋지 않은 방식이다. -> 2번과 같이 한눈에 보이도록 쓰는게 좋다.
			List<Team> result2 = em.createQuery("select m.team from Member m", Team.class).getResultList(); // 좋지 않은 방법, 묵시적 조인
			List<Team> result3 = em.createQuery("select t from Member m join m.team t", Team.class).getResultList(); // 좋은 방법, 명시적 조인

			// 임베디드 타입은 괜찮음
			em.createQuery("select o.address from Order o", Address.class).getResultList();
			em.createQuery("select distinct o.address from Order o").getResultList();


			// 반환 타입이 명확하지 않을 때 1.
			*//*List resultList = em.createQuery("select m.username, m.age from Member m")
					.getResultList();

			Object o = resultList.get(0);
			Object[] result = (Object[]) o;
			System.out.println("username = " + result[0]);
			System.out.println("age = " + result[1]);*//*


			// 반환 타입이 명확하지 않을 때 2. 제네릭 선언
			*//*List<Object[]> resultList = em.createQuery("select m.username, m.age from Member m")
					.getResultList();
			Object[] result = resultList.get(0);
			System.out.println("username = " + result[0]);
			System.out.println("age = " + result[1]);*//*

			// 반환 타입이 명확하지 않을 때 3. new 명령어
			List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
					.getResultList();
			MemberDTO memberDTO = resultList.get(0);
			System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
			System.out.println("memberDTO.getAge() = " + memberDTO.getAge());*/
			// 프로젝션(Select) end ----------------------------------

			// 페이징 begin ----------------------------------
			/*for (int i = 0; i < 100; i++) {
				Member member = new Member();
				member.setUsername("member1");
				member.setAge(i);
				em.persist(member);
			}

			em.flush();
			em.clear();

			// 설정한 방언에 페이징 쿼리가 나간다.
			List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
					.setFirstResult(1)
					.setMaxResults(10)
					.getResultList();

			System.out.println("resultList.size() = " + resultList.size());
			for (Member member1 : resultList) {
				System.out.println("member1 = " + member1.getAge());
			}*/
			// 페이징 end ----------------------------------

			// 조인 begin ----------------------------------
			/*Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);

			member.setTeam(team);

			em.flush();
			em.clear();

			// String query = "select m from Member m join m.team t"; // inner join
			//String query = "select m from Member m left join m.team t"; // left outer join
			*//*String query = "select m from Member m, Team t where m.username = t.name"; // theta join
			List<Member> resultList = em.createQuery(query, Member.class)
					.getResultList();
			// 설정한 방언에 페이징 쿼리가 나간다.

			System.out.println("resultList.size() = " + resultList.size());
			for (Member member1 : resultList) {
				System.out.println("member1 = " + member1.getAge());
			}*//*

			// 조인 대상 필터링
			//String query = "select m from Member m join Team t on t.name = 'teamA'";
			String query = "select m from Member m left join Team t on m.username= t.name";
			List<Member> resultList = em.createQuery(query, Member.class)
					.getResultList();

			System.out.println("resultList.size() = " + resultList.size());
			for (Member member1 : resultList) {
				System.out.println("member1 = " + member1.getAge());
			}*/

			// 조인 end ----------------------------------

			// JPQL 타입 표현 ENUM begin ----------------------------------
			/*Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			member.setType(MemberType.ADMIN);

			em.persist(member);

			member.setTeam(team);

			em.flush();
			em.clear();

			String query = "select m from Member m left join Team t on m.username= t.name" +
							"where m.type = :userType";
			List<Member> resultList = em.createQuery(query, Member.class)
					.setParameter("userType", MemberType.ADMIN)
					.getResultList();

			System.out.println("resultList.size() = " + resultList.size());
			for (Member member1 : resultList) {
				System.out.println("member1 = " + member1.getAge());
			}*/
			// JPQL 타입 표현 ENUM end ----------------------------------

			// JPQL 함수 begin ----------------------------------
			/*Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			member.setType(MemberType.ADMIN);

			em.persist(member);

			member.setTeam(team);

			em.flush();
			em.clear();

			//String query = "select function('group_concat', m.username) from Member m";
			String query = "select group_concat(m.username) from Member m";  // inject language hql 로 변경하면 문법오류 안남
			List<String> resultList = em.createQuery(query, String.class)
					.getResultList();

			System.out.println("resultList.size() = " + resultList.size());
			for (String s : resultList) {
				System.out.println("s = " + s);
			}*/

			// JPQL 함수 end ----------------------------------

			// 페치 조인 begin ----------------------------------
			/*Team teamA = new Team();
			teamA.setName("teamA");
			em.persist(teamA);

			Team teamB = new Team();
			teamB.setName("teamB");
			em.persist(teamB);

			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setTeam(teamA);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("member2");
			member2.setTeam(teamA);
			em.persist(member2);

			Member member3 = new Member();
			member3.setUsername("member3");
			member3.setTeam(teamB);
			em.persist(member3);

			em.flush();
			em.clear();


			// N + 1 발생 예시 => select query 세번 나감
			*//*String query = "select m from Member m";
			List<Member> resultList = em.createQuery(query, Member.class)
					.getResultList();

			for (Member member : resultList) {
				System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
				//member1, teamA (SQL)
				//member2, teamA (1차캐시)
				//member3, teamB (SQL)

				//회원 100명 -> N + 1
			}*//*


			// fetch join => select query 한번만 나감
			String query = "select m from Member m join fetch m.team";
			List<Member> resultList = em.createQuery(query, Member.class)
					.getResultList();
			for (Member member : resultList) {
				System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
			}

			// 1:N 조회할 경우 데이터가 뻥튀기 될 수 있음 => fetch 조인은 메인 엔티티에서 하자
			String query2 = "select t from Team t join fetch t.members";
			List<Team> resultList1 = em.createQuery(query2, Team.class).getResultList();
			System.out.println("resultList1 = " + resultList1.size());
			for (Team team : resultList1) {
				System.out.println("team = " + team.getName() + ", members = " + team.getMembers().size());
				for (Member member : team.getMembers()) {
					System.out.println("  -> member = " + member);
				}
			}

			// Distinct
			// SQL distinct는 전체 컬럼 값들이 같아야만 중복 제거하는 반면에
			// JPQL의 distinct는 같은 식별자를 가진 엔티티(team)를 기준으로 중복 제거
			String query3 = "select distinct t from Team t join fetch t.members";
			List<Team> resultList2 = em.createQuery(query3, Team.class).getResultList();

			System.out.println("resultList1.size() = " + resultList2.size());
			for (Team team : resultList2) {
				System.out.println("team = " + team.getName() + ", members = " + team.getMembers().size());
				for (Member member : team.getMembers()) {
					System.out.println("  -> member = " + member);
				}
			}*/
			// 페치 조인 end ----------------------------------

			// 엔티티 직접 사용 begin ----------------------------------
			/*Team teamA = new Team();
			teamA.setName("teamA");
			em.persist(teamA);

			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setTeam(teamA);
			em.persist(member1);

			em.flush();
			em.clear();

			String query = "select m from Member m where m = :member";
			Member result = em.createQuery(query, Member.class)
					.setParameter("member", member1)
					.getSingleResult();

			System.out.println("result = " + result);

			String query2 = "select m from Member m where m.id = :memberId";
			Member result2 = em.createQuery(query2, Member.class)
					.setParameter("memberId", member1.getId())
					.getSingleResult();

			System.out.println("result2 = " + result2);

			String query3 = "select m from Member m where m.team = :team";  // team_id
			List<Member> resultList = em.createQuery(query3, Member.class)
					.setParameter("team", teamA)
					.getResultList();

			for (Member member : resultList) {
				System.out.println("member = " + member);
			}*/
			// 엔티티 직접 사용 end ----------------------------------


			// Named 쿼리 begin ----------------------------------
			/*Team teamA = new Team();
			teamA.setName("teamA");
			em.persist(teamA);

			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setTeam(teamA);
			em.persist(member1);

			em.flush();
			em.clear();

			Member result = em.createNamedQuery("Member.findByUsername", Member.class)
					.setParameter("username", "member1")
					.getSingleResult();

			System.out.println("result = " + result);*/
			// Named 쿼리 end ----------------------------------


			// 벌크 연산 begin ----------------------------------
			Team teamA = new Team();
			teamA.setName("teamA");
			em.persist(teamA);

			Team teamB = new Team();
			teamB.setName("teamB");
			em.persist(teamB);

			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setAge(0);
			member1.setTeam(teamA);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("member2");
			member2.setAge(0);
			member2.setTeam(teamA);
			em.persist(member2);

			Member member3 = new Member();
			member3.setUsername("member3");
			member3.setAge(0);
			member3.setTeam(teamB);
			em.persist(member3);

			//em.flush();
			//em.clear();

			// commit 또는 query 호출 시 flush 자동 호출됨
			// flush는 쿼리를 날려 DB에만 반영하는 것이지 영속성 컨텍스트를 갱신하는게 아니다.
			int resultCount = em.createQuery("update Member m set m.age = 20")
					.executeUpdate();

			// 위에서 flush만 하는 것은 쿼리를 내 보내는 것이지 영속성 컨텍스트를 업데이트 하는 것이 아니기 때문에
			// clear 하여 정합성을 맞춰준다.
			em.clear();

			Member member = em.find(Member.class, member1.getId());
			System.out.println("member = " + member);

			System.out.println("resultCount = " + resultCount);

			// 이건 clear 되기 전 영속성 context에 계속 남아있는 값들
			System.out.println("member1 = " + member1);
			System.out.println("member2 = " + member2);
			System.out.println("member3 = " + member3);

			// 벌크 연산 end ----------------------------------

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}

}
