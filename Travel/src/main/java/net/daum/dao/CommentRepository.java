package net.daum.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.daum.vo.Cm_CommentVO;
import net.daum.vo.Community_boardVO;


public interface CommentRepository extends JpaRepository<Cm_CommentVO, Long> {

	List<Cm_CommentVO> findByCommunityBoardMateno(Long mateno);
	
	void deleteByCommentNo(Long commentNo);

	Cm_CommentVO findBycommentNo(Long commentNo);

	

	List<Cm_CommentVO> findByParentComment(Cm_CommentVO parentComment);

	
	
	

	





	


}
