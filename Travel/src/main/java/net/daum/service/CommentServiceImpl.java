package net.daum.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.CommentDAO;
import net.daum.vo.Cm_CommentVO;
import net.daum.vo.Community_boardVO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentdao;


	@Override
	public List<Cm_CommentVO> getCommentByMateno(Long mateno) {
		
		return this.commentdao.getCommentByMateno(mateno);
	}

	@Override
	public Cm_CommentVO saveComment(Cm_CommentVO comment, Long mateno) {
		
		return this.commentdao.saveComment(comment,mateno);
	}

	@Override
	public Cm_CommentVO updateComment(Long commentNo, Cm_CommentVO comment) {
		
		return this.commentdao.updateComment(commentNo,comment);
	}

	

	@Override
	@Transactional
	public void deleteComment(Long commentNo) {
		this.commentdao.deleteComment(commentNo);
	}

	@Override
	public Cm_CommentVO getCommentById(Long commentNo) {
		
		return commentdao.getCommentById(commentNo);
	}

	@Override
	public Cm_CommentVO insertComment(Cm_CommentVO comment, Long parentCommentId) {
		
		return commentdao.insertComment(comment,parentCommentId);
	}

	@Override
	public List<Cm_CommentVO> getRepliesByParentComment(Cm_CommentVO parentComment) {
		
		return commentdao.getRepliesByParentComment(parentComment);
	}

	
	



	
}
