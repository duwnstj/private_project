package net.daum.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.daum.service.DestinationService;
import net.daum.service.MemberService;
import net.daum.service.PlanService;
import net.daum.vo.CityVO;
import net.daum.vo.DestinationVO;
import net.daum.vo.MemberVO;
import net.daum.vo.PlanVO;

@Controller
public class ItineraryController {

	@Autowired
    private PlanService planservice;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private DestinationService destinationService;
	
	@PostMapping("/itinerary/{nationalCode}")
	public String itinerary(@PathVariable String nationalCode,
                                  @AuthenticationPrincipal UserDetails userDetails,
                                  @RequestParam("departureDate") Date departureDate,
                                  @RequestParam("arrivalDate") Date arrivalDate,
                                  @RequestParam("selectedCityCodes") List<String> selectedCityCodes,
                                  @RequestParam("placeLatitude[]") List<Double> placeLatitude,
                                  @RequestParam("placeLongitude[]") List<Double> placeLongitude,
                                  @RequestParam("placeName[]") List<String> placeName)
	{
        PlanVO p= new PlanVO();// planVO에 저장하기 위한 planVO p 객체 생성?
        String userID=userDetails.getUsername();
        MemberVO m= this.memberService.idCheck(userID);
        
        p.setArrivalDate(arrivalDate);
        p.setDepartureDate(departureDate);
        p.setMemberVO(m);
        
        // 도시코드로 CityVO객체 조회 후 PlanVO에 추가
        List<CityVO> cities= new ArrayList<>();
        for(String cityCode: selectedCityCodes) {
        	CityVO CV= this.planservice.getCityCode(cityCode);
        	if( CV != null) {
        		cities.add(CV);
        	}
        }
        p.setCities(cities);
        
        this.planservice.insertPlan(p);// 일정 저
        
        		for(int i=0; i<placeLatitude.size(); i++) {
        		DestinationVO d= new DestinationVO();// DestinationVO에 저장하기위한 DestinationVO d 객체 생성?
        	    d.setPlan(p);
        	    // 일정번호 설정
        	    Double latitude= placeLatitude.get(i);
        	    Double longitude= placeLongitude.get(i);
        	    String name= placeName.get(i);
        	    d.setPlaceLatitude(latitude);
        	    d.setPlaceLongitude(longitude);
        	    d.setPlaceName(name);
        	    this.planservice.insertDestination(d);
        	    }
        		return "redirect:/mytrip";
	}
	
    @GetMapping("/itinerary")
    public ModelAndView viewItinerary(@RequestParam int planNo) {
        ModelAndView mv = new ModelAndView("/jsp/itinerary");
        PlanVO pv= this.planservice.getPlan(planNo);
        List<DestinationVO> destinations= this.destinationService.getDestination(planNo);
        
        if(pv == null) {
        	throw new RuntimeException("여행일정을 찾을 수 없습니다.");
        }
        
        mv.addObject("nationalCode", pv.getCities().get(0).getNationalCode().getNationalCode());
        mv.addObject("destinations", destinations);
        mv.addObject("departureDate", pv.getDepartureDate());
        mv.addObject("arrivalDate", pv.getArrivalDate());
        mv.addObject("planNo", planNo);
        return mv;
    }
    
 // 수정된 일정의 목적지 정보를 업데이트하는 PUT 매핑
    @PutMapping("/itinerary/edit/{planNo}")
    public String updateDestination(@PathVariable int planNo,
                                    @RequestBody List<Map<String, Object>> destinations) {
    	// 기존 일정에 속하는 목적지들 삭제
        destinationService.deleteDestinationsByPlanNo(planNo);
        
        for (Map<String, Object> destination : destinations) {
        	
            DestinationVO dv = new DestinationVO();
            PlanVO plan= this.planservice.getPlan(planNo);
            
            dv.setPlan(plan); // 해당 일정에 속하도록 설정
            dv.setPlaceName((String) destination.get("placeName"));
            dv.setPlaceLatitude((Double) destination.get("placeLatitude"));
            dv.setPlaceLongitude((Double) destination.get("placeLongitude"));
            destinationService.saveDestination(dv);
        }
        
        return "redirect:/itinerary?planNo=" +planNo;
    }
    
    @DeleteMapping("/itinerary/delete/{planNo}")
    public ResponseEntity<String> deletePlan(@PathVariable int planNo){
    	this.destinationService.deleteDestinationsByPlanNo(planNo);
    	
    	return ResponseEntity.ok("일정 삭제 완료");
    }
}

