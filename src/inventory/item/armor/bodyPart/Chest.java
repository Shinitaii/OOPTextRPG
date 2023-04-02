package inventory.item.armor.bodyPart;
import inventory.item.armor.Armor;
public class Chest extends Armor{
    public Chest(String name, int hp, int mp, int defense, int evade, int price) {
        super(name, 1, hp, mp, defense, evade, price);
    }
}
