package com.jim.stone.util;

import java.util.ArrayList;
import java.util.List;

public class UtilArrayList {
    private List<String> list = new ArrayList<String>();

    public static void main(String[] args) {
	UtilArrayList utilArrayList = new UtilArrayList();
	utilArrayList.list.add("a");
	utilArrayList.list.add("b");
	utilArrayList.list.add("c");
	utilArrayList.list.add("d");
	utilArrayList.list.add("e");

	System.out.println(utilArrayList.list);

	System.out.println(utilArrayList.list.indexOf("f"));
	String s = utilArrayList.list.get(8);
	System.out.println(s);

	utilArrayList.list.remove(2);
	System.out.println(utilArrayList.list);
    }

}
