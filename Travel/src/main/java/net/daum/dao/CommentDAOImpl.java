package net.daum.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.Cm_CommentVO;
import net.daum.vo.Community_boardVO;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private PostRepository postRepo;
	
	
	@Override
	public List<Cm_CommentVO> getCommentByMateno(Long mateno) {
		
		return this.commentRepo.findByCommunityBoardMateno(mateno);
	}


	@Override
	public Cm_CommentVO saveComment(Cm_CommentVO comment, Long mateno) {
		//Community_boardVO를 데이터 베이스에서 조회
		System.out.println("\n 댓글 추가하기");
		Community_boardVO communityBoard=postRepo.findById(mateno)
				.orElseThrow(()->new IllegalArgumentException("게시물 번호에 해당하는 게시물이 없습니다. mateno: " + mateno));
		comment.setCommunityBoard(communityBoard);
		return commentRepo.save(comment);
	}


	@Override
	public Cm_CommentVO updateComment(Long commentNo, Cm_CommentVO updatedComment) {
	    Cm_CommentVO comment = commentRepo.findById(commentNo)
	            .orElseThrow(() -> new NoSuchElementException("댓글 번호에 해당하는 댓글이 없습니다. commentNo: " + commentNo));
	    comment.setCommentText(updatedComment.getCommentText());
	    return this.commentRepo.save(comment);
	}


	@Override
	public void deleteComment(Long commentNo) {
		try {
			//게시글 번호에 해당하는 댓글 번호만 삭제
			commentRepo.deleteById(commentNo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public Cm_CommentVO getCommentById(Long commentNo) {
		
		return this.commentRepo.findBycommentNo(commentNo);
	}


	@Override
	public Cm_CommentVO insertComment(Cm_CommentVO comment, Long parentCommentId) {
		 Cm_CommentVO parentComment = commentRepo.findById(parentCommentId)
				 .orElseThrow(() -> new IllegalArgumentException("부모 댓글을 찾을 수 없습니다.")); 
	    comment.setParentComment(parentComment);
	    comment.setCommunityBoard(parentComment.getCommunityBoard()); // 게시글 정보도 설정
	    return commentRepo.save(comment);
	}

	@Override
	public List<Cm_CommentVO> getRepliesByParentComment(Cm_CommentVO parentComment) {
	    List<Cm_CommentVO> replies = commentRepo.findByParentComment(parentComment);
	    Community_boardVO board = parentComment.getCommunityBoard();
	    for (Cm_CommentVO reply : replies) {
	        reply.setCommunityBoard(board); // CommunityBoard 정보를 설정
	    }
	    return replies;
	}


	@Override
    public Cm_CommentVO updateReply(Long commentNo, Cm_CommentVO comment) throws Exception  {
        Cm_CommentVO existingComment = commentRepo.findById(commentNo)
        		.orElseThrow(() -> new Exception("Comment not found"));
        existingComment.setCommentText(comment.getCommentText());
        return commentRepo.save(existingComment);
    }
}



