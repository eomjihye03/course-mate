package coursemate;

class MemberLinkedList {

  private MemberNode head;
  private MemberNode tail;
  private int size = 0;

  void add(Member member) {
    MemberNode node = new MemberNode();
    node.value = member;

    if (head == null) {
      tail = head = node;
    } else {
      tail.next = node;
      node.prev = tail;
      tail = node;
    }

    size++;
  }

  Member[] list() {
    Member[] result = new Member[size];

    MemberNode node = head;
    for (int i = 0; i < size; i++) {
      result[i] = node.value;
      node = node.next;
    }

    return result;
  }

  Member get(int i) {
    if (i < 0 || i >= size) {
      return null; // 인덱스가 범위를 벗어나면 false 반환
    }

    // head 노드부터 출발해서 조회할 노드를 찾는다.
    MemberNode node = head; // 처음 노드의 인덱스는 0이다.
    for (int x = 1; x <= i; x++) {
      node = node.next;
    }
    return node.value;
  }

  boolean set(int i, Member member) {
    if (i < 0 || i >= size) {
      return false; // 인덱스가 범위를 벗어나면 false 반환
    }

    // head 노드부터 출발해서 값을 변경할 노드를 찾는다.
    MemberNode node = head; // 처음 노드의 인덱스는 0이다.
    for (int x = 1; x <= i; x++) {
      node = node.next;
    }

    node.value = member; // 해당 인덱스의 노드 값을 변경

    return true; // 설정 성공
  }

  boolean remove(int i) {
    if (i < 0 || i >= size) {
      return false; // 인덱스가 범위를 벗어나면 false 반환
    }

    if (size == 1) {
      head.value = null;
      head = tail = null;
      size = 0;
      return true;
    }

    // head 노드부터 출발해서 삭제할 노드를 찾는다.
    MemberNode node = head; // 처음 노드의 인덱스는 0이다.
    for (int x = 1; x <= i; x++) {
      node = node.next;
    }

    // 이전 노드가 삭제할 노드의 다음 노드를 가리킨다.
    if (node == head) {
      head = node.next;
      head.prev = null;
    } else {
      node.prev.next = node.next;
    }

    // 다음 노드가 삭제할 노드의 이전 노드를 가리킨다.
    if (node == tail) {
      tail = node.prev;
      tail.next = null;
    } else {
      node.next.prev = node.prev;
    }

    // 삭제할 노드의 prev와 next를 null로 설정
    node.next = node.prev = null;
    node.value = null;

    size--;
    return true; // 삭제 성공
  }

  int size() {
    return size; // 현재 저장된 회원 수 반환
  }
}
