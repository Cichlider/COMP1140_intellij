# COMP1110/1140/6710 2025 S1 课程笔记翻译整理
## 核心面向对象编程（Core OO）

**日期：** 2025年9月23日

---

## 今日主题

核心面向对象编程包含：
- **子类型化（Subtyping）**
- **动态分派（Dynamic Dispatch）**
- **重写（Overriding）**

还包括：
- **继承（Inheritance）**
- **访问控制（Access Control）**

---

## 第2部分：真实软件的关注点

**日期：** 2025年9月23日

实际软件开发需要考虑：
- 在团队中工作，与其他程序员、其他团队协作
- 软件需要维护：修复bug、添加功能、适应库/环境的变化
- 更大规模的DRY原则 - 一旦编写，代码应该可以用于其他目的

**➔ 软件应该由小型组件构成，这些组件可以轻松地被替换和在其他地方重用。**

---

## 第4部分：什么是类型？

**日期：** 2025年9月23日

**类型是一组可能的值！**

### 示例：

**int类型包含：**
```
5
1337
-42
0
6710
-90001
…
```

**其他类型的值：**
```
"Hello"                    // String
Person("Frank", 55)        // Person对象
Role.ACADEMIC              // 枚举
true                       // boolean
OrangeBasket(3)            // 对象
[1, 2, 3]                  // 数组/列表
```

---

## 第5部分：记住：人类和机器

**日期：** 2025年9月23日

### Java的int类型：
- 任何介于 −2³¹ 和 2³¹ − 1 之间的整数
- 大约从 -20亿 到 +20亿

### 人类定义的类型：
```java
/** PosInt 是一个大于0的int */
```
**➔** 表示任何介于 1 和 2³¹ − 1 之间的整数，但这只是人类签名的一部分。**Java不会检查这部分。**

---

## 第6部分：函数类型

**日期：** 2025年9月23日

### Function<Integer, Integer> 包含：

```java
x -> x + 1 
x -> 5
x -> x > 5 ? x/5 : x
x -> x * x
x -> x
…
```

### 人类定义的MonoIntFun（单调整数函数）：

```java
/** 
 * MonoIntFun 是一个 Function<Integer,Integer>，
 * 其中，对于任何 MonoIntFun f
 * 和任何两个整数 x, y，如果
 * x >= y，那么 f(x) >= f(y)
 */
```

**MonoIntFun 包含：**
```java
x -> x + 1
x -> x * x
x -> x
…
```

---

## 第7部分：Liskov替换原则

**日期：** 2025年9月23日

### Barbara Liskov（图灵奖2008年获得者）

**Liskov替换原则：**

> 如果类型S的任何值可以在任何期望类型T的值的地方使用，并且它按照T的规范行为，那么类型S是类型T的子类型。

**➔ "行为子类型化（Behavioral Subtyping）"**

---

## 第8部分：行为子类型化示例

**日期：** 2025年9月23日

### 示例分析：

- **每个PosInt也是一个int**，因此PosInt是int的子类型。
- 类似地，**每个MonoIntFun也是一个Function<Integer,Integer>**…
- 在这两种情况下，**反过来都不成立**。

### 反例：

- **String值**（例如"Hello"）不能在期望int的地方使用
- 相反，你也不能写 `5.length()`，因此两者都不是对方的子类型。

---

## 第9部分：行为子类型化示例（图示）

**日期：** 2025年9月23日

### 子类型关系图：

```
PosInt ----是子类型----> int

MonoIntFun ----是子类型----> Function<Integer,Integer>

String <--没有子类型关系--> int
```

**然而，就Java类型而言：**
- `int = PosInt`（Java不区分）
- `MonoIntFun = Function<Integer,Integer>`（Java不区分）

---

## 第10-13部分：Java接口

**日期：** 2025年9月23日

### Java实现子类型化的主要机制

#### 我们之前见过的特殊情况：

```java
sealed interface Shape permits Rectangle, Circle {}
record Rectangle(double w, double h) implements Shape {}
record Circle(double radius) implements Shape {}
```

### 类型关系图：

```
Shape (接口)
  ├── Circle (子类型)
  │   ├── Circle(1.0)
  │   ├── Circle(5.3)
  │   ├── Circle(102.2)
  │   └── …
  └── Rectangle (子类型)
      ├── Rectangle(2.1, 4.3)
      ├── Rectangle(5.7, 0.4)
      ├── Rectangle(1.0, 1.2)
      └── …
```

**注意：** 这是一个"联合（union）"。另见HtDC（How to Design Classes）

### sealed接口的特殊功能：

**用于更函数式的行为：**
- 允许在switch中省略default情况
- 但依赖于知道所有情况

**面向对象哲学：**
- 允许随时用实现现有接口的新类扩展程序

---

### 非sealed接口：

```java
interface Shape {}
record Rectangle(double w, double h) implements Shape {}
record Circle(double radius) implements Shape {}
```

