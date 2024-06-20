package net.daum.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.daum.service.AddScheduleService;
import net.daum.service.MemberService;
import net.daum.vo.CityVO;
import net.daum.vo.MemberVO;
import net.daum.vo.NationalVO;

@Controller
public class AddScheduleController {

	@Autowired
	private AddScheduleService addscheduleservice;
	@Autowired 
	private MemberService memberService;
	
	// 계획추가 메인 페이지
	@GetMapping("/addschedule")
	public String getNlist(Model model,@AuthenticationPrincipal UserDetails userDetails,HttpServletResponse response)
	throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String username=userDetails.getUsername();
		MemberVO m= this.memberService.idCheck(username);
		//이 방법 안쓰고 시큐리티로 권한 가져오는 걸로도 해보기.
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<GrantedAuthority> cAuthorities = new ArrayList<>(auth.getAuthorities());
//		System.out.println(cAuthorities.get(0));//ROLE_PAIDUSER
//		System.out.println(cAuthorities.contains(new SimpleGrantedAuthority("ROLE_PAIDUSER")));//true
//		System.out.println(cAuthorities.contains(new SimpleGrantedAuthority("ROLE_NOPAIDUSER")));//false

		if(m.getRole().equals("NOPAIDUSER")) {
			out.println("<script>");
			out.println("alert('결제하셔야 해용! ㅋ');");	
			out.println("window.location.href = '/homepage';");
			out.println("</script>");
			
			return null;
		}else {
			List<NationalVO> nList= this.addscheduleservice.findNname();

			model.addAttribute("Nlist", nList);
			return "jsp/add_schedule";
		}
		
	}
}



