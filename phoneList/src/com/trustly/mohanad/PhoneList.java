package com.trustly.mohanad;

import java.io.IOException;
import java.util.Scanner;

public class PhoneList {
	private static Tree tree = new Tree();
	private static boolean isConsistent = true;
	
	public static class Tree {
		public boolean isCons = false;
		public Tree[] next = new Tree[10];

		public void setNext(int index, Tree t) {
			next[index] = t;
		}
	}

	public static void main(String[] args) {
		try {
			build();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void build() throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			int n = in.nextInt();
			tree = new Tree();
			for (int j = 0; j < n; j++) {
				String phone = in.next();
				if (isConsistent)
					buildTree(phone);
			}
			if (isConsistent) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

	}

	public static void buildTree(String s) {
		int len = s.length();
		Tree auxTree = tree;
		for (int i = 0; i < len; ++i) {
			int ch = Integer.parseInt(s.substring(i, i + 1));
			Tree aux = auxTree.next[ch];
			if (aux == null) {
				aux = new Tree();
				if (i == len - 1)
					aux.isCons = true;
				auxTree.setNext(ch, aux);
				auxTree = aux;
			} else {
				if (aux.isCons) {
					isConsistent = false;
					break;
				}
				if (i == len - 1) {
					isConsistent = false;
					break;
				}
				auxTree = aux;
			}
		}
	}

}