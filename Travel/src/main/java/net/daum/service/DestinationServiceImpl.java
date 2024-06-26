package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.DestinationDAO;
import net.daum.vo.DestinationVO;

@Service
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	private DestinationDAO destinationDao;
	
	@Override
	public List<DestinationVO> getDestination(int planNo) {
		return this.destinationDao.getDestination(planNo);
	}

	@Override
	public void deleteDestinationsByPlanNo(int planNo) {
		this.destinationDao.deleteDestinationsByPlanNo(planNo);
	}

	@Override
	public void saveDestination(DestinationVO dv) {
		this.destinationDao.saveDestination(dv);
	}

}
