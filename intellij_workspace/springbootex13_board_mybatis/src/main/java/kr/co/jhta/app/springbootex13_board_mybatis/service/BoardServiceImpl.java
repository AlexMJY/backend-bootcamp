package kr.co.jhta.app.springbootex13_board_mybatis.service;

import kr.co.jhta.app.springbootex13_board_mybatis.dao.BoardAttachedDAO;
import kr.co.jhta.app.springbootex13_board_mybatis.dao.BoardDAO;
import kr.co.jhta.app.springbootex13_board_mybatis.dto.BoardAttachedFileDTO;
import kr.co.jhta.app.springbootex13_board_mybatis.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardDAO dao;
    private final BoardAttachedDAO bdao;

    @Value("${file.upload-dir}")
    private String uploadDirectory;

    @Override
    public List<BoardDTO> getList(int startNo, int endNo) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startNo", startNo);
        map.put("endNo", endNo);
        List<BoardDTO> list = dao.getAll(map);
        return list;
    }


    @Override
    public void register(BoardDTO dto, MultipartFile[] files) {
//        int seqno = dao.selectSequence();
//        dto.setBno((long) seqno);
        System.out.println("dto : " + dto.toString());
        dao.addOne(dto);
        System.out.println("dto sequence : " + dto.getBno());
        System.out.println("files : " + files);
        System.out.println("저장될 디텍터리 : " + uploadDirectory);

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
                BoardAttachedFileDTO bdto = new BoardAttachedFileDTO();
                bdto.setFilePath(fileFullName);
                bdto.setFileName(file.getOriginalFilename());
//                bdto.setBoardNo(seqno);
                bdao.addOne(bdto);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public BoardDTO getOne(Long no) {
        BoardDTO dto =  dao.getOne(no);
        return dto;
    }

    @Override
    public void deleteOne(Long no) {
        dao.deleteOne(no);
    }

    @Override
    public void modifyOne(BoardDTO dto) {
        dao.modifyOne(dto);
    }

    @Override
    public int getTotal() {
        int total = dao.getTotal();
        return total;
    }

    @Override
    public int getTotalByKeyword(String keyword) {
        return dao.getTotalByKeyword(keyword);
    }

    @Override
    public List<BoardDTO> getListByKeyword(int startNo, int endNo, String keyword) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("startNo", startNo);
        map.put("endNo", endNo);
        map.put("keyword", keyword);
        List<BoardDTO> list = dao.getAllByKeyWord(map);
        return list;
    }


//    private final BoardRepository boardRepository;
//    private final BoardAttachedFileRepository boardAttachedFileRepository;
//
////    private static final String UPLOAD_DIR = "d:\\dev\\data2";
//    @Value("${file.upload-dir}")
//    private String uploadDirectory;
//
//    @Override
//    public Long register(BoardDTO dto, MultipartFile[] files) {
//
////        Board b = new Board();
////        b.setTitle(dto.getTitle());
////        b.setContents(dto.getContents());
////        b.setWriter(dto.getWriter());
////        b.setRegDate(LocalDateTime.now());
////        b.setIp(dto.getIp());
////        b.setHits(0);
////        b.setStatus(1);
//        System.out.println("files : " + files);
//        System.out.println("저장될 디텍터리 : " + uploadDirectory);
//
//
//        // board 테이블에 저장
//        Board entity = dto.toEntity();
//        Board saved = boardRepository.save(entity);
//        // 아래부터는 attached_file 테이블에 저장
//
//
//
//        File uploadDir = new File(uploadDirectory);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
//
//        Arrays.asList(files).forEach(file -> {
//            String fileFullName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
//            String filePath = uploadDirectory + "/" + fileFullName;
//
//            File dest = new File(filePath);
//            try {
//                file.transferTo(dest);
//
//                BoardAttachedFile bfile = new BoardAttachedFile();
//                bfile.setBoard(entity);
//                bfile.setFilePath(fileFullName);
//                bfile.setFileName(file.getOriginalFilename());
//                boardAttachedFileRepository.save(bfile);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        return saved.getBno();
//    }
//
//    @Override
//    public Page<Board> getList(int page, String keyword) {
//
//        Pageable pageable = PageRequest.of(page, 10, Sort.by("bno").descending());
//
//        Page<Board> list = null;
//        if (keyword.equals("none")) {
//            list = boardRepository.findAll(pageable);
//        } else {
////            list = boardRepository.findByTitleContainingOrderByBnoDesc(keyword, pageable);
//            list = boardRepository.findAllLikeKeyword(keyword, pageable);
//        }
//
//        return list;
//    }
//
//    @Override
//    public Board readOne(Long no) {
////        Optional<Board> result = boardRepository.findById(no);
////
////        Board board = null;
////        if (result.isPresent()) {
////            board = result.get();
////        }
////        return board;
//
//        return boardRepository.findById(no).orElseThrow(() -> new IllegalArgumentException());
//    }
//
//    @Override
//    public void delete(Long no) {
//        boardRepository.deleteById(no);
//    }
//
//    @Override
//    public Board modify(BoardDTO dto) {
////        Board board = boardRepository.findById(dto.getBno()).orElseThrow(() -> new IllegalArgumentException());
////        board.setWriter(dto.getWriter());
////        board.setTitle(dto.getTitle());
////        board.setContents(dto.getContents());
//
//        Board entity = dto.toEntity();
//        Board saved = boardRepository.save(entity);
//        return saved;
//    }
}
