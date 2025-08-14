package coursemate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BoardHandler implements MenuHandler {
  Scanner scanner;
  ArrayList<HashMap<String, String>> posts = new ArrayList<>();

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
    HashMap<String, String> post = new HashMap<>();
    System.out.print("게시글 제목: ");
    post.put("title", scanner.nextLine());
    System.out.print("게시글 내용: ");
    post.put("content", scanner.nextLine());
    post.put("date", java.time.LocalDateTime.now().toString());
    posts.add(post);
    System.out.println("게시글이 추가되었습니다.");
  }

  private void list() {
    if (posts.isEmpty()) {
      System.out.println("등록된 게시글이 없습니다.");
      return;
    }

    System.out.println("[게시글 목록]");

    System.out.println("==================================");
    System.out.println("번호 | 제목               | 작성일");
    System.out.println("----------------------------------");

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    for (int i = 0; i < posts.size(); i++) {
      HashMap<String, String> post = posts.get(i);
      LocalDateTime dateTime = LocalDateTime.parse(post.get("date"));
      System.out.printf(
          "%-4d | %-18s | %s\n", i + 1, post.get("title"), dateTime.format(dateFormatter));
    }

    System.out.println("==================================");
  }

  private void detail() {
    // 게시글 상세 정보를 출력하는 로직을 구현합니다.
    // 게시글 제목, 내용, 작성일 등을 출력.
    if (posts.isEmpty()) {
      System.out.println("등록된 게시글이 없습니다.");
      return;
    }

    System.out.print("상세보기할 게시글 번호를 입력하세요: ");
    int postNumber;
    try {
      postNumber = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("유효한 번호를 입력하세요.");
      return;
    }

    if (postNumber < 1 || postNumber > posts.size()) {
      System.out.println("해당 번호의 게시글이 존재하지 않습니다.");
      return;
    }

    HashMap<String, String> post = posts.get(postNumber - 1);
    LocalDateTime dateTime = LocalDateTime.parse(post.get("date"));
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    System.out.println("==================================");
    System.out.println("제목: " + post.get("title"));
    System.out.println("내용: " + post.get("content"));
    System.out.println("작성 시간: " + dateTime.format(timeFormatter));
    System.out.println("==================================");
  }

  private void update() {
    // 게시글 수정 로직을 구현합니다.
    // 게시글 제목과 내용을 입력받아서 수정.
    // 게시글이 존재하지 않으면 오류 메시지 출력.
    if (posts.isEmpty()) {
      System.out.println("등록된 게시글이 없습니다.");
      return;
    }

    System.out.print("수정할 게시글 번호를 입력하세요: ");
    int postNumber;
    try {
      postNumber = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("유효한 번호를 입력하세요.");
      return;
    }

    if (postNumber < 1 || postNumber > posts.size()) {
      System.out.println("해당 번호의 게시글이 존재하지 않습니다.");
      return;
    }

    HashMap<String, String> post = posts.get(postNumber - 1);
    System.out.println("==================================");
    System.out.println("현재 제목: " + post.get("title"));
    System.out.print("새로운 제목: ");
    String newTitle = scanner.nextLine();

    System.out.println("현재 내용: " + post.get("content"));
    System.out.print("새로운 내용: ");
    String newContent = scanner.nextLine();
    System.out.println("==================================");
    post.put("title", newTitle);
    post.put("content", newContent);

    System.out.println("게시글이 수정되었습니다.");
  }

  private void delete() {
    // 게시글 삭제 로직을 구현합니다.
    // 게시글 번호를 입력받아서 삭제.
    // 게시글이 존재하지 않으면 오류 메시지 출력.
    if (posts.isEmpty()) {
      System.out.println("등록된 게시글이 없습니다.");
      return;
    }

    System.out.print("삭제할 게시글 번호를 입력하세요: ");
    int postNumber;
    try {
      postNumber = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("유효한 번호를 입력하세요.");
      return;
    }

    if (postNumber < 1 || postNumber > posts.size()) {
      System.out.println("해당 번호의 게시글이 존재하지 않습니다.");
      return;
    }

    posts.remove(postNumber - 1);
    System.out.println("게시글이 삭제되었습니다.");
  }
}
