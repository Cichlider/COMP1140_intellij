public class Rectangle {
    int width;
    int height;
    int scale;

    Rectangle(int width, int height, int scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    public int getArea() {
        int area = width * height;
        return scaleArea(area);
    }

    public int scaleArea(int area) {
        return area / scale;
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle(1, 2, 1);
        System.out.println(r.getArea());
    }
}
