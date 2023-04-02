package creature.player;
import javax.swing.JOptionPane;

import creature.Creature;
import inventory.Inventory;
import inventory.item.*;
import inventory.item.armor.*;
import inventory.item.armor.bodyPart.Chest;
import inventory.item.armor.bodyPart.Feet;
import inventory.item.armor.bodyPart.Head;
import inventory.item.armor.bodyPart.Legs;
import main.App;

public class Player extends Creature{
    private double maxEXP, currentEXP;
    private int gold;
    private String role;
    private int roleIndex;
    private boolean hasWeapon, hasHelmet, hasChestplate, hasLeggings, hasBoots;

    public Player(String name, String role, int level, int attack, int defense, int accuracy, int evade, double maxHP, double maxMP, double maxEXP) {
        super(name, level, attack, defense, accuracy, evade, maxHP, maxMP);
        this.role = role;
        this.maxEXP = maxEXP;
        this.currentEXP = 0;
        this.gold = 0;
        this.roleIndex = determineRoleIndex(role);
        this.hasWeapon = false;
        this.hasHelmet = false;
        this.hasChestplate = false;
        this.hasLeggings = false;
        this.hasBoots = false;
    }
    
    //getters && setters
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public int getRoleIndex() { return roleIndex; }
    public double getMaxEXP() { return maxEXP; }
    public void setMaxEXP(double maxEXP) { this.maxEXP = maxEXP; }
    public double getCurrentEXP() { return currentEXP; }
    public void setCurrentEXP(double currentEXP) { this.currentEXP = currentEXP; }
    public int getGold() { return gold; }
    public void setGold(int gold) { this.gold = gold; }
    public boolean isHasWeapon() { return hasWeapon; }
    public void setHasWeapon(boolean hasWeapon) { this.hasWeapon = hasWeapon; }
    public boolean isHasHelmet() { return hasHelmet; }
    public void setHasHelmet(boolean hasHelmet) { this.hasHelmet = hasHelmet; }
    public boolean isHasChestplate() { return hasChestplate; }
    public void setHasChestplate(boolean hasChestplate) { this.hasChestplate = hasChestplate; }
    public boolean isHasLeggings() { return hasLeggings; }
    public void setHasLeggings(boolean hasLeggings) { this.hasLeggings = hasLeggings; }
    public boolean isHasBoots() { return hasBoots; }
    public void setHasBoots(boolean hasBoots) { this.hasBoots = hasBoots; }

    //custom methods
    private int determineRoleIndex(String role){
        switch(role){
            case "Warrior": return 0;
            case "Archer": return 1;
            case "Mage": return 2;
            default: return -1;
        }
    }

    public void checkEXP(){
        if(this.getCurrentEXP() >= this.getMaxEXP()) levelUp();
    }

    private void levelUp(){
        this.setLevel(this.getLevel() + 1);
        this.setMaxEXP(calculateMaxEXP(this.getMaxEXP(), 1.2, 0, this.getLevel()));
        this.setCurrentEXP(0);
        switch(this.getRoleIndex()){
            case 0: increaseStats(20, 5, 2, 4, 1, 1);
            case 1: increaseStats(15, 10, 3, 3, 2, 2);
            case 2: increaseStats(10, 20, 4, 2, 1, 1);
        }
        JOptionPane.showMessageDialog(null, "Congratulations " + this.getName() + "! You are now level " + this.getLevel() + "!", App.TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    private double calculateMaxEXP(double maxEXP, double growth, int offset, int level){
        return maxEXP * Math.pow(growth, level) + offset;
    }

    private void increaseStats(double newMaxHP, double newMaxMP, int newAttack, int newDefense, int newAccuracy, int newEvade){
        this.setMaxHP(this.getMaxHP() + newMaxHP);
        this.setMaxMP(this.getMaxMP() + newMaxMP);
        this.setAttack(this.getAttack() + newAttack);
        this.setDefense(this.getDefense() + newDefense);
        this.setAccuracy(this.getAccuracy() + newAccuracy);
        this.setEvade(this.getEvade() + newEvade);
    }

    public void checkInventory(Inventory inventory){
        
    }

    public void setWeapon(Weapon weapon){
        if(!isHasWeapon()){
            this.setAttack(this.getAttack() + weapon.getAttack());
            this.setAccuracy(this.getAccuracy() + weapon.getAccuracy());
            this.setEvade(this.getEvade() + weapon.getEvade());
            this.setHasWeapon(true);
        }
    }

    private void setArmor(Armor armor, String armorType){
        this.setMaxHP(this.getMaxHP() + armor.getHp());
        this.setMaxMP(this.getMaxMP() + armor.getMp());
        this.setDefense(this.getDefense() + armor.getDefense());
        this.setEvade(this.getEvade() + armor.getEvade());
    }

    public void setHelmet(Head helmet){
        if(!isHasHelmet()){
            this.setArmor(helmet, "helmet");
            this.setHasHelmet(true);
        }
    }
    
    public void setChest(Chest chest){
        if(!isHasChestplate()){
            this.setArmor(chest, "chest");
            this.setHasChestplate(true);
        }
    }
    
    public void setLeggings(Legs armor){
        if(!isHasLeggings()){
            this.setArmor(armor, "leggings");
            this.setHasLeggings(true);
        }
    }
    
    public void setBoots(Feet armor){
        if(!isHasBoots()){
            this.setArmor(armor, "feet");
            this.setHasBoots(true);
        }
    }
}
