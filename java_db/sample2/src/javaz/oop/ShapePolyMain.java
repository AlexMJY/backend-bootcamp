package javaz.oop;

class Shape {
	public void draw() {
		System.out.println("형태를 그립니다.");
	}
}

class Circle extends Shape {
	@Override
	public void draw() {
		System.out.println("원을 그립니다.");
	}
	
	public void area() {
		System.out.println("원의 넓이 : 반지름 * 반지름 * 3.14");
	}
}

class Rectangle extends Shape {
	@Override
	public void draw() {
		System.out.println("사각형을 그립니다.");
	}

	public void area() {
		System.out.println("사각형의 넓이 : 가로 * 가로");
	}
}

public class ShapePolyMain {
	public static void main(String[] args) {
		Shape shape = new Shape();
		shape.draw();
		
		shape = new Circle();
		
		shape.draw();
//		shape.area();	//상속시켜 준 메서드가 아니므로 사용 불가
		
		shape = new Rectangle(); 
		System.out.println(shape instanceof Shape);
		System.out.println(shape instanceof Object);
		System.out.println(shape instanceof Circle);
		System.out.println(shape instanceof Rectangle);
		
		shape.draw();	 
	}//END main()
}//END class









