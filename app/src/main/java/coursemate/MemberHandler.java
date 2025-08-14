package coursemate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MemberHandler implements MenuHandler {

  Scanner scanner;

  private static final int MAX_SIZE = 100;

  private final int[] no = new int[MAX_SIZE];
  private final String[] name = new String[MAX_SIZE];
  private final String[] email = new String[MAX_SIZE];
  private final String[] phone = new String[MAX_SIZE];
  private final String[] password = new String[MAX_SIZE];
  private final LocalDateTime[] registeredDate = new LocalDateTime[MAX_SIZE];
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

    System.out.print("이름? ");
    name[size] = scanner.nextLine();

    System.out.print("이메일? ");
    email[size] = scanner.nextLine();

    System.out.print("암호? ");
    password[size] = scanner.nextLine();

    System.out.print("전화번호? ");
    phone[size] = scanner.nextLine();

    no[size] = nextNo++; // 1부터 시작하는 회원 번호 부여
    registeredDate[size] = LocalDateTime.now(); // 가입일 저장

    size++;
    System.out.println("회원을 등록했습니다.");
  }

  private void list() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    System.out.println("번호\t이름\t전화번호\t\t등록일");
    for (int i = 0; i < size; i++) {
      System.out.printf(
          "%d\t%s\t%s\t%s\n", no[i], name[i], phone[i], registeredDate[i].format(formatter));
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
      if (no[i] == inputNo) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    System.out.printf("번호: %d\n", no[index]);
    System.out.printf("이름: %s\n", name[index]);
    System.out.printf("이메일: %s\n", email[index]);
    System.out.printf("전화번호: %s\n", phone[index]);
    System.out.printf("등록일: %s\n", registeredDate[index].format(formatter));
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
      if (no[i] == inputNo) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    // 기존 값 복사
    String tempName = name[index];
    String tempEmail = email[index];
    String tempPhone = phone[index];
    String tempPassword = password[index];

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
      name[index] = tempName;
      email[index] = tempEmail;
      phone[index] = tempPhone;
      password[index] = tempPassword;
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
      if (no[i] == inputNo) {
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
        no[i] = no[i + 1];
        name[i] = name[i + 1];
        email[i] = email[i + 1];
        phone[i] = phone[i + 1];
        password[i] = password[i + 1];
        registeredDate[i] = registeredDate[i + 1];
      }
      size--;
      System.out.println("회원을 삭제했습니다.");
    } else {
      System.out.println("삭제가 취소되었습니다.");
    }
  }
}
