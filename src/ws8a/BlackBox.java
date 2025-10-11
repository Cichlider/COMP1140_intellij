package ws8a;

public class BlackBox implements Box{
    private int width;
    private int height;
    private char frame;

    public BlackBox(int width, int height, char frame) {
        this.width = width;
        this.height = height;
        this.frame = frame;
    }

    @Override
    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(frame);
            }
            System.out.print("\n");
        }
    }

    @Override
    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public void setFrameChar(char frame){
        this.frame = frame;
    }

    public static void main(String[] args){
        BlackBox a = new BlackBox(3,4,'#');

        a.print();
    }

}
