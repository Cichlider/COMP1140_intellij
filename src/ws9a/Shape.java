package ws9a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Shape {
    public abstract double area();
    public void display(){
        System.out.println("This is a Shape");
    }
}

class Circle extends Shape{
    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    @Override
    public double area(){
        return (Math.PI * (radius * radius));
    }
}

class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle (double width,double height){
        this.height = height;
        this.width = width;
    }

    @Override
    public double area(){
        return height * width;
    }
}

class ShapeContainer<T extends Shape>{
    private List<T> list = new ArrayList<>();

    public void addShape(T shape){
        list.add(shape);
    }

    public double getTotalArea(){
        double sum = 0;
        for (int i = 0 ; i < list.size() ; i++){
            sum += list.get(i).area();
        }
        return sum;
    }

}


class Main_shape{
    public static void main(String[] args){
        // 创建3个Circle实例
        Circle circle1 = new Circle(5.0);
        Circle circle2 = new Circle(3.0);
        Circle circle3 = new Circle(7.0);

        // 创建3个Rectangle实例
        Rectangle rect1 = new Rectangle(4.0, 5.0);
        Rectangle rect2 = new Rectangle(3.0, 6.0);
        Rectangle rect3 = new Rectangle(2.0, 8.0);

        ShapeContainer<Circle> circleContainer = new ShapeContainer<>();
        ShapeContainer<Rectangle> rectangleContainer = new ShapeContainer<>();

        circleContainer.addShape(circle1);
        circleContainer.addShape(circle2);
        circleContainer.addShape(circle3);

        rectangleContainer.addShape(rect1);
        rectangleContainer.addShape(rect2);
        rectangleContainer.addShape(rect3);

        System.out.println(circleContainer.getTotalArea());
        System.out.println(rectangleContainer.getTotalArea());

    }
}