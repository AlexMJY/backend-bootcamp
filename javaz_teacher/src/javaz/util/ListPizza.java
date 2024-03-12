package javaz.util;

import java.util.ArrayList;
import java.util.List;

class Pizza {
	private String name;	//Shrimp,Potato, Chicago
	private String size;	//small, medium, large
	private int price;		//10000, 20000,  30000
	
	public Pizza(String name, String size, int price) {
		this.name = name;
		this.size = size;
		this.price = price;
	}
	
	//2.멤버 변수들의 setter / getter 메서드
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSize() {
		return size;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}
}

public class ListPizza {
	public static void main(String[] args) {
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza("Potato",  "small", 10000));
		pizzas.add(new Pizza("Chicago", "small", 10000));
		pizzas.add(new Pizza("Shrimp",  "large", 30000));
		
		////////////////////////////////////////////////////
		
		System.out.println("--- Pizza order list ---");
		System.out.println("no.\tname\tsize\tprice");
		 
		int i=1;
		int sum = 0;
		for (Pizza pizza : pizzas) {
			System.out.println(i++ + "\t" + pizza.getName() + 
							   "\t" + pizza.getSize() + 
							   "\t" + pizza.getPrice());
			sum += pizza.getPrice();
		}
		
		System.out.println("----------------------------------");
		System.out.println("전체 주문 수량 : " + pizzas.size());
		System.out.println("총 주문 금액 : " + sum);
	}
}













