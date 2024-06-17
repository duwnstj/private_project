package net.daum.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.MemberDAO;
import net.daum.vo.ChatVO;
import net.daum.vo.MemberVO;
import net.daum.vo.MessageVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDao;

	@Override
	public void insertMember(MemberVO m) {
		this.memberDao.insertMember(m);
	}

	@Override
	public MemberVO loginCheck(String member_id) {
		return this.memberDao.loginCheck(member_id);
	}

	@Override
	public MemberVO idCheck(String id) {
		return this.memberDao.idCheck(id);
	}

	@Override
	public MemberVO idAndMailCheck(String member_id, String mail_id, String mail_domain) {
		return this.memberDao.idAndMailCheck(member_id, mail_id, mail_domain);
	}

	@Override
	public void updatePwd(MemberVO m) {
		this.memberDao.updatePwd(m);
	}

	@Override
	public void update_Edit(MemberVO m) {
		this.memberDao.update_Edit(m);
	}

	@Override
	public void deleteUser(String userId) {
		this.memberDao.deldeteUser(userId);
	}

	@Override
	public long getChatNumber(String member_id) {
		
		return this.memberDao.getChatNumber(member_id);
	}

	@Override
	public List<String> getAllUserId() {
		return this.memberDao.getAllUserId();
	}

	@Override
	public void setMessage(MessageVO msg) {
		this.memberDao.setMessage(msg); 
	}

	@Override
	public List<MessageVO> getAllMessage(long chatNumber) {
		return this.memberDao.getAllMessage(chatNumber);
	}


}
