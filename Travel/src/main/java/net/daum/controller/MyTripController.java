package net.daum.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import net.daum.service.MemberService;
import net.daum.service.PlanService;
import net.daum.vo.MemberVO;
import net.daum.vo.PlanVO;

@Controller
public class MyTripController {// MyTrip Controller
	@Autowired
	private PlanService planservice;
	
	@Autowired
	private MemberService memberService;
	
	public class planComparator implements Comparator<PlanVO>{
		@Override
		public int compare(PlanVO plan1, PlanVO plan2) {
			return Integer.compare(plan1.getPlanNo(), plan2.getPlanNo());
		}
	}
	@GetMapping("/mytrip")
	public ModelAndView myTrip (@AuthenticationPrincipal UserDetails userDetails) {
		ModelAndView mv= new ModelAndView("/jsp/my_trip");
		String memberID = userDetails.getUsername();
		MemberVO member = memberService.idCheck(memberID);
		
		//회원의 일정번호 가져오기
		List<PlanVO> myPlan= member.getPlanVO();
		Collections.sort(myPlan, new planComparator());
		
		System.out.println(myPlan.get(0));
		mv.addObject("planList", myPlan);
		return mv;
	}
}
