package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.File;


import javax.servlet.http.HttpServletResponse;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    final private BoardService service; // 생성자 주입

    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");
        model.addAttribute("list",service.getList());
    }

    // 게시글 작성 페이지로 이동
    @GetMapping("/create")
    public void create(){
        log.info("create");
    }

    // 게시글 등록 처리
    @PostMapping("/create")
    public String create(BoardDTO board){
        log.info("create: "+board);
        service.create(board);
        return "redirect:/board/list";
    }

    @GetMapping({"/get","/update"}) // /board/get?no=1
    public void get(@RequestParam("no") Long no, Model model){
        log.info("/get or /update");
        model.addAttribute("board",service.get(no));
    }

    @PostMapping("/update")
    public String update(BoardDTO board){
        log.info("update: "+board);
        service.update(board);
        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long no){
        log.info("delete: "+no);
        service.delete(no);
        return "redirect:/board/list";
    }

    @GetMapping("/download/{no}")
    @ResponseBody // view를 사용하지 않고, 직접 내보냄
    public void download(@PathVariable("no") Long no, HttpServletResponse response) throws Exception {
        BoardAttachmentVO attach = service.getAttachment(no);

        File file = new File(attach.getPath());

        UploadFiles.download(response, file, attach.getFilename());
    }
}
