# Q5 文件系统实现讲解

## 题目概述

这道题要求实现一个文件系统节点的抽象表示，能够表示文件系统中的目录、文本文件和非文本文件。重点是要理解面向对象的设计模式和深度优先搜索的实现。

## 核心设计思想

### 1. 策略模式 (Strategy Pattern)
题目使用了策略模式来处理不同类型的文件系统节点：
- `FsNode` 接口定义了所有节点的通用行为
- `FsDir`、`FsTextFile`、`FsNonTextFile` 分别实现不同类型节点的具体行为

### 2. 工厂方法 (Factory Method)
`FsNode.createNode(File file)` 静态方法是一个工厂方法，根据文件类型创建相应的节点对象：
```java
static FsNode createNode(File file) {
    if (file.isDirectory()) {
        return new FsDir(file);
    } else if (file.getName().endsWith(".txt")) {
        return new FsTextFile(file);
    } else {
        return new FsNonTextFile(file);
    }
}
```

## 关键实现要点

### 1. 避免使用 listFiles() 方法
题目要求不能使用 `File.listFiles()` 方法。我们的解决方案是使用 Java NIO 的 `Files.list()`：

```java
// 在 FsDir 构造函数中
List<Path> paths = Files.list(dir.toPath())
    .sorted((p1, p2) -> p1.getFileName().toString().compareTo(p2.getFileName().toString()))
    .collect(Collectors.toList());
```

这样既满足了题目要求，又保证了文件的字母顺序排列。

### 2. Pre-order DFS 遍历实现
`allNodes()` 方法实现了前序深度优先搜索：

**FsDir 中的实现：**
```java
public List<FsNode> allNodes() {
    List<FsNode> result = new ArrayList<>();
    result.add(this); // 先访问当前节点（前序）
    
    // 然后递归访问所有子节点
    for (FsNode child : children) {
        result.addAll(child.allNodes());
    }
    
    return result;
}
```

**文件节点中的实现：**
```java
public List<FsNode> allNodes() {
    List<FsNode> result = new ArrayList<>();
    result.add(this); // 文件节点只包含自己
    return result;
}
```

### 3. 相等性 (Equality) 的实现
题目对不同类型节点的相等性有不同要求：

**目录节点：** 名称相等且所有子节点都相等
```java
// 检查名称
if (!directory.getName().equals(other.directory.getName())) {
    return false;
}
// 检查所有子节点
for (int i = 0; i < children.size(); i++) {
    if (!children.get(i).equals(other.children.get(i))) {
        return false;
    }
}
```

**文本文件：** 名称相等且内容（所有行）都相等
```java
// 检查名称
if (!file.getName().equals(other.file.getName())) {
    return false;
}
// 检查内容
return lines.equals(other.lines);
```

**非文本文件：** 仅名称相等即可
```java
return file.getName().equals(other.file.getName());
```

## 类结构说明

### FsNode (接口)
- 定义了所有文件系统节点的通用接口
- 包含工厂方法 `createNode()`
- 声明了 `getUnderlyingFile()` 和 `allNodes()` 方法

### FsDir (目录节点)
- 存储子节点列表 `children`
- 构造时使用 `Files.list()` 读取目录内容
- 实现 pre-order DFS 遍历

### FsTextFile (文本文件节点)  
- 存储文件的所有行内容 `lines`
- 构造时使用 `BufferedReader` 读取文件内容
- 相等性基于名称和内容

### FsNonTextFile (非文本文件节点)
- 最简单的实现，只存储文件引用
- 相等性仅基于文件名

## 测试用例理解

题目给出的示例：
```
testFiles/A → 期望返回：
["testFiles/A", "testFiles/A/B", "testFiles/A/B/C.txt", 
 "testFiles/A/C.txt", "testFiles/A/D.doc"]
```

这展示了前序DFS的访问顺序：
1. 访问 A 目录
2. 访问 A 的第一个子节点 B 目录
3. 访问 B 的子节点 C.txt
4. 回到 A，访问 A 的其他子节点 C.txt 和 D.doc

## 关键技术点总结

1. **面向对象设计**：接口、实现类、多态的运用
2. **文件I/O操作**：使用 NIO API 和传统 I/O
3. **递归算法**：DFS 的递归实现
4. **数据结构**：树形结构的遍历
5. **异常处理**：文件读取的异常处理
6. **相等性契约**：正确实现 equals() 和 hashCode()

这道题很好地综合了Java基础、面向对象设计、算法和数据结构等多个知识点。 