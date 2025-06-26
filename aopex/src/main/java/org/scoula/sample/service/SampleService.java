package org.scoula.sample.service;

/**
 * SampleService 인터페이스
 * 두 개의 문자열을 입력받아 Integer 결과를 반환
 */
public interface SampleService {
    Integer doAdd(String str1, String str2) throws Exception;
}