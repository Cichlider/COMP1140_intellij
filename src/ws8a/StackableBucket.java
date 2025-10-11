package ws8a;

import java.util.Comparator;
import java.util.Stack;
import java.util.Arrays;

public class StackableBucket extends Bucket implements Comparable<StackableBucket> {
    private String name;
    private StackableBucket innerBucket;

    StackableBucket(double capacity, String name){
        super(capacity);
        this.name = name;
        this.innerBucket = null;
    }

    public String getInnerBucketName(){
        if(this.innerBucket == null){
            return null;
        }
        return this.innerBucket.name;
    }

    public void setInnerBucket(StackableBucket smallerBucket){
        if(smallerBucket.getCapacity() >= this.getCapacity()){
            System.out.println("Too large to stack!");
            return;
        }else{
            if(this.getInnerBucketName() == null){
                this.innerBucket = smallerBucket;
            }else{
                this.innerBucket.setInnerBucket(smallerBucket);
            }
        }
    }

    public void unstackBuckets(){
        if (this.getInnerBucketName() == null){
            return;
        }else{
            this.innerBucket.unstackBuckets();
            this.innerBucket = null;
        }
    }

    public int compareTo(StackableBucket otherBucket){
        return Double.compare(this.getCapacity(), otherBucket.getCapacity());
    }

    public String toString(){
        return name +"(capacity" + getCapacity() + ")";
    }

    public static void main(String[] args){
        StackableBucket[] buckets = new StackableBucket[6];
        buckets[0] = new StackableBucket(10.0, "Large");
        buckets[1] = new StackableBucket(3.5, "Small");
        buckets[2] = new StackableBucket(7.2, "Medium");
        buckets[3] = new StackableBucket(1.0, "Tiny");
        buckets[4] = new StackableBucket(15.0, "Huge");
        buckets[5] = new StackableBucket(5.0, "Regular");

        System.out.println("\n排序前的桶：");
        for (StackableBucket bucket : buckets) {
            System.out.println("  " + bucket);
        }

        Arrays.sort(buckets);

        System.out.println("\n排序后的桶（按容量从小到大）：");
        for (StackableBucket bucket : buckets) {
            System.out.println("  " + bucket);
        }
    }
}
