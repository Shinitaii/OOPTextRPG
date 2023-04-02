package inventory.item;

public class Potion extends Item{
    private int healthIncrease, manaIncrease, attackIncrease, defenseIncrease, accuracyIncrease, evadeIncrease;
    
    public Potion(String name, int quantity, int healthIncrease, int manaIncrease, int attackIncrease, int defenseIncrease, int accuracyIncrease, int evadeIncrease, int price){
        super(name, quantity, price);
        this.healthIncrease = healthIncrease;
        this.manaIncrease = manaIncrease;
        this.defenseIncrease = defenseIncrease;
        this.accuracyIncrease = accuracyIncrease;
        this.evadeIncrease = evadeIncrease;
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }

    public void setHealthIncrease(int healthIncrease) {
        this.healthIncrease = healthIncrease;
    }

    public int getManaIncrease() {
        return manaIncrease;
    }

    public void setManaIncrease(int manaIncrease) {
        this.manaIncrease = manaIncrease;
    }

    public int getAttackIncrease() {
        return attackIncrease;
    }

    public void setAttackIncrease(int attackIncrease) {
        this.attackIncrease = attackIncrease;
    }

    public int getDefenseIncrease() {
        return defenseIncrease;
    }

    public void setDefenseIncrease(int defenseIncrease) {
        this.defenseIncrease = defenseIncrease;
    }

    public int getAccuracyIncrease() {
        return accuracyIncrease;
    }

    public void setAccuracyIncrease(int accuracyIncrease) {
        this.accuracyIncrease = accuracyIncrease;
    }

    public int getEvadeIncrease() {
        return evadeIncrease;
    }

    public void setEvadeIncrease(int evadeIncrease) {
        this.evadeIncrease = evadeIncrease;
    }
}