**现在可以有任意多的类/记录实现Shape**

**但是：**
```java
switch(shape) {
    case Rectangle(var w, var h) -> …;
    case Circle(var radius) -> …;
    default -> …; // 这里写什么???
}
```

**替代方案：** 现在我们使用大括号之间的空间！

---

## 第14部分：接口方法

**日期：** 2025年9月23日

```java
interface Shape {
    double getArea();
}
```

### 关键点：

- **接口可以规定任意多个方法**，实现它们的类必须提供这些方法。
- **接口本身通常没有这些方法的代码。**

---

## 第15部分：实现接口方法

**日期：** 2025年9月23日

```java
interface Shape {
    double getArea();
}

class Circle implements Shape {
    double radius;
    
    Circle(double radius) { 
        this.radius = radius; 
    }
    
    @Override
    double getArea() { 
        return radius * radius * Math.PI; 
    }
}
```

### 重写（Overriding）：

- **实现或替换（见下一部分）超类型中的方法称为"重写"。**
- Java使用 `@Override` 注解来检查是否确实有东西被重写。

---

## 第16部分：使用记录实现接口

**日期：** 2025年9月23日

```java
interface Shape {
    double getArea();
}

record Rectangle(double w, double h) implements Shape {
    @Override
    double getArea() { 
        return w * h; 
    }
}
```

### 专业提示：

如果你的字段永远不需要改变，你也可以使用记录，这样可以节省编写构造函数的工作。

---

## 第17部分：动态分派

**日期：** 2025年9月23日

### 我们如何知道调用哪个方法？

```java
double getShapeArea(Shape s) { 
    return s.getArea(); 
}

void main() {
    System.out.println(getShapeArea(new Rectangle(5, 3)));
    System.out.println(getShapeArea(new Circle(3.2)));
}
```

**记住：对象了解自己。**

因此每个单独的`s`都知道它是矩形还是圆形或…，并调用正确的`getArea()`方法。

---

## 第18部分：Java接口 vs. 类

**日期：** 2025年9月23日

### 特殊功能（优秀级别内容）：

- **接口可以有静态字段和方法**；对于字段，你可以省略static关键字
- **接口可以为实例方法提供"default"（默认）实现**
- **接口没有实例字段，也没有构造函数**
- **接口的主要目的是声明（而不是实现）方法**

---

## 第19部分：设计方法

**日期：** 2025年9月23日

### 东西应该放在哪里？

**对于每个方法，在接口中写一次。** 复制到实现接口和重写方法的类/记录中。

**设计策略** 添加到实际实现中，而不是接口。

**示例/测试** 应该放在最合适的地方：
- 可以与接口一起，为所有内容提供示例/测试
- 或者与单独的类一起
- **每个类至少应该有一个示例/测试**

---

## 第20部分：设计方法 - 关键点

**日期：** 2025年9月23日

### 关键点：Liskov替换原则

#### 参数类型/前置条件：

**子类型的参数类型/前置条件不能比超类型更严格。**

如果你的某个方法实现对输入数据/整体状态做出某些假设，你需要在接口中说明这一点。

#### 返回类型/后置条件：

**返回类型/后置条件必须至少与接口中的一样强。**

你不能在子类中放宽签名的任何要求 - 你需要在接口中这样做。

---

## 第21部分：继承（Inheritance）

**日期：** 2025年9月23日

### 代码重用

**但只有在存在行为子类型化的情况下才行，因为Java混淆了这两个概念。**

---

## 第22部分：有些事情变得重复

**日期：** 2025年9月23日

```java
interface User {
    String getName();
}

class Academic implements User {
    String name;
    Academic(String name) { … }
    @Override
    String getName() { return name; }
}

class Student implements User {
    String name;
    Student(String name) { … }
    @Override
    String getName() { return name; }
}

class ProfessionalStaff implements User {
    String name;
    ProfessionalStaff(String name) { … }
    @Override
    String getName() { return name; }
}
```

**问题：** 相同的代码重复了三次！

---

## 第23部分：使用类继承解决重复

**日期：** 2025年9月23日

```java
class User {
    String name;
    User(String name) { … }
    String getName() { 
        return name; 
    }
}

class Academic extends User {
    Academic(String name) { 
        super(name); 
    }
}

class Student extends User {
    Student(String name) { 
        super(name); 
    }
}

class ProfessionalStaff extends User {
    ProfessionalStaff(String name) { 
        super(name); 
    }
}
```

### 关键点：

- **现在是一个类**，这样我们可以有实例字段
- **扩展类的关键字是"extends"**
- **构造函数需要显式调用父构造函数**，使用特殊关键字"`super`"
- 除非存在无参数构造函数，否则必须调用super

---

## 第24部分：抽象类

**日期：** 2025年9月23日

### 当你不能在那里实现所有东西时

```java
abstract class User {
    String name;
    User(String name) { … }
    
    String getName() {
        return name; 
    }
    
    abstract Maybe<Date> nextSalaryIncrease();
}
```

