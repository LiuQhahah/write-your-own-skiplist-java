package com.liuqhahah;

import java.util.Arrays;

public class Node {

  int value;
  Node[] forward;

  public Node(int value, int level) {
    this.value = value;
    this.forward = new Node[level + 1];
  }

  @Override
  public String toString() {
    return "Node{" +
        "value=" + value +
        ", forward=" + Arrays.toString(forward) +
        '}';
  }
}
