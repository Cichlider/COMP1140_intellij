package ws8a;

public class ColouredBox implements Box{
    private int width;
    private int height;
    private char frame;
    private char fill;

    ColouredBox(int width, int height, char frame, char fill){
        this.width = width;
        this.height = height;
        this.frame = frame;
        this.fill = fill;
    }

    public void print(){
        for (int i = 0; i< height ; i++){
            for (int j = 0; j< width ;j++){
                if(i==0 || i ==height-1 || j ==0 || j ==width-1){
                    System.out.print(frame);
                }else {
                    System.out.print(fill);
                }
            }
            System.out.print("\n");
        }
    }

    public void setFrameChar(char frame){
        this.frame = frame;
    }

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void setFillChar(char fill){
        this.fill = fill;
    }

    public static void main(String[] args){
        ColouredBox a = new ColouredBox(3,4,'#','@');

        a.print();
    }
}
