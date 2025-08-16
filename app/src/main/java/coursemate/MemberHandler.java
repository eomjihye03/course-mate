package coursemate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MemberHandler implements MenuHandler {

  Scanner scanner;

  private final LinkedList members = new LinkedList();
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

    members.add(member);

    System.out.println("회원을 등록했습니다.");
  }

  private void list() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    System.out.println("번호\t이름\t전화번호\t\t등록일");
    Object[] list = members.list();
    for (Object item : list) {
      Member m = (Member) item;
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

    Member member = null;
    for (int i = 0; i < members.size(); i++) {
      Member m = (Member) members.get(i);
      if (m.no == inputNo) {
        member = m;
        break;
      }
    }

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

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
    Member member = null;
    for (int i = 0; i < members.size(); i++) {
      Member m = (Member) members.get(i);
      if (m.no == inputNo) {
        index = i;
        member = m;
        break;
      }
    }

    if (index == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member updatedMember = new Member();
    updatedMember.no = member.no; // 기존 회원 번호 유지
    updatedMember.name = member.name;
    updatedMember.email = member.email;
    updatedMember.phone = member.phone;
    updatedMember.password = member.password;
    updatedMember.registeredDate = member.registeredDate; // 기존 가입일 유지

    System.out.printf("이름(%s)? ", updatedMember.name);
    String newName = scanner.nextLine();
    if (!newName.isEmpty()) {
      updatedMember.name = newName;
    }

    System.out.printf("이메일(%s)? ", updatedMember.email);
    String newEmail = scanner.nextLine();
    if (!newEmail.isEmpty()) {
      updatedMember.email = newEmail;
    }

    System.out.printf("전화번호(%s)? ", updatedMember.phone);
    String newPhone = scanner.nextLine();
    if (!newPhone.isEmpty()) {
      updatedMember.phone = newPhone;
    }

    System.out.print("암호(변경 시 입력)? ");
    String newPassword = scanner.nextLine();
    if (!newPassword.isEmpty()) {
      updatedMember.password = newPassword;
    }

    System.out.print("정말 변경하시겠습니까? (yes/no) ");
    String answer = scanner.nextLine();

    if (answer.equalsIgnoreCase("yes")) {
      members.set(index, updatedMember);
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
    for (int i = 0; i < members.size(); i++) {
      Member m = (Member) members.get(i);
      if (m.no == inputNo) {
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
      members.remove(index);
      System.out.println("회원을 삭제했습니다.");
    } else {
      System.out.println("삭제가 취소되었습니다.");
    }
  }
}
