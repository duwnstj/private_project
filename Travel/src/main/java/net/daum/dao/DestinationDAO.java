package net.daum.dao;

import java.util.List;

import net.daum.vo.DestinationVO;

public interface DestinationDAO {

	List<DestinationVO> getDestination(int planNo);

	void deleteDestinationsByPlanNo(int planNo);

	void saveDestination(DestinationVO dv);

}
