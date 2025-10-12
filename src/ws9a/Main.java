package ws9a;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 步骤1: 创建实例并调用render ===");
        Player player = new Player(100, 200, 98);
        Enemy enemy = new Enemy(-50, 38, 5);

        player.render();
        enemy.render();

        System.out.println("\n=== 步骤2: 使用List存储并遍历 ===");
        // 创建List<GameEntity>并添加实例
        // 可以添加是因为Player和Enemy都是GameEntity的子类（多态）
        java.util.List<GameEntity> entities = new java.util.ArrayList<>();
        entities.add(player);
        entities.add(enemy);

        // 使用增强for循环遍历
        for (GameEntity entity : entities) {
            // entity的静态类型(编译时类型)是 GameEntity
            // entity的动态类型(运行时类型)在第一次迭代是 Player，第二次是 Enemy
            // 调用的render方法由动态类型决定（动态绑定）
            entity.render();
        }

        System.out.println("\n=== 步骤3: 测试三个move方法重载版本 ===");
        System.out.println("Player move测试:");
        player.move(150, 250);  // 版本1
        player.move(Direction.NORTH, 50);  // 版本2
        player.move(enemy);  // 版本3

        System.out.println("\nEnemy move测试:");
        enemy.move(0, 0);  // 版本1
        enemy.move(Direction.SOUTHEAST, 30);  // 版本2
        enemy.move(player);  // 版本3

        System.out.println("\n=== 步骤4: 通过GameEntity类型变量调用move ===");
        // 可以！因为move方法在GameEntity中定义
        for (GameEntity entity : entities) {
            entity.move(Direction.WEST, 10);  // 完全可以调用
        }
    }
}