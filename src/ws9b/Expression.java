package ws9b;

public abstract class Expression {
    public abstract int evaluate();
}

/**
 * The constant subClass of Expression.
 */
class Constant extends Expression{
    int value;

    Constant(int value){
        this.value = value;
    }

    @Override
    public int evaluate(){
        return value;
    }

    @Override
    public boolean equals(Object other){
        if(this == other) return true;
        if(other == null || getClass() != other.getClass()) return false;
        Constant constant = (Constant) other;
        return value == constant.value;
    }

    @Override
    public int hashCode(){
        return value;
    }
}

enum OperatorType{
    SUM,PRODUCT;

    public String toString(){
        switch(this){
            case SUM : return "+";
            case PRODUCT : return"*";
        }
        return "?";
    }
}

class Operator extends Expression{
    OperatorType type;
    Expression left;
    Expression right;

    Operator(OperatorType type, Expression left, Expression right){
        this.type = type;
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate(){
        int lv =left.evaluate();
        int rv = right.evaluate();
        switch(type){
            case SUM: return lv + rv;
            case PRODUCT: return lv*rv;
        }
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Operator operator = (Operator) other;
        return type == operator.type &&
                left.equals(operator.left) &&
                right.equals(operator.right);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}



