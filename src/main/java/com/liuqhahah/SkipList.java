package com.liuqhahah;

import java.util.Random;

public class SkipList {

  private Node head;
  private int maxLevel;
  private int level;
  private Random random;

  @Override
  public String toString() {
    return "SkipList{" +
        "head=" + head +
        ", maxLevel=" + maxLevel +
        ", level=" + level +
        ", random=" + random +
        '}';
  }

  public SkipList() {
    maxLevel = 16;
    level = 0;
    head = new Node(Integer.MIN_VALUE, maxLevel);
    random = new Random();
  }

  public void insert(int value) {
    Node[] update = new Node[maxLevel + 1];
    // find the right place to insert the node
    Node current = this.head;
    for (int i = this.level; i >= 0; i--) {
      // find the right place to insert the node
      while (current.forward[i] != null && current.forward[i].value < value) {
        current = current.forward[i];
      }
      // update the update array
      update[i] = current;
    }
    // move to the next node
    current = current.forward[0];
    // if the node is not in the list, insert it
    if (current == null || current.value != value) {
      // generate a random level for the node
      int lvl = randomLevel();
      // if the level is greater than the current level, update the update array
      if (lvl > level) {
        // update the update array
        for (int i = level + 1; i <= lvl; i++) {
          update[i] = head;
        }
        // update the level
        level = lvl;
      }

      // create a new node
      Node newNode = new Node(value, lvl);
      // insert the node into the list
      for (int i = 0; i <= lvl; i++) {
        // update the forward array of the new node
        newNode.forward[i] = update[i].forward[i];
        update[i].forward[i] = newNode;
      }
    }
  }

  public boolean search(int value) {
    Node current = this.head;
    for (int i = level; i >= 0; i--) {
      while (current.forward[i] != null && current.forward[i].value < value) {
        current = current.forward[i];
      }
    }
    current = current.forward[0];
    return current != null && current.value == value;
  }

  public void delete(int value) {
    Node[] update = new Node[maxLevel + 1];
    Node current = this.head;

    for (int i = level; i >= 0; i--) {
      while (current.forward[i] != null && current.forward[i].value < value) {
        current = current.forward[i];
      }
      update[i] = current;
    }
    current = current.forward[0];

    if (current != null && current.value == value) {
      for (int i = 0; i <= level; i++) {
        if (update[i].forward[i] != current) {
          break;
        }
        update[i].forward[i] = current.forward[i];
      }

      while (level > 0 && head.forward[level] == null) {
        level--;
      }
    }
  }

  /**
   * generate a random level for the node
   *
   * @return random level
   */
  private int randomLevel() {
    int lvl = 0;
    while (lvl < maxLevel && random.nextDouble() < 0.5) {
      lvl++;
    }
    return lvl;
  }

  public void printSkipList() {
    for (int i = level; i >= 0; i--) {
      Node node = head.forward[i];
      System.out.print("Level " + i + ": ");
      while (node != null) {
        System.out.print(node.value + " ");
        node = node.forward[i];
      }
      System.out.println();
    }
  }

}
