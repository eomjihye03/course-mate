package coursemate;

import java.util.Scanner;

public class App {

  public static void main(String[] args) throws Exception {
    System.out.println("강의 관리 시스템 '코스메이트'");

    MemberHandler memberHandler = new MemberHandler();
    BoardHandler boardHandler = new BoardHandler();

    printMainMenu();

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("메뉴> ");
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      } else if (input.equals("1")) {
        memberHandler.execute();
      } else if (input.equals("2")) {
        boardHandler.execute();
      } else {
        System.out.println("잘못된 메뉴입니다. 다시 선택하세요.");
        continue;
      }
    }

    scanner.close();
    System.out.println("프로그램을 종료합니다.");
  }

  static void printMainMenu() {
    System.out.println("메뉴:");
    System.out.println("  1. 회원");
    System.out.println("  2. 게시글");
    System.out.println("  0. 종료");
  }
}
