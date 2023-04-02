package main;

import javax.swing.JOptionPane;

import creature.player.Player;
import creature.enemy.Enemy;

public class Battle {

    private Player player;
    private String TITLE;

    public Battle(Player player, String TITLE){
        this.player = player;
        this.TITLE = TITLE;
    }


    public void battleMenu(){
        int turn = 1;
        int playerChoice = -1;
        Enemy enemy = new Enemy(player);
        JOptionPane.showMessageDialog(null, "You encountered " + enemy.getName() + "!", TITLE, JOptionPane.WARNING_MESSAGE);
        Object[] playerOptions = {"Normal Attack", "Defend", "Skill", "Item", "Run"};
        while(!player.isDefeated() && !enemy.isDefeated() && playerChoice != 4){
            String status = String.format("Turn %d\n\n%s | HP: %,.0f / %,.0f | MP: %,.0f / %,.0f\n%s | HP: %,.0f / %,.0f | MP: %,.0f / %,.0f\n\nWhat do you want to do?", turn, player.getName(), player.getCurrentHP(), player.getMaxHP(), player.getCurrentMP(), player.getMaxMP(), enemy.getName(), enemy.getCurrentHP(), enemy.getMaxHP(), enemy.getCurrentMP(), enemy.getMaxMP());
            playerChoice = JOptionPane.showOptionDialog(null, status, TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, playerOptions, playerOptions[0]);
            switch(playerChoice){
                case 0: player.normalAttack(enemy); break;
                case 1: player.defend(); break;
                case 2: break;
                case 3: break;
                case 4: JOptionPane.showMessageDialog(null, "You ran away successfully!", TITLE, JOptionPane.INFORMATION_MESSAGE); App.menu(); break;
            }
            enemy.resetDefend();
            enemy.enemyChoice(player);
            player.resetDefend();

            if(enemy.isDefeated()) victory(enemy);
            if(player.isDefeated()) defeat(enemy);
            turn++;
        }
        App.menu();
    }

    private void victory(Enemy enemy){
        String message = String.format("You won!\n\nYou gained %,.0f EXP\nYou have received %d gold!", enemy.dropEXP(player), enemy.dropGold());
        player.setCurrentEXP(player.getCurrentEXP() + enemy.dropEXP(player));
        player.setGold(player.getGold() + enemy.dropGold());
        player.checkEXP();
        JOptionPane.showMessageDialog(null, message, TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    private void defeat(Enemy enemy){
        String message = String.format("You lost! %s happened to steal %d of your gold!", enemy.getName(), enemy.takeGold(player));
        player.setGold(player.getGold() - enemy.takeGold(player));
        JOptionPane.showMessageDialog(null, message, TITLE, JOptionPane.WARNING_MESSAGE);
    }
}
