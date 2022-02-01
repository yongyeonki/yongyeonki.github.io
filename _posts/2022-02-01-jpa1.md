###H2 데이터베이스 설치와 실행
#####• http://www.h2database.com/
#####• 최고의 실습용 DB 
#####• 가볍다.(1.5M) 
#####• 웹용 쿼리툴 제공
#####• MySQL, Oracle 데이터베이스 시뮬레이션 기능
#####• 시퀀스, AUTO INCREMENT 기능 지원

###메이븐 소개
#####• https://maven.apache.org/ 
#####• 자바 라이브러리, 빌드 관리
#####• 라이브러리 자동 다운로드 및 의존성 관리
#####• 최근에는 그래들(Gradle)이 점점 유명

###프로젝트 생성
#####• 메이븐 설정
#####• groupId: jpa-basic 
#####• artifactId: ex1-hello-jpa 
#####• version: 1.0.0

###라이브러리 추가 - pom.xml
```java
<?xml version="1.0" encoding="UTF-8"?> 
<project xmlns="http://maven.apache.org/POM/4.0.0" 
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
              xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"> 
  <modelVersion>4.0.0</modelVersion> 
  <groupId>jpa-basic</groupId> 
  <artifactId>ex1-hello-jpa</artifactId> 
  <version>1.0.0</version> 
  <dependencies> 
      <!-- JPA 하이버네이트 --> 
      <dependency> 
          <groupId>org.hibernate</groupId> 
          <artifactId>hibernate-entitymanager</artifactId> 
          <version>5.3.10.Final</version> 
      </dependency> 
    <!-- H2 데이터베이스 --> 
    <dependency> 
        <groupId>com.h2database</groupId> 
        <artifactId>h2</artifactId> 
        <version>1.4.199</version> 
     </dependency> 
 </dependencies> 
</project> 
```

####JPA 설정하기 - persistence.xml
#####• JPA 설정 파일
#####• /META-INF/persistence.xml 위치
#####• persistence-unit name으로 이름 지정
#####• javax.persistence로 시작: JPA 표준 속성
#####• hibernate로 시작: 하이버네이트 전용 속성

###persistence.xml
```java
<?xml version="1.0" encoding="UTF-8"?> 
<persistence version="2.2" 
            xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd"> 
    <persistence-unit name="hello"> 
        <properties> 
           <!-- 필수 속성 --> 
           <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/> 
           <property name="javax.persistence.jdbc.user" value="sa"/> 
           <property name="javax.persistence.jdbc.password" value=""/> 
           <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/> 
           <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/> 
           <!-- 옵션 --> 
           <property name="hibernate.show_sql" value="true"/> 
           <property name="hibernate.format_sql" value="true"/> 
           <property name="hibernate.use_sql_comments" value="true"/> 
           <!--<property name="hibernate.hbm2ddl.auto" value="create" />--> 
        </properties> 
    </persistence-unit> 
</persistence>
```

####데이터베이스 방언
#####• JPA는 특정 데이터베이스에 종속 X 
#####• 각각의 데이터베이스가 제공하는 SQL 문법과 함수는 조금씩 다름
#####• 가변 문자: MySQL은 VARCHAR, Oracle은 VARCHAR2 
#####• 문자열을 자르는 함수: SQL 표준은 SUBSTRING(), Oracle은

####SUBSTR() 
#####• 페이징: MySQL은 LIMIT , Oracle은 ROWNUM 
#####• 방언: SQL 표준을 지키지 않는 특정 데이터베이스만의 고유한 기능
