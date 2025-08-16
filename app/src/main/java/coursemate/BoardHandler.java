package coursemate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BoardHandler implements MenuHandler {
  Scanner scanner;
  static final int MAX_SIZE = 100;
  private final Board[] boards = new Board[MAX_SIZE];
  private int size = 0;
  private int nextNo = 1;

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
    if (size >= MAX_SIZE) {
      System.out.println("더 이상 게시글을 추가할 수 없습니다.");
      return;
    }
    // 게시글 추가 로직을 구현합니다.
    // 게시글 제목과 내용을 입력받아서 저장.
    Board post = new Board();
    post.no = nextNo++; // 게시글 번호를 설정
    System.out.print("게시글 제목: ");
    post.title = scanner.nextLine();
    System.out.print("게시글 내용: ");
    post.content = scanner.nextLine();
    post.date = LocalDateTime.now(); // 현재 시간을 ISO 8601 형식으로 저장

    boards[size++] = post; // Add the new post to the array
    System.out.println("게시글이 추가되었습니다.");
  }

  private void list() {
    // 게시글이 없다면
    if (size == 0) {
      System.out.println("등록된 게시글이 없습니다.");
      return;
    }

    System.out.println("[게시글 목록]");

    System.out.println("==================================");
    System.out.println("번호 | 제목               | 작성일");
    System.out.println("----------------------------------");

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    for (int i = 0; i < size; i++) {
      // 게시글 정보를 출력합니다.
      // 게시글 번호, 제목, 작성일을 출력합니다.
      Board post = boards[i];
      LocalDateTime dateTime = post.date;
      System.out.printf("%-4d | %-18s | %s\n", post.no, post.title, dateTime.format(dateFormatter));

      // 만약 HashMap을 사용한다면 아래와 같이 작성할 수 있습니다.
      // (현재는 Board 클래스를 사용하고 있으므로 주석 처리)
      //      HashMap<String, String> post = posts.get(i);
      //      LocalDateTime dateTime = LocalDateTime.parse(post.get("date"));
      //      System.out.printf(
      //          "%-4d | %-18s | %s\n", i + 1, post.get("title"), dateTime.format(dateFormatter));
    }

    System.out.println("==================================");
  }

  private void detail() {
    if (size == 0) {
      System.out.println("등록된 게시글이 없습니다.");
      return;
    }

    System.out.print("상세보기할 게시글 번호를 입력하세요: ");
    int postNo;
    try {
      postNo = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("유효한 번호를 입력하세요.");
      return;
    }

    int index = -1;
    for (int i = 0; i < size; i++) {
      if (boards[i].no == postNo) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.printf("게시글 번호 %d는 존재하지 않습니다.\n", postNo);
      return;
    }

    Board post = boards[index];
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");

    System.out.println("==================================");
    System.out.println("제목: " + post.title);
    System.out.println("내용: " + post.content);
    System.out.println("작성 시간: " + post.date.format(timeFormatter));
    System.out.println("==================================");
  }

  private void update() {

    if (size == 0) {
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
    for (int i = 0; i < size; i++) {
      if (boards[i].no == postNo) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      System.out.printf("게시글 번호 %d는 존재하지 않습니다.\n", postNo);
      return;
    }

    Board post = boards[index];

    System.out.println("==================================");
    System.out.println("현재 제목: " + post.title);
    System.out.print("새로운 제목: ");
    String newTitle = scanner.nextLine();
    if (!newTitle.isEmpty()) {
      post.title = newTitle;
    }

    System.out.println("현재 내용: " + post.content);
    System.out.print("새로운 내용: ");
    String newContent = scanner.nextLine();
    if (!newContent.isEmpty()) {
      post.content = newContent;
    }
    System.out.println("==================================");

    System.out.println("게시글이 수정되었습니다.");
  }

  private void delete() {
    if (size == 0) {
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
    for (int i = 0; i < size; i++) {
      if (boards[i].no == postNo) {
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
      for (int i = index; i < size - 1; i++) {
        boards[i] = boards[i + 1];
      }
      boards[size - 1] = null; // Clear the last element
      size--;
      System.out.println("게시글이 삭제되었습니다.");
    } else {
      System.out.println("삭제가 취소되었습니다.");
    }
  }
}
