package inventory.item;

public class Armor extends Item{
    private int hp, mp, defense, evade;

    public Armor(String name, int hp, int mp, int defense, int evade, int price){
        super(name, price, 1);
        this.hp = hp;
        this.mp = mp;
        this.defense = defense;
        this.evade = evade;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getDefense() {
        return defense;
    }

    public int getEvade() {
        return evade;
    }
}
