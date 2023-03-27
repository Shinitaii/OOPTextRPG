package creature.enemy;
import java.util.Random;

import creature.Creature;
import creature.player.Player;

public class Enemy extends Creature{
    private static String[] enemyNames = {"Goblin", "Orc", "Skeleton", "Zombie", "Wraith", "Demon", "Dragon"};
    
    public Enemy(Player player){
        super("", player.getLevel(), 0, 0, 0, 0, 0, 0);
        this.generateName();
        this.generateStats(player);
        this.setCurrentHP(this.getMaxHP());
        this.setCurrentMP(this.getMaxMP());
    }

    private void generateName() {
        Random random = new Random();
        int index = random.nextInt(enemyNames.length);
        this.setName(enemyNames[index]);
    }

    private void generateStats(Player player){
        double scale = Math.pow(1.2, player.getLevel()/10);

        double minStat = 0.8; //80% 
        double maxStat = 1.2; // 120%

        double attack = player.getAttack() * (minStat + (maxStat - minStat) * Math.random()) * scale;
        double defense = player.getDefense() * (minStat + (maxStat - minStat) * Math.random()) * scale;
        double accuracy = player.getAccuracy() * (minStat + (maxStat - minStat) * Math.random()) * scale;
        double evade = player.getEvade() * (minStat + (maxStat - minStat) * Math.random()) * scale;
        double maxHP = player.getMaxHP() * (minStat + (maxStat - minStat) * Math.random()) * scale;
        double maxMP = player.getMaxMP() * (minStat + (maxStat - minStat) * Math.random()) * scale;

        this.setAttack((int)attack);
        this.setDefense((int)defense);
        this.setAccuracy((int)accuracy);
        this.setEvade((int)evade);
        this.setMaxHP((int)maxHP);
        this.setMaxMP((int)maxMP);
    }

    public void enemyChoice(Player player){
        if(this.getCurrentHP() < this.getCurrentHP() * 0.3) defend();
        else normalAttack(player);
    }

    public double dropEXP(Player player){
        return (Math.floor(player.getMaxEXP() * (2 * this.getLevel() + 5) / (player.getLevel() + 5)) / 10);
    }

    public int dropGold(){
        return this.getLevel() * (10 + (this.getLevel() * 2));
    }

    public int takeGold(Player player){
        int stolenGold = (int) Math.floor(player.getGold() * (0.05 + 0.01 * player.getLevel()));
        if(stolenGold >= player.getGold()) return stolenGold;
        else return player.getGold();
    }
}
