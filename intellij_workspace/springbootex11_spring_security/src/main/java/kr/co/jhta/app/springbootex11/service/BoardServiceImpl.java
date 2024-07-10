package kr.co.jhta.app.springbootex11.service;

import kr.co.jhta.app.springbootex11.domain.Board;
import kr.co.jhta.app.springbootex11.domain.BoardAttachedFile;
import kr.co.jhta.app.springbootex11.dto.BoardDTO;
import kr.co.jhta.app.springbootex11.repository.BoardAttachedFileRepository;
import kr.co.jhta.app.springbootex11.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final BoardAttachedFileRepository boardAttachedFileRepository;
    @Value("${file.upload-dir}")
    private String uploadDirectory;
   // private static final String UPLOAD_DIR= "d:\\dev\\data2";

    @Override
    public Long register(BoardDTO dto, MultipartFile[] files) {
        Board b = new Board();
        b.setTitle(dto.getTitle());
        b.setContents(dto.getContents());
        b.setWriter(dto.getWriter());
        b.setRegDate(LocalDateTime.now());
        b.setIp(dto.getIp());
        b.setHits(0);
        b.setStatus(1);

        System.out.println("files : " + files);
        System.out.println("저장디렉토리 : " + uploadDirectory);

        File uploadDir = new File(uploadDirectory);

        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }

        Arrays.asList(files).forEach( file -> {
            String fileFullName =UUID.randomUUID().toString()+"-"+file.getOriginalFilename();
            String filePath = uploadDirectory + "/" +fileFullName;
            File dest = new File(filePath);
            try {
                file.transferTo(dest);

                BoardAttachedFile bfile = new BoardAttachedFile();
                bfile.setBoard(b);
                bfile.setFilePath(fileFullName);
                bfile.setFileName(file.getOriginalFilename());
                boardAttachedFileRepository.save(bfile);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });


        Board saved = boardRepository.save(b);
        return saved.getBno();
    }

    @Override
    public Page<Board> getList(int page, String keyword) {
        Pageable   pageable =
                PageRequest.of(page, 10, Sort.by("bno").descending());

        Page<Board> list = null;

        if(keyword.equals("none")) {
            list = boardRepository.findAll(pageable);
        }else{
//            list =  boardRepository.findByTitleContainingOrderByBnoDesc(keyword,pageable);
            list =  boardRepository.findAllLikeKeyword(keyword,pageable);

        }


//        Board ==> DTO
        return list;

    }

    @Override
    public Board readOne(Long no) {
        Optional<Board> result = boardRepository.findById(no);
        Board board = null;
        if(result.isPresent()){
            board =  result.get();
        }
        return board;
    }

    @Override
    public void modify(BoardDTO dto) {

        Board entity = dto.toEntity();
        boardRepository.save(entity);

    }
}

