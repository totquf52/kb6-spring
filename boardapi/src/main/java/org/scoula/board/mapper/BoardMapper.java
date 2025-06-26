package org.scoula.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;

/**
 * 📌 게시글 목록을 DB에서 가져오는 Mapper 인터페이스
 */
public interface BoardMapper {

    // 게시글 전체 조회
    public List<BoardVO> getList();

    // 게시글 번호로 단건 조회
    public BoardVO get(Long no);

    // 게시글 등록
    public void create(BoardVO board);

    // 게시글 수정 (제목, 내용만 수정 가능)
    public int update(BoardVO board);

    // 게시글 삭제 (PK 기준 삭제)
    public int delete(Long no);

    // 파일 생성
    public void createAttachment(BoardAttachmentVO attach);

    // 해당 게시글의 파일 목록 조회
    public List<BoardAttachmentVO> getAttachmentList(Long bno);

    // 특정 파일 조회
    public BoardAttachmentVO getAttachment(Long no);

    // 특정 파일 삭제
    public int deleteAttachment(Long no);
}