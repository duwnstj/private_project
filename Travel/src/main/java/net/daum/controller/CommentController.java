package net.daum.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.daum.service.CommentService;
import net.daum.service.MemberService;
import net.daum.vo.Cm_CommentVO;
import net.daum.vo.Community_boardVO;
import net.daum.vo.MemberVO;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private MemberService memberService;

    // 댓글 추가 기능
    @PostMapping(value = "/add/{mateno}")
    public ResponseEntity<Cm_CommentVO> addComment(@RequestBody Cm_CommentVO comment, @PathVariable Long mateno,
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        // 현재 인증된 사용자의 Id(username)가져오기
        String username = userDetails.getUsername();

        // 사용자명으로 사용자 정보 조회
        MemberVO m = this.memberService.idCheck(username);

        comment.setCommentWriter(username);
        Community_boardVO board = new Community_boardVO();
        board.setMateno(mateno);
        comment.setCommunityBoard(board);

        try {
            Cm_CommentVO savedComment = commentService.saveComment(comment, mateno);
            return ResponseEntity.ok(savedComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 특정 게시물의 댓글 조회
    @GetMapping("/list/{mateno}")
    public ResponseEntity<List<Cm_CommentVO>> getCommentsByMateno(@PathVariable Long mateno) {
        try {
            List<Cm_CommentVO> comments = commentService.getCommentByMateno(mateno);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 댓글 수정 기능
    @PutMapping("/update/{commentNo}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentNo, @RequestBody Map<String, String> request,
            @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        String username = userDetails.getUsername();
        Cm_CommentVO existingComment = commentService.getCommentById(commentNo);

        if (!username.equals(existingComment.getCommentWriter())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("댓글 수정 권한이 없습니다.");
        }

        if (request.containsKey("commentText")) {
            String newCommentText = request.get("commentText");
            existingComment.setCommentText(newCommentText);

            try {
                Cm_CommentVO updatedComment = commentService.updateComment(commentNo, existingComment);
                return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정 중 오류가 발생했습니다.");
            }
        } else {
            return ResponseEntity.ok("권한 확인됨");
        }
    }

    // 댓글 삭제 기능
    @DeleteMapping("/delete/{commentNo}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentNo, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Cm_CommentVO existingComment = commentService.getCommentById(commentNo);

        if (!username.equals(existingComment.getCommentWriter())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("댓글 삭제 권한이 없습니다.");
        }

        try {
            commentService.deleteComment(commentNo);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 대댓글 추가 기능
    @PostMapping("/addReply/{mateno}")
    public ResponseEntity<Cm_CommentVO> addReply(@RequestBody Cm_CommentVO comment, @PathVariable Long mateno,
            @RequestParam Long parentCommentId, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        MemberVO m = this.memberService.idCheck(username);
        comment.setCommentWriter(username);
        Community_boardVO board = new Community_boardVO();
        board.setMateno(mateno);
        comment.setCommunityBoard(board);
        
        Cm_CommentVO parentComment = new Cm_CommentVO();
        parentComment.setCommentNo(parentCommentId);
        comment.setParentComment(parentComment);

        try {
            Cm_CommentVO savedComment = commentService.insertComment(comment, parentCommentId);
            return ResponseEntity.ok(savedComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 대댓글 목록 보기
    @GetMapping("/replies/{parentCommentId}")
    public ResponseEntity<List<Cm_CommentVO>> getRepliesByParentComment(@PathVariable Long parentCommentId) {
        try {
            Cm_CommentVO parentComment = commentService.getCommentById(parentCommentId);
            List<Cm_CommentVO> replies = commentService.getRepliesByParentComment(parentComment);
            return ResponseEntity.ok(replies);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
