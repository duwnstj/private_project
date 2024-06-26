package net.daum.service;

import java.util.List;

import net.daum.vo.DestinationVO;

public interface DestinationService {

	List<DestinationVO> getDestination(int planNo);

	void deleteDestinationsByPlanNo(int planNo);

	void saveDestination(DestinationVO dv);

}
