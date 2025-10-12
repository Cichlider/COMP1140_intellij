package ws9a;

public abstract class PaymentProcessor {
    public abstract void processPayment(double number);
}

//为什么这里不能用public class？
class PayPalProcessor extends PaymentProcessor{
    private String emailAddress;

    //构造器智能用public + 名字的形式吗？
    public PayPalProcessor(String emailAddress){
        this.emailAddress = emailAddress;
    }

    @Override
    public void processPayment(double number){
        System.out.println("Processing "+ number +" AUD via PayPal. User email: "+ this.emailAddress);
    }
}

class CreditCardProcessor extends PaymentProcessor{
    private String number;
    private String date;

    public CreditCardProcessor(String number, String date){
        this.number = number;
        this.date = date;
    }

    @Override
    public void processPayment(double amount){
        System.out.println("Processing " + amount + " AUD via Credit Card. Card number: "+ this.number);
    }

    public void processPayment(double amount, String transactionID){
        System.out.println("Processing " + amount + " AUD via Credit Card. Card number: "+ this.number +" Transaction ID: "+ transactionID);
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println("=== 步骤1: 创建实例（编译时类型为PaymentProcessor）===");
        PaymentProcessor paypal = new PayPalProcessor("user@example.com");
        PaymentProcessor creditCard = new CreditCardProcessor("1234-5678-9012-3456", "12/26");

        System.out.println("\n=== 步骤2: 调用基本processPayment方法 ===");
        paypal.processPayment(300.0);
        creditCard.processPayment(250.0);

        System.out.println("\n=== 步骤3: 尝试调用重载的processPayment(带交易ID) ===");

        // 问题：这样会编译错误！
        // creditCard.processPayment(25.0, "TXN12345");
        // 原因：creditCard的编译时类型是PaymentProcessor，
        // 而PaymentProcessor中没有这个重载方法

        System.out.println("使用类型转换解决:");
        // 解决方法：向下转型(downcasting)
        if (creditCard instanceof CreditCardProcessor) {
            CreditCardProcessor cc = (CreditCardProcessor) creditCard;
            cc.processPayment(25.0, "TXN12345");
        }

        System.out.println("\n=== 步骤4: 尝试对PayPal调用重载方法 ===");
        // 问题：即使转型也不行，因为PayPalProcessor根本没有这个方法
        // if (paypal instanceof PayPalProcessor) {
        //     PayPalProcessor pp = (PayPalProcessor) paypal;
        //     pp.processPayment(100.0, "TXN99999");  // 编译错误！
        // }
        System.out.println("无法调用，因为PayPalProcessor类中没有定义这个重载方法");

        System.out.println("\n=== 额外说明 ===");
        System.out.println("1. 方法重载在编译时决定（静态绑定）");
        System.out.println("2. 方法重写在运行时决定（动态绑定）");
        System.out.println("3. 编译时类型决定可以调用哪些方法");
        System.out.println("4. 运行时类型决定调用哪个具体实现");
    }
}