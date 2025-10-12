package ws9a;

public class Enemy extends GameEntity{
    private int threatLevel;

    public Enemy(int x ,int y ,int threatLevel){
        super(x,y);
        this.threatLevel = threatLevel;
    }

    @Override
    public void render(){
        System.out.println("Rendering Enemy at (" + x + "," + y + ") with threat level: " + threatLevel);
    }


}
