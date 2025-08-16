package coursemate;

public class BoardList {
  private static int size = 0;
  static int MAX_SIZE = 100;
  private Board[] boards = new Board[MAX_SIZE];

  void add(Board board) {
    if (size >= boards.length) {
      Board[] newBoards = new Board[boards.length + (boards.length >> 1)];
      System.arraycopy(boards, 0, newBoards, 0, boards.length);
      boards = newBoards;
    }
    boards[size++] = board;
  }

  Board[] list() {
    if (size == 0) {
      System.out.println("등록된 게시글이 없습니다.");
      return null;
    }

    Board[] result = new Board[size];
    System.arraycopy(boards, 0, result, 0, size);
    return result;
  }

  Board get(int i) {
    if (i < 0 || i >= size) {
      return null; // 인덱스가 범위를 벗어나면 null 반환
    }
    return boards[i];
  }

  boolean set(int i, Board board) {
    if (i < 0 || i >= size) {
      return false; // 인덱스가 범위를 벗어나면 false 반환
    }
    boards[i] = board;
    return true; // 설정 성공
  }

  boolean remove(int i) {
    if (i < 0 || i >= size) {
      return false; // 인덱스가 범위를 벗어나면 false 반환
    }
    for (int j = i; j < size - 1; j++) {
      boards[j] = boards[j + 1];
    }
    boards[--size] = null; // 마지막 요소는 null로 설정
    return true; // 삭제 성공
  }

  int size() {
    return size; // 현재 저장된 게시글 수 반환
  }
}
