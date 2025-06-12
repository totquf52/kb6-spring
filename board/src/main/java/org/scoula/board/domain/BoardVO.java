package org.scoula.board.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO {
    private Long no;            // 게시글 번호
    private String title;       // 게시글 제목
    private String content;     // 게시글 내용
    private String writer;      // 작성자
    private Date regDate;       // 등록일
    private Date updateDate;    // 수정일
}