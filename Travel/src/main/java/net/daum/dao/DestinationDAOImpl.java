package net.daum.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.DestinationVO;

@Repository
public class DestinationDAOImpl implements DestinationDAO {

	@Autowired
	private DestinationRepository DestinationRepo;
	
	@Override
	public List<DestinationVO> getDestination(int planNo) {
		return this.DestinationRepo.findByPlan_PlanNo(planNo);
	}

	@Transactional
	@Override
	public void deleteDestinationsByPlanNo(int planNo) {
		this.DestinationRepo.deleteByPlan_PlanNo(planNo);
	}

	@Transactional
	@Override
	public void saveDestination(DestinationVO dv) {
		this.DestinationRepo.save(dv);
	}

}
