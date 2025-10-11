package ws8a;

public class Rectangle implements Shape {
    private double width;
    private double heights;

    public Rectangle(double width, double heights){
        this.width = width;
        this.heights = heights;
    }

    @Override
    public double calculatePerimeter() {
        return (this.width + this.heights) * 2;
    }

    public double calculateArea(){
        return this.width * this.heights;
    }
}
