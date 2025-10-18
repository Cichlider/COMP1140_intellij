package ws10a10b;
import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) {

        // ===== 1. 创建HashMap =====

        System.out.println("===== 创建HashMap =====");

        // 方式1: 无参构造器
        HashMap<String, Integer> scores = new HashMap<>();

        // 方式2: 指定初始容量
        HashMap<String, String> phoneBook = new HashMap<>(16);

        // 方式3: 从其他Map创建
        HashMap<String, Integer> copyMap = new HashMap<>(scores);

        System.out.println("HashMap创建成功!");


        // ===== 2. 添加元素（put） =====

        System.out.println("\n===== 添加元素 =====");

        // 添加学生成绩
        scores.put("张三", 85);
        scores.put("李四", 92);
        scores.put("王五", 78);
        scores.put("赵六", 95);

        System.out.println("成绩表: " + scores);

        // 重复键会覆盖旧值
        scores.put("张三", 90);  // 张三的成绩从85变成90
        System.out.println("更新后: " + scores);

        // putIfAbsent - 只在键不存在时添加
        scores.putIfAbsent("李四", 100);  // 不会覆盖，因为李四已存在
        scores.putIfAbsent("孙七", 88);   // 会添加，因为孙七不存在
        System.out.println("putIfAbsent后: " + scores);


        // ===== 3. 获取元素（get） =====

        System.out.println("\n===== 获取元素 =====");

        // 通过键获取值
        Integer score = scores.get("李四");
        System.out.println("李四的成绩: " + score);

        // 获取不存在的键返回null
        Integer notExist = scores.get("不存在的人");
        System.out.println("不存在的键: " + notExist);

        // getOrDefault - 键不存在时返回默认值
        Integer defaultScore = scores.getOrDefault("不存在", 0);
        System.out.println("使用默认值: " + defaultScore);


        // ===== 4. 检查操作 =====

        System.out.println("\n===== 检查操作 =====");

        // 检查是否包含键
        System.out.println("包含'张三'吗? " + scores.containsKey("张三"));
        System.out.println("包含'周八'吗? " + scores.containsKey("周八"));

        // 检查是否包含值
        System.out.println("有人得95分吗? " + scores.containsValue(95));
        System.out.println("有人得100分吗? " + scores.containsValue(100));

        // 检查是否为空
        System.out.println("成绩表为空吗? " + scores.isEmpty());

        // 获取大小
        System.out.println("学生人数: " + scores.size());


        // ===== 5. 删除元素 =====

        System.out.println("\n===== 删除元素 =====");

        // 通过键删除
        scores.remove("赵六");
        System.out.println("删除赵六后: " + scores);

        // 只有键值都匹配才删除
        scores.remove("李四", 100);  // 不会删除，因为李四是92分
        scores.remove("李四", 92);   // 会删除，键值都匹配
        System.out.println("条件删除后: " + scores);

        // 清空所有元素
        HashMap<String, Integer> temp = new HashMap<>(scores);
        temp.clear();
        System.out.println("清空后大小: " + temp.size());


        // ===== 6. 遍历HashMap =====

        System.out.println("\n===== 遍历HashMap =====");

        // 创建示例数据
        HashMap<String, String> capitals = new HashMap<>();
        capitals.put("中国", "北京");
        capitals.put("美国", "华盛顿");
        capitals.put("日本", "东京");
        capitals.put("法国", "巴黎");

        // 方式1: 遍历键值对（推荐）
        System.out.println("方式1 - entrySet:");
        for (Map.Entry<String, String> entry : capitals.entrySet()) {
            System.out.println(entry.getKey() + " 的首都是 " + entry.getValue());
        }

        // 方式2: 遍历键
        System.out.println("\n方式2 - keySet:");
        for (String country : capitals.keySet()) {
            System.out.println("国家: " + country);
        }

        // 方式3: 遍历值
        System.out.println("\n方式3 - values:");
        for (String capital : capitals.values()) {
            System.out.println("首都: " + capital);
        }

        // 方式4: forEach + Lambda (Java 8+)
        System.out.println("\n方式4 - forEach:");
        capitals.forEach((country, capital) ->
                System.out.println(country + " -> " + capital)
        );


        // ===== 7. 高级操作 =====

        System.out.println("\n===== 高级操作 =====");

        HashMap<String, Integer> inventory = new HashMap<>();
        inventory.put("苹果", 10);
        inventory.put("香蕉", 5);
        inventory.put("橙子", 8);

        // compute - 计算新值
        inventory.compute("苹果", (key, value) -> value + 5);  // 苹果+5
        System.out.println("增加后: " + inventory);

        // computeIfAbsent - 键不存在时计算
        inventory.computeIfAbsent("葡萄", key -> 3);
        System.out.println("添加新水果: " + inventory);

        // merge - 合并值
        inventory.merge("苹果", 2, (oldVal, newVal) -> oldVal + newVal);  // 苹果再+2
        System.out.println("合并后: " + inventory);

        // replace - 替换值
        inventory.replace("香蕉", 5, 10);  // 只有值为5时才替换
        System.out.println("替换后: " + inventory);


        // ===== 8. 实际应用场景 =====

        System.out.println("\n===== 实际应用场景 =====");

        // 场景1: 统计字符出现次数
        String text = "hello world";
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (c != ' ') {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
        }
        System.out.println("字符统计: " + charCount);

        // 场景2: 缓存系统（模拟）
        HashMap<String, String> cache = new HashMap<>();
        cache.put("user:123", "张三的信息");
        cache.put("user:456", "李四的信息");
        String userInfo = cache.getOrDefault("user:123", "未找到用户");
        System.out.println("缓存查询: " + userInfo);

        // 场景3: 配置管理
        HashMap<String, String> config = new HashMap<>();
        config.put("database.host", "localhost");
        config.put("database.port", "3306");
        config.put("database.name", "mydb");
        System.out.println("配置项: " + config);

        // 场景4: 分组统计
        HashMap<String, List<String>> groups = new HashMap<>();
        groups.put("水果", Arrays.asList("苹果", "香蕉", "橙子"));
        groups.put("蔬菜", Arrays.asList("白菜", "萝卜", "西红柿"));
        System.out.println("分组数据: " + groups);


        // ===== 9. 注意事项 =====

        System.out.println("\n===== 注意事项 =====");

        // 注意1: null键和null值
        HashMap<String, String> nullTest = new HashMap<>();
        nullTest.put(null, "null键的值");  // 允许一个null键
        nullTest.put("key1", null);        // 允许多个null值
        nullTest.put("key2", null);
        System.out.println("null测试: " + nullTest);

        // 注意2: 键必须是不可变对象（推荐用String、Integer等）
        // 不推荐用可变对象作为键

        // 注意3: 线程不安全
        System.out.println("⚠️  HashMap不是线程安全的");
        System.out.println("多线程环境请使用 ConcurrentHashMap");


        // ===== 10. 与其他Map对比 =====

        System.out.println("\n===== Map家族对比 =====");
        System.out.println("HashMap:    无序，允许null键，最常用");
        System.out.println("LinkedHashMap: 保持插入顺序");
        System.out.println("TreeMap:    有序（自然排序），不允许null键");
        System.out.println("Hashtable:  线程安全，但已过时");
        System.out.println("ConcurrentHashMap: 线程安全，推荐使用");


        // ===== 练习题 =====

        System.out.println("\n===== 练习题 =====");
        System.out.println("1. 创建HashMap存储商品和价格，计算总价");
        System.out.println("2. 统计一个句子中每个单词出现的次数");
        System.out.println("3. 用HashMap实现一个简单的电话簿");
        System.out.println("4. 找出HashMap中值最大的键");
    }
}
