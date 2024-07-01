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
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardAttachedFileRepository boardAttachedFileRepository;

//    private static final String UPLOAD_DIR = "d:\\dev\\data2";
    @Value("${file.upload-dir}")
    private String uploadDirectory;

    @Override
    public Long register(BoardDTO dto, MultipartFile[] files) {

//        Board b = new Board();
//        b.setTitle(dto.getTitle());
//        b.setContents(dto.getContents());
//        b.setWriter(dto.getWriter());
//        b.setRegDate(LocalDateTime.now());
//        b.setIp(dto.getIp());
//        b.setHits(0);
//        b.setStatus(1);
        System.out.println("files : " + files);
        System.out.println("저장될 디텍터리 : " + uploadDirectory);


        // board 테이블에 저장
        Board entity = dto.toEntity();
        Board saved = boardRepository.save(entity);
        // 아래부터는 attached_file 테이블에 저장



        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        Arrays.asList(files).forEach(file -> {
            String fileFullName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            String filePath = uploadDirectory + "/" + fileFullName;

            File dest = new File(filePath);
            try {
                file.transferTo(dest);

                BoardAttachedFile bfile = new BoardAttachedFile();
                bfile.setBoard(entity);
                bfile.setFilePath(fileFullName);
                bfile.setFileName(file.getOriginalFilename());
                boardAttachedFileRepository.save(bfile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return saved.getBno();
    }

    @Override
    public Page<Board> getList(int page, String keyword) {

        Pageable pageable = PageRequest.of(page, 10, Sort.by("bno").descending());

        Page<Board> list = null;
        if (keyword.equals("none")) {
            list = boardRepository.findAll(pageable);
        } else {
//            list = boardRepository.findByTitleContainingOrderByBnoDesc(keyword, pageable);
            list = boardRepository.findAllLikeKeyword(keyword, pageable);
        }

        return list;
    }

    @Override
    public Board readOne(Long no) {
//        Optional<Board> result = boardRepository.findById(no);
//
//        Board board = null;
//        if (result.isPresent()) {
//            board = result.get();
//        }
//        return board;

        return boardRepository.findById(no).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public void delete(Long no) {
        boardRepository.deleteById(no);
    }

    @Override
    public Board modify(BoardDTO dto) {
//        Board board = boardRepository.findById(dto.getBno()).orElseThrow(() -> new IllegalArgumentException());
//        board.setWriter(dto.getWriter());
//        board.setTitle(dto.getTitle());
//        board.setContents(dto.getContents());

        Board entity = dto.toEntity();
        Board saved = boardRepository.save(entity);
        return saved;
    }
}
