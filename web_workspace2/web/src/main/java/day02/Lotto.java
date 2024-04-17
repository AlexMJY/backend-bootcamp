package day02;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lotto {
	
	int[] rnd;
	int[] m;
	
	public Lotto() {
		rnd = new int[45];
		List<int[]> list = Arrays.asList(rnd);
		m = new int[6];
		
		init(); // 초기화
		shuffle(); // 1000번 섞기
		set();  // m에 6개 숫자 넣기
		sort();  // 정렬
		
	}

	public void init() {
		for (int i = 0; i < rnd.length; i++) {
			rnd[i] = i + 1;
		}
	}; 
	
	private void shuffle() {
		for (int i = 0; i <1000; i++) {
			int a = (int) (Math.random() * 45);
			int b = (int) (Math.random() * 45);
			int temp = 0;
			
//			System.out.println("rnd[" + rnd[a] + "]");
//			System.out.println("rnd[" + rnd[b] + "]");
			
			temp = rnd[a];
			rnd[a] = rnd[b];
			rnd[b] = temp;
		}
//		System.out.println(Arrays.toString(rnd));
		
	}
	
	private void set() {
		for (int i = 0; i < m.length; i++) {
			m[i] = rnd[i];
		}
		
	}
	
	
	private void sort() {
		Arrays.sort(m);
//		System.out.println(Arrays.toString(m));
		
	}
	
	
	
	public void print() {
//		System.out.println(Arrays.toString(m));
		for (int i = 0; i < m.length; i++) {
			System.out.print(m[i] + "\t");
		}
		System.out.println();
		
	}
	
	public int[] print2() {
		return m;
	}
}
