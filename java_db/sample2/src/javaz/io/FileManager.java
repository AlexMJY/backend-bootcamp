package javaz.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

    Scanner scanner;

    FileManager() {
        scanner = new Scanner(System.in);
    }

    public void menu() throws IOException {
        while (true) {
            System.out.println("--------------------JAVA FILE MANAGER --------------------");
            System.out.println("1.NEW    2.OPEN    3.COPY    4.DELETE    5.LIST    6.QUIT");
            System.out.print(">> 선택 : ");
//            int input = scanner.nextInt();
            String input = scanner.nextLine();


            if (input.equals("1")) {
                new_file();
            } else if (input.equals("2")) {
                open_file();
            } else if (input.equals("3")) {
                copy_file();
            } else if (input.equals("4")) {
                delete_file();
            } else if (input.equals("5")) {
                list_file();
            } else if (input.equals("6")) {
                System.out.println(">> 프로그램을 종료합니다.");
                System.exit(0);
            }
        }

    }

    private void new_file() throws IOException {
        System.out.println(">> 신규 파일 작성");
        System.out.print("파일명 : ");
        String filename = scanner.nextLine();

        System.out.print("경로명(미입력 시 현재 디렉토리) : ");
        String dir = scanner.nextLine();
        System.out.println("저장할 내용을 입력해 주세요. (종료는 /q");
        List<String> tokens = new ArrayList<>();


//        while (sc.hasNext()) {
//            tokens.add(sc.next());
//        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("/q")) {
                break; // 입력이 끝났으면 반복문 종료
            }
            tokens.add(line);
        }

        System.out.println(">> 파일 작성 완료");

        Path filePath = Paths.get(dir + filename);
        try {
            Path newFilePath = Files.createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Files.write(Paths.get(dir + filename), tokens);

    }

    private void open_file() throws IOException {
        System.out.println(">> 파일 열기");
        System.out.print("     파일명 : ");
        String filename = scanner.nextLine();
        System.out.print("     경로명(미입력 시 현재 디렉토리) : ");
        String dir = scanner.nextLine();

        System.out.println(">> 파일 내용 : ");
        String temp = dir + filename;

        try (Stream<String> lines = Files.lines(Paths.get(temp))) {
            String result = lines.collect(Collectors.joining("\n"));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void copy_file() {
        System.out.println(">> 파일을 복사합니다");
        System.out.print(">> 원본 파일명 : ");
        String file = scanner.nextLine();
        System.out.print(">> 경로명(미입력 시 현재 디렉토리) : ");
        String file_dir = scanner.nextLine();


        System.out.print(">> 사본 파일명 : ");
        String copied_file = scanner.nextLine();
        System.out.print(">> 경로명(미입력 시 현재 디렉토리) : ");
        String copied_file_dir = scanner.nextLine();

        Path original = Paths.get(file_dir + file);
        Path copied = Paths.get(copied_file_dir + copied_file);

        try {
            Files.copy(original, copied);
            System.out.println("파일 복사 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void delete_file() {
        System.out.println(" 파일을 삭제합니다.");

        System.out.print("삭제 파일명 : ");
        String file = scanner.nextLine();

        System.out.print("경로명(미입력 시 현재 디렉토리) : ");
        String dir = scanner.nextLine();

        Path del_file = Paths.get(dir + file);

        try {
            Files.deleteIfExists(del_file);
            System.out.println(">> 파일 삭제 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void list_file() {
        FileDirExercise.fileDir();
    }

//    private void quit_file() {
//        System.out.println(");
//
//
//    }


    public static void main(String[] args) throws IOException {
        new FileManager().menu();
    }

}
