package com.liuqhahah;

public class Main {

  public static void main(String[] args) {

    SkipList skipList = new SkipList();
    skipList.insert(10);
    skipList.insert(20);
    skipList.insert(30);
    skipList.insert(40);
    skipList.insert(50);
    skipList.insert(60);
    skipList.insert(70);
    skipList.insert(80);
    skipList.insert(90);
    skipList.insert(100);
    skipList.insert(110);
    skipList.insert(120);

//    System.out.println("Skip list: " + skipList);

    skipList.printSkipList();
  }
}
