package net.daum.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.PlanDAO;
import net.daum.vo.CityVO;
import net.daum.vo.DestinationVO;
import net.daum.vo.NationalVO;
import net.daum.vo.PlanVO;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanDAO planDao;

	@Override
	public NationalVO findNational(String nationalCode) {
		return this.planDao.findNational(nationalCode);
	}

	@Override
	public void insertPlan(PlanVO p) {
		this.planDao.insertPlan(p);
	}

	@Override
	public void insertDestination(DestinationVO d) {
		this.planDao.insertDestination(d);
	}

	@Override
	public CityVO getCityCode(String cityCode) {
		return this.planDao.getCityCode(cityCode);
	}

	@Override
	public List<PlanVO> allUserPlan() {	
		return this.planDao.allUserPlan();
	}

}
