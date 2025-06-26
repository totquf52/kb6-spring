package org.scoula.board.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * 📌 게시판 DTO 클래스 – 계층 간 데이터 전달 전용 객체
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
    // 첨부 파일
    private List<BoardAttachmentVO> attaches;

    List<MultipartFile> files = new ArrayList<>(); // 실제 업로드된 파일(Multipart) 목록

    // ✅ VO → DTO 변환
    public static BoardDTO of(BoardVO vo) {
        return vo == null ? null : BoardDTO.builder()
                .no(vo.getNo())
                .title(vo.getTitle())
                .content(vo.getContent())
                .writer(vo.getWriter())
                .attaches(vo.getAttaches())
                .regDate(vo.getRegDate())
                .updateDate(vo.getUpdateDate())
                .build();
    }

    // ✅ DTO → VO 변환
    public BoardVO toVo() {
        return BoardVO.builder()
                .no(no)
                .title(title)
                .content(content)
                .writer(writer)
                .attaches(attaches)
                .regDate(regDate)
                .updateDate(updateDate)
                .build();
    }
}