package ws8a;

public class Shapes {

    public static double[] computeAreaAndPerimeter(Shape shape){
        double[] result = new double[2];
        result[0] = shape.calculateArea();
        result[1] = shape.calculatePerimeter();
        return result;
    }

    public static void main(String[] args){
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0,6.0);
        Shape rightTriangle = new Triangle(3.0,4.0,5.0);

        double[] circleResults = computeAreaAndPerimeter(circle);
        System.out.println("Circle:");
        System.out.println("  Area: " + circleResults[0]);
        System.out.println("  Perimeter: " + circleResults[1]);
        System.out.println();

        // 计算并打印矩形的面积和周长
        double[] rectangleResults = computeAreaAndPerimeter(rectangle);
        System.out.println("Rectangle:");
        System.out.println("  Area: " + rectangleResults[0]);
        System.out.println("  Perimeter: " + rectangleResults[1]);
        System.out.println();

        // 计算并打印直角三角形的面积和周长
        double[] triangleResults = computeAreaAndPerimeter(rightTriangle);
        System.out.println("Right Triangle:");
        System.out.println("  Area: " + triangleResults[0]);
        System.out.println("  Perimeter: " + triangleResults[1]);
    }
}
