package inventory.item.armor;

import inventory.item.Item;

public class Armor extends Item{
    private int partID, hp, mp, defense, evade;
    public Armor(String name, int partID, int hp, int mp, int defense, int evade, int price){
        super(name, price, 1);
        this.partID = partID;
        this.hp = hp;
        this.mp = mp;
        this.defense = defense;
        this.evade = evade;
    }

    public int getPartID() {
        return partID;
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
