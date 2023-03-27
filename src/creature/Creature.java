package creature;
import java.util.Random;
import javax.swing.JOptionPane;
import main.App;

public class Creature {
    private int level, attack, defense, accuracy, evade;
    private double maxHP, currentHP, maxMP, currentMP;
    private String name;
    private boolean isDefending;

    public Creature(String name, int level, int attack, int defense, int accuracy, int evade, double maxHP, double maxMP){
        this.name = name;
        this.level = level;
        this.attack = attack;
        this.defense = defense;
        this.accuracy = accuracy;
        this.evade = evade;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.maxMP = maxMP;
        this.currentMP = maxMP;
        this.isDefending = false;
    }

    //getters && setters
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public int getLevel(){ return level; }
    public void setLevel(int level){ this.level = level; }
    public int getAttack(){ return attack; }
    public void setAttack(int attack){ this.attack = attack; }
    public int getDefense(){ return defense; }
    public void setDefense(int defense){ this.defense = defense; }
    public int getAccuracy() { return accuracy; }
    public void setAccuracy(int accuracy) { this.accuracy = accuracy; }
    public int getEvade() { return evade; }
    public void setEvade(int evade) { this.evade = evade; }
    public double getMaxHP() { return maxHP; }
    public void setMaxHP(double maxHP) { this.maxHP = maxHP; }
    public double getCurrentHP() { return currentHP; }
    public void setCurrentHP(double currentHP) { this.currentHP = currentHP; }
    public double getMaxMP() { return maxMP; }
    public void setMaxMP(double maxMP) { this.maxMP = maxMP; }
    public double getCurrentMP() { return currentMP; }
    public void setCurrentMP(double currentMP) { this.currentMP = currentMP; }
    
    //custom methods

    public void normalAttack(Creature creature) { // normal attack for player
        double damageDealt = Math.round((2.0 / 3.0) * (this.getAttack() - creature.getDefense()) + (new Random().nextInt(11)));
        if(damageDealt <= -1) damageDealt = 0; 
        creature.setCurrentHP(creature.getCurrentHP() - damageDealt);
        JOptionPane.showMessageDialog(null, String.format("%s dealt %,.0f damage to %s!", this.getName(), damageDealt, creature.getName()), App.TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean checkDefending(){
        if(this.isDefending) return true;
        else return false;
    }

    public void defend(){
        if(!this.isDefending){
            this.isDefending = true;
            this.setDefense(this.getDefense() * 2);
            JOptionPane.showMessageDialog(null, getName() + " defended!", App.TITLE, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void resetDefend(){
        if(this.isDefending){
            this.isDefending = false;
            this.setDefense(this.getDefense() / 2);
        }
    }
}
