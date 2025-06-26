package org.scoula.board.service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final static String BASE_DIR = "c:/upload/board";
    final private BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {
        log.info("getList.........");

        // mapper에서 VO 목록 받은 후, DTO 변환시킨 전체목록 반환
        return mapper.getList().stream().map(BoardDTO::of).toList();
    }

    @Override
    public BoardDTO get(Long no) {
        log.info("get........." + no);
        BoardDTO board = BoardDTO.of(mapper.get(no));
        // 해당 게시글이 없는경우 NoSuchElementException 예외 던짐
        return Optional.ofNullable(board).orElseThrow(NoSuchElementException::new);
    }

    @Transactional // 2개 이상의 insert문이 실행될수있으므로 트랜잭션 처리
    @Override
    public void create(BoardDTO board) {
        log.info("create........." + board);
        BoardVO vo = board.toVo(); // DTO -> VO 변환
        mapper.create(vo);

        // 파일 업로드 처리
        List<MultipartFile> files = board.getFiles();
        if(files != null && !files.isEmpty()) {
            upload(vo.getNo(), files);
        }
    }

    private void upload(Long bno, List<MultipartFile> files) {
        for (MultipartFile file : files) {
            if(file.isEmpty()) {continue;} // 파일이 비어있으면 다음 파일 가져오기
            try{
                String uploadPath = UploadFiles.upload(BASE_DIR,file);
                BoardAttachmentVO attach = BoardAttachmentVO.of(file,bno,uploadPath);
                mapper.createAttachment(attach);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
    // 첨부파일 한 개 얻기
    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return mapper.getAttachment(no);
    }
    // 첨부파일 삭제
    @Override
    public boolean deleteAttachment(Long no) {
        return mapper.deleteAttachment(no) == 1;
    }

    @Override
    public boolean update(BoardDTO board) {
        log.info("update........." + board);
        // update SQL 실행 후 변경된 행이 1개면 true 반환
        return mapper.update(board.toVo()) == 1;
    }

    @Override
    public boolean delete(Long no) {
        // 1. 첨부파일 목록 먼저 불러오기
        List<BoardAttachmentVO> attachList = mapper.getAttachmentList(no);

        // 2. 반복문으로 하나씩 삭제
        for (BoardAttachmentVO attach : attachList) {
            mapper.deleteAttachment(attach.getNo());
        }

        // 3. 게시글 삭제
        return mapper.delete(no) == 1;
    }
}
