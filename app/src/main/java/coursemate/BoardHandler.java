package coursemate;

public class BoardHandler implements MenuHandler {
  public void excute() { // instance method가 되어야함. public을 생략하면 pakage 멤버가 됨.
    System.out.println("[게시글 관리 시스템]");
    System.out.println("보드 관리 시스템에 오신 걸 환영합니다.");
  }
}
