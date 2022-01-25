package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
//@Table(name = "MBR")
//@Table(name = "USER") 테이블이 여러개일 경우 테이블을 지정해줄때 사용하는 어노테이션
public class Member {
    @Id
    private Long id;
    //@Column(name ="username") 컬럼을 지정할때 사용 하는 어노테이션
    private String name;
    private int age;

    public Member(){

    }

    public Member(Long id, String name){
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
    }


}
