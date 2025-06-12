package org.scoula.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    // 어노테이션 기반 SQL - 현재 시간 조회
    @Select("SELECT sysdate()")
    public String getTime();

    // getTime2()는 XML 매퍼에 SQL 정의 예정
    public String getTime2();
}