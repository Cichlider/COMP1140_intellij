package ws10a10b;

import java.util.ArrayList;
import java.util.List;

// 简单的哈希表实现
class MyHashMap {
    // 内部节点类（链表法处理碰撞）
    private static class Node {
        char key;
        int value;
        Node next;

        Node(char key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] buckets;  // 桶数组
    private int size;        // 当前元素数量

    public MyHashMap(int capacity) {
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    // 🔑 核心：自定义哈希函数
    private int hash(char key) {
        // 方法1：简单取模（差的哈希函数）
        // return key % buckets.length;

        // 方法2：乘法哈希（好的哈希函数）
        int h = key;
        h = h * 31;  // 质数乘法
        h = h ^ (h >>> 16);  // 高位参与运算
        return Math.abs(h % buckets.length);
    }

    // 获取值（不存在返回0）
    public int get(char key) {
        int index = hash(key);
        Node curr = buckets[index];

        while (curr != null) {
            if (curr.key == key) {
                return curr.value;
            }
            curr = curr.next;
        }
        return 0;  // 不存在
    }

    // 设置值
    public void put(char key, int value) {
        int index = hash(key);
        Node curr = buckets[index];

        // 查找是否已存在
        while (curr != null) {
            if (curr.key == key) {
                curr.value = value;  // 更新
                return;
            }
            curr = curr.next;
        }

        // 不存在，插入到链表头部
        Node newNode = new Node(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }

    // 增加计数
    public void increment(char key) {
        put(key, get(key) + 1);
    }

    // 打印哈希表统计信息
    public void printStats() {
        System.out.println("=== 哈希表统计 ===");
        System.out.println("总元素数: " + size);

        int usedBuckets = 0;
        int maxChainLength = 0;

        for (int i = 0; i < buckets.length; i++) {
            int chainLength = 0;
            Node curr = buckets[i];

            while (curr != null) {
                chainLength++;
                curr = curr.next;
            }

            if (chainLength > 0) {
                usedBuckets++;
                maxChainLength = Math.max(maxChainLength, chainLength);
                System.out.println("桶[" + i + "]: " + chainLength + " 个元素");
            }
        }

        System.out.println("使用的桶: " + usedBuckets + "/" + buckets.length);
        System.out.println("最长链表: " + maxChainLength);
        System.out.println("平均链长: " + (size * 1.0 / usedBuckets));
    }

    // 获取所有键值对
    public List<Node> getAllEntries() {
        List<Node> entries = new ArrayList<>();
        for (Node bucket : buckets) {
            Node curr = bucket;
            while (curr != null) {
                entries.add(curr);
                curr = curr.next;
            }
        }
        return entries;
    }
}

public class MostFrequentChar {
    public static void main(String[] args) {
        // ========== 1. 创建哈希表 ==========
        MyHashMap map = new MyHashMap(26);  // 容量26（适合26个字母）

        // ========== 2. 添加数据 ==========
        map.put('a', 5);      // 存储：'a' → 5
        map.put('b', 10);     // 存储：'b' → 10
        map.put('c', 3);      // 存储：'c' → 3

        // ========== 3. 获取数据 ==========
        int count_a = map.get('a');  // 返回 5
        int count_z = map.get('z');  // 返回 0（不存在）

        System.out.println("'a' 出现: " + count_a + " 次");
        System.out.println("'z' 出现: " + count_z + " 次");

        // ========== 4. 更新数据 ==========
        map.put('a', 8);      // 更新：'a' → 8
        System.out.println("更新后 'a': " + map.get('a'));

        // ========== 5. 增加计数（常用） ==========
        map.increment('a');   // 'a': 8 → 9
        map.increment('a');   // 'a': 9 → 10
        System.out.println("增加后 'a': " + map.get('a'));

        // ========== 6. 查看统计信息 ==========
        map.printStats();
    }

}
