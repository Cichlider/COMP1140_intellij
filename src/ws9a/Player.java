package ws9a;

public class Player extends GameEntity{
    private int health;

    public Player(int x , int y, int health){
        super(x,y);
        this.health = health;
    }

    @Override
    public void render(){
        System.out.println("Rendering Player at (" + x + "," +") with health: " + health);
    }
}
