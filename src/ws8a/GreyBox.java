package ws8a;

public class GreyBox implements Box{
    private int width;
    private int height;
    private char frame;

    GreyBox(int width, int height, char frame){
        this.width = width;
        this.height = height;
        this.frame = frame;
    }

    public void print(){
        for (int i = 0; i< height ; i++){
            for (int j = 0; j< width ;j++){
                if(i==0 || i ==height-1 || j ==0 || j ==width-1){
                    System.out.print(frame);
                }else {
                    System.out.print('@');
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

    public static void main(String[] args){
        GreyBox a = new GreyBox(3,4,'#');

        a.print();
    }
}
