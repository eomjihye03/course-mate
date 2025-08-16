package coursemate;

public class LinkedList {
  private int size = 0;
  private Node head;
  private Node tail;

  void add(Board board) {
    Node node = new Node();
    node.value = board;

    if (head == null) {
      tail = head = node;
    } else {
      tail.next = node;
      node.prev = tail;
      tail = node;
    }
    size++;
  }

  Object[] list() {
    Object[] result = new Object[size];
    Node node = head;
    for (int i = 0; i < size; i++) {
      result[i] = node.value;
      node = node.next;
    }
    return result;
  }

  Object get(int i) {
    if (i < 0 || i >= size) {
      return null; // 인덱스가 범위를 벗어나면 null 반환
    }

    Node node = head; // 처음 노드부터 시작
    for (int x = 0; x < i; x++) {
      node = node.next; // i번째 노드로 이동
    }

    return node.value; // 해당 노드의 값을 반환
  }

  boolean set(int i, Object value) {
    if (i < 0 || i >= size) {
      return false; // 인덱스가 범위를 벗어나면 false 반환
    }
    Node node = head; // 처음 노드부터 시작
    for (int x = 0; x < i; x++) {
      node = node.next; // i번째 노드로 이동
    }
    node.value = value; // 해당 노드의 값을 변경

    return true; // 설정 성공
  }

  boolean remove(int i) {
    if (i < 0 || i >= size) {
      return false; // 인덱스가 범위를 벗어나면 false 반환
    }

    if (size == 1) {
      head = tail = null; // 마지막 노드 삭제 시 head와 tail을 null로 설정
    } else if (i == 0) {
      head = head.next; // 첫 번째 노드 삭제 시 head를 다음 노드로 이동
      head.prev = null; // 새로운 head의 prev를 null로 설정
    } else if (i == size - 1) {
      tail = tail.prev; // 마지막 노드 삭제 시 tail을 이전 노드로 이동
      tail.next = null; // 새로운 tail의 next를 null로 설정
    } else {
      Node node = head; // 처음 노드부터 시작
      for (int x = 0; x < i; x++) {
        node = node.next; // i번째 노드로 이동
      }
      node.prev.next = node.next; // 이전 노드의 next를 현재 노드의 next로 설정
      node.next.prev = node.prev; // 다음 노드의 prev를 현재 노드의 prev로 설정
    }
    size--;
    return true;
  }

  int size() {
    return size; // 현재 저장된 게시글 수 반환
  }

  private static class Node {
    public Object value;
    public Node next;
    public Node prev;
  }
}
