package javaz.util;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

class Product {
	private int pid;
	private String pname;
	private int price;
	
	public Product(int pid, String pname, int price) {
		this.pid = pid;
		this.pname = pname;
		this.price = price;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}

public class StreamProductMain {
	public static void main(String[] args) {
		List<Product> productList = new ArrayList<Product>();
		productList.add(new Product(1111, "Robot", 	   1000));
		productList.add(new Product(2222, "Spaceship", 2000));
		productList.add(new Product(3333, "UFO", 3000));
		
		List<Product> newList 
			= productList.stream()
						 .filter(p -> p.getPrice() >= 2000)
						 .collect(Collectors.toList());
		newList.forEach(p -> System.out.println(
									p.getPname() + " : " +
									p.getPrice()));					
		
		
		//5. 전체 제품의 가격을 total에 저장
		//6. 전체 제품 개수를 cnt에 저장
		//7. 전체 제품의 평균 가격을 avg에 저장
		//8. 5, 6, 7의 값 출력
		int total  = productList.stream()
							    .mapToInt(p -> p.getPrice())
						 	    .sum();
		long cnt   = productList.stream()
				   			    .mapToInt(Product::getPrice)
				   			    .count();
		int cntt   = (int) productList.stream()
				   			    .mapToInt(Product::getPrice)
				   			    .count();
		//---------------------------------------------------
		OptionalDouble avg = productList.stream()
	   			  				.mapToInt(Product::getPrice)
	   			  				.average();
		double avgg = productList.stream()
				  				.mapToInt(Product::getPrice)
				  				.average().getAsDouble();
	}
}




















