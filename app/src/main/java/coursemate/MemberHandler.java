package coursemate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MemberHandler implements MenuHandler {

  Scanner scanner;

  private static final int MAX_SIZE = 100;

  // Member 인스턴스 주소를 다을 레퍼런스 배열을 생성
  private final Member[] members = new Member[MAX_SIZE];
  private int size = 0;
  private int nextNo = 1;

  MemberHandler(Scanner scanner) {
    this.scanner = scanner;
  }

  public void execute() {
    printMainMenu();

    label:
    while (true) {
      System.out.print("회원관리> ");
      String input = scanner.nextLine();

      switch (input) {
        case "1":
          this.add();
          break;
        case "2":
          this.list();
          break;
        case "3":
          this.detail();
          break;
        case "4":
          this.update();
          break;
        case "5":
          this.delete();
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
    System.out.println("회원관리:");
    System.out.println("1. 회원 추가");
    System.out.println("2. 회원 목록");
    System.out.println("3. 회원 조회");
    System.out.println("4. 회원 변경");
    System.out.println("5. 회원 삭제");
    System.out.println("0. 메인");
  }

  private void add() {
    if (size >= MAX_SIZE) {
      System.out.println("더 이상 회원을 추가할 수 없습니다.");
      return;
    }

    Member member = new Member();

    System.out.print("이름? ");
    member.name = scanner.nextLine();

    System.out.print("이메일? ");
    member.email = scanner.nextLine();

    System.out.print("암호? ");
    member.password = scanner.nextLine();

    System.out.print("전화번호? ");
    member.phone = scanner.nextLine();

    member.no = nextNo++; // 1부터 시작하는 회원 번호 부여
    member.registeredDate = LocalDateTime.now(); // 가입일 저장

    members[size++] = member; // 배열에 회원 정보를 담고 있는 인스턴스 주소를 저장

    System.out.println("회원을 등록했습니다.");
  }

  private void list() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    System.out.println("번호\t이름\t전화번호\t\t등록일");
    for (int i = 0; i < size; i++) {
      Member m = members[i];
      System.out.printf(
          "%d\t%s\t%s\t%s\n", m.no, m.name, m.phone, m.registeredDate.format(formatter));
    }
  }

  private void detail() {
    System.out.print("조회할 회원 번호? ");
    int inputNo;
    try {
      inputNo = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("번호를 정확히 입력하세요.");
      return;
    }

    int index = -1;
    for (int i = 0; i < size; i++) {
      if (members[i].no == inputNo) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member member = members[index];

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    System.out.printf("번호: %d\n", member.no);
    System.out.printf("이름: %s\n", member.name);
    System.out.printf("이메일: %s\n", member.email);
    System.out.printf("전화번호: %s\n", member.phone);
    System.out.printf("등록일: %s\n", member.registeredDate.format(formatter));
  }

  private void update() {
    System.out.print("변경할 회원 번호? ");
    int inputNo;
    try {
      inputNo = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("번호를 정확히 입력하세요.");
      return;
    }

    int index = -1;
    for (int i = 0; i < size; i++) {
      if (members[i].no == inputNo) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member member = members[index];

    String tempName = member.name;
    String tempEmail = member.email;
    String tempPhone = member.phone;
    String tempPassword = member.password;

    System.out.printf("이름(%s)? ", tempName);
    String newName = scanner.nextLine();
    if (!newName.isEmpty()) {
      tempName = newName;
    }

    System.out.printf("이메일(%s)? ", tempEmail);
    String newEmail = scanner.nextLine();
    if (!newEmail.isEmpty()) {
      tempEmail = newEmail;
    }

    System.out.printf("전화번호(%s)? ", tempPhone);
    String newPhone = scanner.nextLine();
    if (!newPhone.isEmpty()) {
      tempPhone = newPhone;
    }

    System.out.print("암호(변경 시 입력)? ");
    String newPassword = scanner.nextLine();
    if (!newPassword.isEmpty()) {
      tempPassword = newPassword;
    }

    System.out.print("정말 변경하시겠습니까? (yes/no) ");
    String answer = scanner.nextLine();

    if (answer.equalsIgnoreCase("yes")) {
      member.name = tempName;
      member.email = tempEmail;
      member.phone = tempPhone;
      member.password = tempPassword;
      System.out.println("회원 정보를 변경했습니다.");
    } else {
      System.out.println("변경이 취소되었습니다.");
    }
  }

  private void delete() {
    System.out.print("삭제할 회원 번호? ");
    int inputNo;
    try {
      inputNo = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("번호를 정확히 입력하세요.");
      return;
    }

    int index = -1;
    for (int i = 0; i < size; i++) {
      if (members[i].no == inputNo) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    System.out.print("정말 삭제하시겠습니까? (yes/no) ");
    String answer = scanner.nextLine();

    if (answer.equalsIgnoreCase("yes")) {
      for (int i = index; i < size - 1; i++) {
        members[i] = members[i + 1];
      }
      size--;
      System.out.println("회원을 삭제했습니다.");
    } else {
      System.out.println("삭제가 취소되었습니다.");
    }
  }
}
