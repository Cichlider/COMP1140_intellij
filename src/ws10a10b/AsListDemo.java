package ws10a10b;

import java.util.*;

import static java.util.Arrays.asList;


public class AsListDemo {
    public static void main(String[] args) {

        // ===== 1. 基本用法：快速创建List =====

        System.out.println("===== 基本用法 =====");

        // 直接传入元素
        List<String> fruits = asList("苹果", "香蕉", "橙子");
        System.out.println("水果列表: " + fruits);

        // 传入数字
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        System.out.println("数字列表: " + numbers);

        // 从已有数组转换
        String[] colors = {"红色", "蓝色", "绿色"};
        List<String> colorList = asList(colors);
        System.out.println("颜色列表: " + colorList);


        // ===== 2. 遍历操作 =====

        System.out.println("\n===== 遍历操作 =====");

        List<String> cities = asList("北京", "上海", "广州", "深圳");

        // 使用for-each
        for (String city : cities) {
            System.out.println("城市: " + city);
        }

        // 使用索引访问
        System.out.println("第一个城市: " + cities.get(0));
        System.out.println("最后一个城市: " + cities.get(cities.size() - 1));


        // ===== 3. 查询操作 =====

        System.out.println("\n===== 查询操作 =====");

        List<String> animals = asList("猫", "狗", "兔子", "狗", "鸟");

        // 检查是否包含
        System.out.println("包含'狗'吗? " + animals.contains("狗"));
        System.out.println("包含'猪'吗? " + animals.contains("猪"));

        // 获取索引位置
        System.out.println("'狗'第一次出现位置: " + animals.indexOf("狗"));
        System.out.println("'狗'最后出现位置: " + animals.lastIndexOf("狗"));

        // 获取大小
        System.out.println("动物数量: " + animals.size());

        // 判断是否为空
        System.out.println("列表为空吗? " + animals.isEmpty());


        // ===== 4. ⚠️ 重要限制：不能修改大小 =====

        System.out.println("\n===== 修改限制演示 =====");

        List<String> langs = asList("Java", "Python", "C++");

        // ✅ 可以：修改已有元素
        langs.set(1, "JavaScript");
        System.out.println("修改后: " + langs);

        // ❌ 不可以：添加元素（会抛出异常）
        try {
            langs.add("Go");
        } catch (UnsupportedOperationException e) {
            System.out.println("❌ 错误：不能添加元素! " + e.getClass().getSimpleName());
        }

        // ❌ 不可以：删除元素（会抛出异常）
        try {
            langs.remove("Java");
        } catch (UnsupportedOperationException e) {
            System.out.println("❌ 错误：不能删除元素! " + e.getClass().getSimpleName());
        }


        // ===== 5. 解决方案：转换为可修改的List =====

        System.out.println("\n===== 转换为可修改List =====");

        // 方法1: 使用ArrayList构造器
        List<String> modifiableList1 = new ArrayList<>(asList("A", "B", "C"));
        modifiableList1.add("D");
        modifiableList1.remove("A");
        System.out.println("可修改List: " + modifiableList1);

        // 方法2: 使用Stream (Java 8+)
        List<String> modifiableList2 = new ArrayList<>();
        Collections.addAll(modifiableList2, "X", "Y", "Z");
        modifiableList2.add("W");
        System.out.println("另一个可修改List: " + modifiableList2);


        // ===== 6. 实用场景 =====

        System.out.println("\n===== 实用场景 =====");

        // 场景1: 作为方法参数
        printList(asList("元素1", "元素2", "元素3"));

        // 场景2: 快速初始化HashSet
        Set<String> uniqueNames = new HashSet<>(asList("张三", "李四", "张三", "王五"));
        System.out.println("去重后: " + uniqueNames);

        // 场景3: 数组和List的桥梁
        String[] array = {"One", "Two", "Three"};
        List<String> list = asList(array);

        // 注意：修改List会影响原数组！
        list.set(0, "壹");
        System.out.println("原数组也被修改: " + Arrays.toString(array));


        // ===== 7. 与其他方法对比 =====

        System.out.println("\n===== 对比其他方法 =====");

        // Arrays.asList() - 固定大小
        List<String> list1 = asList("A", "B", "C");
        System.out.println("asList创建: " + list1);

        // List.of() - Java 9+，完全不可变
        List<String> list2 = List.of("A", "B", "C");
        System.out.println("List.of创建: " + list2);

        // Collections.emptyList() - 空的不可变列表
        List<String> list3 = Collections.emptyList();
        System.out.println("空列表: " + list3);


        // ===== 8. 常见实战技巧 =====

        System.out.println("\n===== 实战技巧 =====");

        // 技巧1: 快速判断元素是否在集合中
        if (asList("admin", "root", "user").contains("admin")) {
            System.out.println("✓ 是管理员用户");
        }

        // 技巧2: 循环处理多个相同操作
        for (String file : asList("data.txt", "config.xml", "log.json")) {
            System.out.println("处理文件: " + file);
        }

        // 技巧3: 快速创建测试数据
        List<Integer> testScores = asList(85, 92, 78, 95, 88);
        double avg = testScores.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
        System.out.println("平均分: " + avg);


        // ===== 9. 注意事项总结 =====

        System.out.println("\n===== 注意事项 =====");
        System.out.println("✓ 可以做：遍历、查询、修改元素值");
        System.out.println("✗ 不能做：添加、删除元素");
        System.out.println("⚠️  修改List会影响原数组（如果从数组转换）");
        System.out.println("💡 需要可修改List？用 new ArrayList<>(asList(...))");
    }

    // 辅助方法：打印列表
    private static void printList(List<String> list) {
        System.out.println("打印列表: " + list);
    }
}