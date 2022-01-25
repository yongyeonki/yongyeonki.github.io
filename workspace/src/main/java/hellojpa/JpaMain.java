package hellojpa;

import javax.persistence.*;


public class JpaMain {
    public static void main(String[] agrs){
        //EntityManagerFactory는 에플리케이션 로딩시점에 딱하나만 존재해야 한다.
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
        
        EntityManager em = emf.createEntityManager();
        // 트랜젝션 생성
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        /* //Member table
        Member member = new Member();
        // Id 1생성
        *//*member.setId(1L);
        member.setName("HelloA");*//*
        // Id 2생성
        member.setId(2L);
        member.setName("helloB");
        em.persist(member);
        tx.commit();*/
        // 이렇게 코드를 짜면 안좋은 코드 문제가 생겼을때 대처하기 힘듬
        // 예외 처리 해줘야 한다.

        try{
            //Member table 생성
          /*  Member member = new Member();
            member.setId(1L);
            member.setName("helloA");
            em.persist(member);*/

            // Member table 조회
            // Member findMember = em.find(Member.class,1L);
            //System.out.println("findMember.id " + findMember.getId());
            //System.out.println("findMember.name " + findMember.getName());

            //Member table 컬럼 삭제
            //Member findMember = em.find(Member.class,1L);
            //em.remove(findMember);

            //Member table 수정정
            //Member findMember = em.find(Member.class,1L);
            // findMember.setName("HelloJPA");
            /*List<Member> result = em.createQuery("select m from Member as m",Member.class)
                    .getResultList();

            for(Member member : result){
                System.out.println("Member.name = " + member.getName());
            }*/

           /* //비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            //영속
            System.out.println("=== BEFORE ===");
            em.persist(member);
            // 이때 디비에 저장은 되지 않는다.
            // BEFORE AFTER 이후에 쿼리가 나간다. 영속성 persist에서 나가지는 않는다 트랜젝션 commit시점에서 나감
            System.out.println("=== AFTER ===");*/

            // 준영속
            //em.detach(member); // 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태 : 이렇게 하면 의미가 없어진다.

            //삭제 요청
            //em.remove(member);


            /*//1차 캐시에서 조회
            Member member = new Member();
            member.setId(123L);
            member.setName("name2");

            //1차 캐시에 저장됨
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");

            //1차 캐시에서 조회
            Member findMember = em.find(Member.class, 123L);
            System.out.println("findMember.id" + findMember.getId());
            System.out.println("findMember.name" + findMember.getName());*/
            // 2번 조회할때 부터 디비가 아닌 1차 케시에서 조회된다.

            //영속 엔티티의 동일성 보장
            //1차 캐시로 반복 가능한 읽기(REPEATABLE READ) 등급의 트랜젝션 격리 수준을
            // 데이터이스가 아닌 애츨리케이션 차원에서 제공
            // 1차 캐시에 올라간 상태가 영속 상태

            /*Member findMember1 = em.find(Member.class, 123L);
            Member findMember2 = em.find(Member.class, 123L);

            System.out.println("result =  " + (findMember1 == findMember2));*/

            /*  //엔티티 등록
            Member member1 = new Member(11L,"na1");
            Member member2 = new Member(22L,"na2");

            em.persist(member1);
            em.persist(member2);
            //여기까지 INSERT SQL을 데이터베이스에 보내지 않는다
            //커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다
            System.out.println("==============");*/

            // 영속 엔티티 수정
            /* Member member = em.find(Member.class, 11L);
            member.setName("nam1");
            //em.persist(member); 커넥션을 호출하지 않는다.*/
            
            // 영속 플러쉬
            //플러쉬
            // 영속성 컨텍스트를 비우지 않음
            // 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
            // 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화 하면됨
            /*Member member = new Member(201L,"member201");
            em.persist(member);
            em.flush();
            System.out.println("==============");*/

            // 준영속 상태
            // 영속 -> 준영속
            // 영속 상태의 엔티티가 영속성 컨텍스트에서 분리(detached)
            // 영속성 컨텍스트가 제공하는 기능을 사용 못함

            //준영속 상태로 만드는 방법
            // em.detach(entity)  : 특정 엔티티만 준영속 상태로 전환
            // em.clear() : 영속성 컨텍스트를 완전히 초기화
            // em.close() : 영속성 컨텍스트를 종료

            Member member = em.find(Member.class, 200L);
            member.setName("AAA");

            //준영속 상태로 만드는 방법
            // em.detach(entity)  : 특정 엔티티만 준영속 상태로 전환
            //em.detach(member);
            // 이렇게 하면 selete 쿼리만 나감 업데이트 쿼리가 안나감 더이상 JPA 관리 하지 않음

            // em.clear() : 영속성 컨텍스트를 완전히 초기화
            //em.clear();
            // 1차 캐시를 통으로 초기화 시킴

            // 영속성 컨텍스트 정리
            // 엔티티를 영구 저장하는 환경
            // EntityManager.persist(entity); -> em.persist(member);

            // em.close() : 영속성 컨텍스트를 종료
            em.close();
            // 실행이후 종료

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
