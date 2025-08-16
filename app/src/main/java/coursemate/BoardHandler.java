package coursemate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BoardHandler implements MenuHandler {
  Scanner scanner;
  private int nextNo = 1;
  LinkedList boards = new LinkedList(); // 게시글 목록을 관리하는 BoardList 객체

  BoardHandler(Scanner scanner) {
    this.scanner = scanner;
  }

  public void execute() { // instance method가 되어야함. public을 생략하면 pakage 멤버가 됨.
    printMainMenu();

    label:
    while (true) {
      System.out.print("게시글관리> ");
      String input = scanner.nextLine();

      switch (input) {
        case "1":
          add();
          break;
        case "2":
          list();
          break;
        case "3":
          detail();
          break;
        case "4":
          update();
          break;
        case "5":
          delete();
          break;
        case "0":
          break label;
        case "menu":
          printMainMenu();
          break;
        default:
          System.out.println("잘못된 메뉴입니다. 다시 선택하세요.");
          break;
      }
    }
  }

  static void printMainMenu() {
    System.out.println("게시글관리:");
    System.out.println("1. 게시글 추가");
    System.out.println("2. 게시글 목록");
    System.out.println("3. 게시글 조회");
    System.out.println("4. 게시글 변경");
    System.out.println("5. 게시글 삭제");
    System.out.println("0. 메인");
  }

  private void add() {
    // 게시글 추가 로직을 구현합니다.
    // 게시글 제목과 내용을 입력받아서 저장.
    Board post = new Board();

    post.no = nextNo++; // 게시글 번호를 설정
    System.out.print("게시글 제목: ");
    post.title = scanner.nextLine();
    System.out.print("게시글 내용: ");
    post.content = scanner.nextLine();
    post.date = LocalDateTime.now(); // 현재 시간을 ISO 8601 형식으로 저장

    boards.add(post); // Add the new post to the array
    System.out.println("게시글이 추가되었습니다.");
  }

  private void list() {
    Object[] list = boards.list(); // 게시글 목록을 가져옵니다.

    if (list == null) {
      System.out.println("등록된 게시글이 없습니다.");
    } else {

      System.out.println("[게시글 목록]");
      System.out.println("==================================");
      System.out.println("번호 | 제목               | 작성일");
      System.out.println("----------------------------------");

      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      for (Object obj : list) {
        Board post = (Board) obj; // Object를 Board로 캐스팅
        System.out.printf(
            "%-4d | %-18s | %s\n", post.no, post.title, post.date.format(dateFormatter));
      }
    }
  }

  private void detail() {
    if (boards.size() == 0) {
      System.out.println("등록된 게시글이 없습니다.");
      return;
    }
    int postNo;
    System.out.print("상세보기할 게시글 번호를 입력하세요: ");

    try {
      postNo = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("유효한 번호를 입력하세요.");
      return;
    }

    Board board = null;
    int index = -1;
    for (int i = 0; i < boards.size(); i++) {
      board = (Board) boards.get(i);
      if (board.no == postNo) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.printf("게시글 번호 %d는 존재하지 않습니다.\n", postNo);
      return;
    }

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");

    System.out.println("==================================");
    System.out.println("제목: " + board.title);
    System.out.println("내용: " + board.content);
    System.out.println("작성 시간: " + board.date.format(timeFormatter));
    System.out.println("==================================");
  }

  private void update() {
    if (boards.size() == 0) {
      System.out.println("등록된 게시글이 없습니다.");
      return;
    }

    System.out.print("수정할 게시글 번호를 입력하세요: ");
    int postNo;
    try {
      postNo = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("유효한 번호를 입력하세요.");
      return;
    }

    int index = -1;
    Object[] list = boards.list();

    for (int i = 0; i < list.length; i++) {
      if (((Board) list[i]).no == postNo) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.printf("게시글 번호 %d는 존재하지 않습니다.\n", postNo);
      return;
    }

    Board updatedPost = (Board) boards.get(index);

    System.out.println("==================================");
    System.out.println("현재 제목: " + updatedPost.title);
    System.out.print("새로운 제목: ");
    String newTitle = scanner.nextLine();
    if (!newTitle.isEmpty()) {
      updatedPost.title = newTitle;
    }

    System.out.println("현재 내용: " + updatedPost.content);
    System.out.print("새로운 내용: ");
    String newContent = scanner.nextLine();
    if (!newContent.isEmpty()) {
      updatedPost.content = newContent;
    }
    System.out.println("==================================");

    // yes or no
    System.out.print("수정 내용을 저장하시겠습니까? (yes/no): ");
    String answer = scanner.nextLine();
    if (!answer.equalsIgnoreCase("yes")) {
      System.out.println("수정이 취소되었습니다.");
      return;
    } else {
      // 게시글 수정 로직을 구현합니다.
      // 게시글 제목과 내용을 입력받아서 저장.
      updatedPost.date = LocalDateTime.now(); // 현재 시간을 ISO 8601 형식으로 저장
      boards.set(index, updatedPost); // Update the post in the array
    }

    System.out.println("게시글이 수정되었습니다.");
  }

  private void delete() {

    if (boards.size() == 0) {
      System.out.println("등록된 게시글이 없습니다.");
      return;
    }

    System.out.print("삭제할 게시글 번호를 입력하세요: ");
    int postNo;
    try {
      postNo = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("유효한 번호를 입력하세요.");
      return;
    }

    int index = -1;

    for (int i = 0; i < boards.size(); i++) {
      Board board = (Board) boards.get(i);
      if (board.no == postNo) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.printf("게시글 번호 %d는 존재하지 않습니다.\n", postNo);
      return;
    }

    System.out.print("정말 삭제하시겠습니까? (yes/no) ");
    String answer = scanner.nextLine();

    if (answer.equalsIgnoreCase("yes")) {
      boards.remove(index); // 게시글 삭제
      System.out.println("게시글이 삭제되었습니다.");
    } else {
      System.out.println("삭제가 취소되었습니다.");
    }
  }
}
