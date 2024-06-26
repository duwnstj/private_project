package net.daum.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import net.daum.vo.DestinationVO;

public interface DestinationRepository extends JpaRepository<DestinationVO, Integer> {
	List<DestinationVO> findByPlan_PlanNo(int planNo);

	void deleteByPlan_PlanNo(int planNo);
}
