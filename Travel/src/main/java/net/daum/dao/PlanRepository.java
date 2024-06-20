package net.daum.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.daum.vo.PlanVO;

public interface PlanRepository extends JpaRepository<PlanVO, Integer> {


}
