package javaz.nio;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

// FileSystem
// 파티션 표시
public class FileSystemMain {
    public static void main(String[] args) throws IOException {
        FileSystem fileSystem = FileSystems.getDefault();

        for (FileStore fs : fileSystem.getFileStores()) {
            System.out.println("드라이브 : " + fs.name());
            System.out.println("파일 시스템 : " + fs.type());
            System.out.println("전체 용량 : " + fs.getTotalSpace());
            System.out.println("사용 중인 용량 : " + (fs.getTotalSpace() - fs.getUnallocatedSpace()));
            System.out.println("사용 가능한 공간 : " + fs.getUsableSpace());

            System.out.println();
        }
    }
}
