package net.daum.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.PlanVO;

public interface PlanRepository extends JpaRepository<PlanVO, Integer> {

	@Query("select m from PlanVO m")// join fetch m.cities
	List<PlanVO> allUserPlan();

}
