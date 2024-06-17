package net.daum.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.ChatVO;
import net.daum.vo.MemberVO;
import net.daum.vo.MessageVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private ChatRepository chatRepo;
	@Autowired
	private MessageRepository messageRepo;

	@Override
	public void insertMember(MemberVO m) {
		this.memberRepo.save(m);
	}

	@Override
	public MemberVO loginCheck(String member_id) {
		return this.memberRepo.loginCheck(member_id);

	}

	@Override
	public MemberVO idCheck(String id) {

		Optional<MemberVO> opMember = this.memberRepo.findById(id);
		MemberVO member;
		if(opMember.isPresent()) {
			member = opMember.get();
		}else {
			member = null;
		}
		return member;
	}

	@Override
	public MemberVO idAndMailCheck(String member_id, String mail_id, String mail_domain) {
		return this.memberRepo.idAndMailCheck(member_id, mail_id, mail_domain);
	}

	@Transactional
	@Override
	public void updatePwd(MemberVO m) {
		this.memberRepo.updatePwd(m.getMember_id(), m.getMember_pwd());
	}

	@Transactional
	@Override
	public void update_Edit(MemberVO m) {
		this.memberRepo.update_Edit(m.getMember_name(),m.getResident_id(),m.getResident_id2(),m.getMember_id(),
				m.getMember_phone01(),m.getMember_phone02(),m.getMember_phone03(),m.getMail_id(),m.getMail_domain()
				,m.getSample6_postcode(),m.getSample6_address(),m.getSample6_detailAddress(),m.getSample6_extraAddress());
		
	}

	@Override
	public void deldeteUser(String userId) {
		this.memberRepo.deleteById(userId);
		
	}

	@Override
	public long getChatNumber(String member_id) {
		return this.chatRepo.findChatNumberByMemberId(member_id);
	}

	@Override
	public List<String> getAllUserId() {
		return this.memberRepo.getAllUserId();
	}

	@Override
	public void setMessage(MessageVO msg) {
		this.messageRepo.save(msg);
	}

	@Override
	public List<MessageVO> getAllMessage(long chatNumber) {

		List<MessageVO> list=new ArrayList<>();
		list=this.messageRepo.getAllMessage(chatNumber);
		
		return list;
		
	}

}
