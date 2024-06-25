package net.daum.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"communityBoard","parentComment","childComments"})
@EqualsAndHashCode(of = "commentNo")
@Entity
@SequenceGenerator(// 오라클 시퀀스 생성기
		name = "comment_noseq_gename", sequenceName = "comment_no_seq", // 시퀀스 이름
		initialValue = 1, // 시퀀스 시작값
		allocationSize = 1)
@Table(name = "cm_comment")

public class Cm_CommentVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_noseq_gename")
	private Long commentNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mateno", nullable = false)
	@JsonBackReference(value="communityBoard")
	private Community_boardVO communityBoard;

	private String commentWriter;

	@Column(length = 4000)
	private String commentText;

	@CreationTimestamp // 하이버네이트 기능으로 등록시점 날짜를 기록
	private Timestamp makedate;// 등록 날짜

	@UpdateTimestamp
	private Timestamp updatedate;// 하이버네이트 기능으로 업데이트 날짜 자동 기록

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_comment_id")
	@JsonBackReference(value="parentComment")
	private Cm_CommentVO parentComment; // 부모 댓글 ID(대댓글을 위해서)
	
	@OneToMany(mappedBy = "parentComment",cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference(value="parentComment")
	private List<Cm_CommentVO> childComments; //자식 댓글들 
	

}