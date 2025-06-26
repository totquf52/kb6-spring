package org.scoula.board.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // Spring 테스트 기능을 확장
@ContextConfiguration(classes = { RootConfig.class }) // 스프링 설정 클래스 등록
@Log4j2 // Lombok의 로그 객체(log) 자동 생성
public class BoardMapperTest {

    @Autowired
    private BoardMapper mapper; // 테스트 대상 Mapper 주입

    @Test
    @DisplayName("BoardMapper의 목록 불러오기")
    public void getList() {
        for (BoardVO board : mapper.getList()) {
            log.info(board); // 조회된 각 게시글 출력
        }
    }

    @Test
    @DisplayName("BoardMapper의 게시글 읽기")
    public void get() {
        // 존재하는 게시물 번호로 테스트
        BoardVO board = mapper.get(1L);

        log.info(board);  // 조회된 게시물 정보 출력
    }

    @Test
    @DisplayName("BoardMapper의 새글 작성")
    public void create() {
        // 새 게시글 객체 생성 및 데이터 설정
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("user0");

        // DB에 insert 수행
        mapper.create(board);

        // 삽입된 VO 정보 로그로 출력
        log.info(board);
    }

    @Test
    @DisplayName("BoardMapper의 글 수정")
    public void update() {
        // 수정할 게시글 객체 생성 (기존 no가 있어야 함)
        BoardVO board = new BoardVO();
        board.setNo(5L); // PK 기준값
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("user00");

        // 업데이트 실행 후 결과 반환
        int count = mapper.update(board);

        log.info("UPDATE COUNT: " + count); // 수정된 행 개수 출력
    }

    @Test
    @DisplayName("BoardMapper의 글 삭제")
    public void delete() {
        // 게시글 번호 3번 삭제 요청 → 성공 시 1 반환
        log.info("DELETE COUNT: " + mapper.delete(3L));
    }
}