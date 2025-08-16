package coursemate;

class MemberList {
  private static final int MAX_SIZE = 100;

  private Member[] members = new Member[MAX_SIZE];
  private int size = 0;

  void add(Member member) {
    if (size >= members.length) {
      Member[] newMembers = new Member[members.length + (members.length >> 1)];
      System.arraycopy(members, 0, newMembers, 0, members.length);
      members = newMembers;
    }
    members[size++] = member;
  }

  Member[] list() {
    Member[] result = new Member[size];
    System.arraycopy(members, 0, result, 0, size);
    return result;
  }

  Member get(int i) {
    if (i < 0 || i >= size) {
      return null; // 인덱스가 범위를 벗어나면 null 반환
    }
    return members[i];
  }

  boolean set(int i, Member member) {
    if (i < 0 || i >= size) {
      return false; // 인덱스가 범위를 벗어나면 false 반환
    }
    members[i] = member;
    return true; // 설정 성공
  }

  boolean update(int i, Member member) {
    if (i < 0 || i >= size) {
      return false; // 인덱스가 범위를 벗어나면 false 반환
    }
    members[i] = member;
    return true; // 업데이트 성공
  }

  boolean remove(int i) {
    if (i < 0 || i >= size) {
      return false; // 인덱스가 범위를 벗어나면 false 반환
    }
    for (int j = i; j < size - 1; j++) {
      members[j] = members[j + 1];
    }
    members[--size] = null; // 마지막 요소는 null로 설정
    return true; // 삭제 성공
  }

  int size() {
    return size; // 현재 저장된 회원 수 반환
  }
}
