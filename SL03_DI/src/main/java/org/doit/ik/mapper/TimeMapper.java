package org.doit.ik.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	String getTime();
	
	
	@Select("select sysdate +1 from dual") //쿠리문이 짧으면 SELECT 어노테이션 으로 작성가능, 그러나 쿼리문이 짧은 경우는 거의 없기떄문에  XML을 사용하는 것을 추천.
	String getNextTime();

}
