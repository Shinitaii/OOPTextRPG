package inventory.item;

public class Weapon extends Item {
    private int attack, accuracy, evade;

    public Weapon(String name, int attack, int accuracy, int evade, int price){
        super(name, price,1);
        this.attack = attack;
        this.accuracy = accuracy;
        this.evade = evade;
    }

    public int getAttack() {
        return attack;
    }

    public int getAccuracy() {
        return accuracy;
    }
    
    public int getEvade() {
        return evade;
    }

}
