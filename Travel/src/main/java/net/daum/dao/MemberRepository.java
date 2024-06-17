package net.daum.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, String> {

	@Query("select m from MemberVO m where m.member_id=?1")
	public MemberVO loginCheck(String member_id);
	
	@Query("select z from MemberVO z where z.member_id=?1 and z.mail_id=?2 and z.mail_domain=?3")
	public MemberVO idAndMailCheck(String member_id, String mail_id, String mail_domain);

	@Modifying
	@Query("update MemberVO m set m.member_pwd=?2 where m.member_id=?1")
	public void updatePwd(String member_id, String member_pwd);

	
	@Modifying
	@Query("update MemberVO m set m.member_name=?1,m.resident_id=?2,m.resident_id2=?3,m.member_phone01=?5, "
			+"m.member_phone02=?6,m.member_phone03=?7,m.mail_id=?8,m.mail_domain=?9,m.sample6_postcode=?10, "
			+"m.sample6_address=?11,m.sample6_detailAddress=?12,m.sample6_extraAddress=?13 where m.member_id=?4")
	public void update_Edit(String member_name, String resident_id, String resident_id2, String member_id,
			String member_phone01, String member_phone02, String member_phone03, String mail_id, String mail_domain,
			String sample6_postcode, String sample6_address, String sample6_detailAddress, String sample6_extraAddress);

	@Query("select m.member_id from MemberVO m")
	public List<String> getAllUserId();

	
}
