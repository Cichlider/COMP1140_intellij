package ws9a;

public abstract class GameEntity {
    protected int x;
    protected int y;

    public GameEntity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract void render();

    // This is an example for overloading.
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Moved to coordinates (" + x + ", " + y + ")");
    }

    // 版本2：按方向移动指定距离
    public void move(Direction direction, int distance) {
        switch (direction) {
            case NORTH:
                this.y += distance;
                break;
            case SOUTH:
                this.y -= distance;
                break;
            case EAST:
                this.x += distance;
                break;
            case WEST:
                this.x -= distance;
                break;
            case NORTHEAST:
                this.x += distance;
                this.y += distance;
                break;
            case NORTHWEST:
                this.x -= distance;
                this.y += distance;
                break;
            case SOUTHEAST:
                this.x += distance;
                this.y -= distance;
                break;
            case SOUTHWEST:
                this.x -= distance;
                this.y -= distance;
                break;
        }
        System.out.println("Moved " + distance + " units " + direction);
    }

    // 版本3：移动到目标实体的位置
    public void move(GameEntity target) {
        this.x = target.x;
        this.y = target.y;
        System.out.println("Moved to target entity at (" + x + ", " + y + ")");
    }
}

