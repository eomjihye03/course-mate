package coursemate;

import java.util.Scanner;

public class BoardHandler implements MenuHandler {
  Scanner scanner;

  BoardHandler(Scanner scanner) {
    this.scanner = scanner;
  }

  public void execute() { // instance method가 되어야함. public을 생략하면 pakage 멤버가 됨.
    System.out.println("[게시글 관리 시스템]");
    System.out.println("보드 관리 시스템에 오신 걸 환영합니다.");
    printMainMenu();

    label:
    while (true) {
      System.out.print("게시글관리> ");
      String input = scanner.nextLine();

      switch (input) {
        case "1":
          System.out.println("회원 추가입니다.");
          break;
        case "2":
          System.out.println("회원 목록입니다.");
          break;
        case "3":
          System.out.println("회원 조회입니다.");
          break;
        case "4":
          System.out.println("회원 변경입니다.");
          break;
        case "5":
          System.out.println("회원 삭제입니다.");
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
    System.out.println("1. 회원 추가");
    System.out.println("2. 회원 목록");
    System.out.println("3. 회원 조회");
    System.out.println("4. 회원 변경");
    System.out.println("5. 회원 삭제");
    System.out.println("0. 메인");
  }
}
