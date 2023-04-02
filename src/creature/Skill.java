package creature;

public class Skill {
    private Creature creature;
    private String name;
    private int elementID, roleIndex, mpCost;
    private double baseDamage;

    public Skill(Creature creature, String name, int elementID, int roleIndex, int mpCost, double baseDamage){
        this.creature = creature;
        this.name = name;
        this.elementID = elementID;
        this.roleIndex = roleIndex;
        this.mpCost = mpCost;
        this.baseDamage = baseDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