### 关键点：

- **"abstract"方法** 的行为就像在接口中指定的一样
- **"abstract"类** 允许"abstract"方法存在于其中，但反过来**不允许直接构造该类的实例**
- 即，你不能写 `new User("John")`
- **需要使用非抽象的子类**

---

## 第25部分：扩展类

**日期：** 2025年9月23日

### 一个更大的示例

```java
class Student extends User {
    Date birthday;
    
    Student(String name, Date birthday) { 
        super(name);
        this.birthday = birthday;
    }
    
    @Override
    Maybe<Date> nextSalaryIncrease() { 
        return new Nothing<>(); 
    }
}
```

### 关键点：

- 可以添加新字段和构造函数参数
- 需要在调用父构造函数后初始化新字段
- 重写的工作方式与接口相同

---

## 第26部分：Object类 - 回顾

**日期：** 2025年9月23日

### 本课程将涵盖的内容：

```java
"hello".equals("world")
    ≈ Equals("hello", "world")

new HashMap<>().toString()
    ≈ ToString(new HashMap<>())

CurrentDate().getHashCode()
```

### 本课程可能不会涵盖的内容：

```java
"hello".getClass()
"hello".notify()
"hello".notifyAll()
"hello".wait()
```

**每个引用值最终都从Object类继承这些方法。大多数都可以被重写。**

---

## 第27部分：重写现有方法

**日期：** 2025年9月23日

```java
class User {
    String name;
    User(String name) { … }
    
    String getName() { return name; }
    
    @Override
    String toString() { 
        return name + " / " + super.toString(); 
    }
}
```

### 说明：

- **隐式扩展Object** - 如果不指定extends，默认扩展Object
- **重写Object的默认实现**
- **调用父类的默认实现**，在这种情况下是Object - 你不必这样做

---

## 第28部分：Java接口 vs. 类，第2部分

**日期：** 2025年9月23日

### 规则：

- **任何东西都可以扩展任意多个接口**
- **但只有类可以扩展类**，并且每个类**最多扩展一个类**（默认情况下：Object）

---

## 第29-33部分：访问控制（Access Control）

**日期：** 2025年9月23日

### 核心原则：

**为了保留你改变事物的能力，对其他人隐藏所有他们不明确需要的东西**

---

## 第30部分：超类型已经隐藏了东西

```java
class Student extends User {
    Date birthday;
    
    Student(String name, Date birthday) { 
        super(name);
        this.birthday = birthday;
    }
    
    @Override
    Maybe<Date> nextSalaryIncrease() { 
        return new Nothing<>(); 
    }
}
```

**由于"birthday"只在这里引入，任何将此对象视为"User"的人都不知道该字段的存在。**

---

## 第31部分：可见性属性

**日期：** 2025年9月23日

```java
public abstract class User {
    public final int id;
    protected String name;
    private static int counter = 0;
    
    protected User(String name) {
        this.name = name;
        this.id = counter++;
    }
    
    String getName() { return name; }
}
```

### 可见性修饰符：

| 修饰符 | 访问范围 |
|--------|----------|
| **public** | 可以从任何地方访问 |
| **protected** | 可以从子类访问 |
| **private** | 只能从类内部访问 |
| **[无修饰符]** | 可以从同一文件夹（包）内的类访问 |

### final修饰符：

- **不是严格意义上的可见性**
- 要求字段在构造函数中设置
- 之后不允许任何更改
- **这就是记录的工作方式**

---

## 第32部分：内部类

**日期：** 2025年9月23日

### 获取内部访问权限

```java
interface A { 
    String getString(); 
}

class B {
    private String str;
    …
    
    private class C implements A {
        String getString() { 
            return str; 
        }
    }
    
    A getA() { 
        return new C(); 
    }
}
```

### 关键点：

- **C是一个"内部类"**
- 它只能从B的实例方法内部创建
- 然后与该对象关联
- **因此它可以直接访问其字段**

---

## 第33部分：静态内部类

**日期：** 2025年9月23日

### 获取内部访问权限

```java
interface A { 
    String getString(); 
}

class B {
    private String str;
    …
    
    public static class D implements A {
        B b; 
        D(B b) { 
            this.b = b; 
        }
        
        String getString() { 
            return b.str; 
        }
    }
    
    A getA() { 
        return new D(this); 
    }
}
```

### 关键点：

- **D是一个"静态内部类"**
- 它可以从任何地方访问
- 只是逻辑上包含在B中
- **给定任何B，它仍然可以访问其私有字段**

---

## 课程总结

这节课深入讲解了Java面向对象编程的核心概念：

1. **子类型化与Liskov替换原则** - 行为兼容性
2. **接口与抽象类** - 代码组织与重用
3. **动态分派** - 运行时方法选择
4. **继承机制** - extends与implements
5. **访问控制** - public/protected/private
6. **内部类** - 封装与访问控制的高级技巧