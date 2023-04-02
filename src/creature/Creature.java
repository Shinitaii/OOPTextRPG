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

    public boolean isDefeated(){
        return this.getCurrentHP() <= 0;
    }

    public void normalAttack(Creature creature) { // normal attack for player
        double damageDealt = Math.round((2.0 / 3.0) * (this.getAttack() - creature.getDefense()) + (new Random().nextInt(11)));
        if(damageDealt <= -1) damageDealt = 0; 
        if(attackHits(this.getAccuracy(), creature.getEvade())){
            creature.receiveDamage(damageDealt);
            JOptionPane.showMessageDialog(null, String.format("%s dealt %,.0f damage to %s!", this.getName(), damageDealt, creature.getName()), App.TITLE, JOptionPane.INFORMATION_MESSAGE);
        } else JOptionPane.showMessageDialog(null,this.getName() + " missed!", App.TITLE, JOptionPane.INFORMATION_MESSAGE);
        
    }

    private void receiveDamage(double damage){
        this.setCurrentHP(this.getCurrentHP() - damage);
    }

    private double calculateMissChance(int attackingAccuracy, int defendingEvasion) {
        double missChance = attackingAccuracy - defendingEvasion;
        if (missChance > 0) return missChance / 100;
        else return 0;
    }

    private boolean attackHits(int accuracy, int evasion) {
        double missChance = calculateMissChance(accuracy, evasion);
        double modifiedHitRate = 1.00 - missChance;
        double random = Math.random();
        return random < modifiedHitRate;
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
