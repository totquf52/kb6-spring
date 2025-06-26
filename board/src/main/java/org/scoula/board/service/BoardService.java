package org.scoula.board.service;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;

import java.util.List;
import java.util.Optional;

/**
 * 📌 게시판 서비스 계층 인터페이스
 * → Controller와 Mapper 사이에서 비즈니스 로직을 수행하는 계층
 */
public interface BoardService {

    // 전체 목록 조회
    public List<BoardDTO> getList();

    // 게시글 1건 조회
    public BoardDTO get(Long no);

    // 새 게시글 등록
    public void create(BoardDTO board);

    // 기존 게시글 수정
    public boolean update(BoardDTO boardDTO);

    // 게시글 삭제
    public boolean delete(Long no);

    // 첨부파일 추가
    public BoardAttachmentVO getAttachment(Long no);

    // 첨부파일 삭제
    public boolean deleteAttachment(Long no);
}