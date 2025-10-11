package ws8a;

public class Bucket {
    private final double capacity;
    private double contents;

    public Bucket(double capacity){
        this.capacity = capacity;
        this.contents = 0.0;
    }

    public double getContents(){
        return this.contents;
    }

    public double getCapacity(){
        return this.capacity;
    }

    public double empty(){
        double pre = this.contents;
        this.contents = 0.0;
        return pre;
    }

    public void add(double amount){
        if(this.contents + amount >= this.capacity){
            this.contents = this.capacity;
        }else{
            this.contents += amount;
        }
    }


    public static void main(String[] args){
        Bucket big = new Bucket(10.0);
        Bucket small = new Bucket(10.0);

        big.add(20.0);
        small.add(20.0);

        System.out.println(big.getContents());
        System.out.println(small.getContents());

        big.empty();
        System.out.println(big.getContents());
        System.out.println(small.getContents());

        big.add(small.empty());

        System.out.println(big.getContents());
        System.out.println(small.getContents());
    }
}
