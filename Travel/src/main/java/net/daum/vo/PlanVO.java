package net.daum.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.daum.config.MyEntityListener;

@Setter //setter()메서드 자동생성
@Getter //getter()메서드 자동생성
@ToString //toString()메서드 자동생성
@Entity //엔티티빈
@SequenceGenerator( //오라클 시퀀스 생성기
		  name="plan_seq" , //시퀀스 제너레이터 이름
		  sequenceName="plan_no_seq",//시퀀스 이름
		  initialValue= 1 , //시퀀스 시작값
		  allocationSize = 1 //1씩 증가
		)
@Table(name="plan") //member 테이블 생성
@EqualsAndHashCode(of="planNo")
//equals(), hashCode(), canEqual() 메서드 자동생성
@EntityListeners(MyEntityListener.class)
public class PlanVO {
	
	@Id
	@GeneratedValue(
			strategy= GenerationType.SEQUENCE,
			generator= "plan_seq"
			)
	@Column(name= "plan_no", nullable = false)
	private int planNo;// 일정번호
	
	@Column(name = "departure_date", nullable = false)
	private Date departureDate;// 출발일
	
	@Column(name= "arrival_date", nullable = false)
	private Date arrivalDate;// 도착일

	@CreationTimestamp// 하이버네이트로 등록시점 날짜값 생성
	@Column(name= "plan_date", nullable = false)
    private Timestamp planDate;// 일정생성날짜
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberVO memberVO;
	
	   @ManyToMany(fetch = FetchType.LAZY)
	   @JoinTable(
	         name= "plan_city",
	         joinColumns= @JoinColumn(name= "plan_no"),
	         inverseJoinColumns= @JoinColumn(name= "city_code")
	         )
	   private List<CityVO>cities= new ArrayList<>();
}
