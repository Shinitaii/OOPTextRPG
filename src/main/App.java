package main;
import javax.swing.JOptionPane;

import creature.player.Player;
import creature.player.classes.*;
import inventory.*;

public class App {
    private static Player player;
    private static Inventory inventory;
    public static final String TITLE = "TextRPG - OOP";
    public static void main(String[] args) throws Exception {
        introduction();
        menu();
    }

    private static void introduction(){
        String name = JOptionPane.showInputDialog(null, "Welcome!\nPlease enter your name:", TITLE, JOptionPane.QUESTION_MESSAGE);
        Object[] roles = {"Warrior", "Archer", "Mage"};
        int roleOptions = JOptionPane.showOptionDialog(null, "Welcome, " + name + "\nPlease select a class:\nNOTE: Once you clicked a class, you can't go back!", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);
        switch(roleOptions){
            case 0: player = new Warrior(name); break;
            case 1: player = new Archer(name); break;
            case 2: player = new Mage(name); break;
        }
        JOptionPane.showMessageDialog(null, player.getName() + ", you have chosen the class: " + player.getRole() + ".", TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void menu(){
        player.setCurrentHP(player.getMaxHP());
        int menuOptionChoice = 0;
        while(menuOptionChoice != 3){
            Object[] menuOptions = {"Battle", "Shop", "Stats", "Exit"};
            menuOptionChoice = JOptionPane.showOptionDialog(null, "Hello, " + player.getName() + "!\nWhat do you want to do?", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuOptions, menuOptions[0]);
            switch(menuOptionChoice){
                case 0: Battle battle = new Battle(player, TITLE); battle.battleMenu(); break;
                case 1: Shop shop = new Shop(player, inventory, TITLE); shop.shopMenu(); break;
                case 2: stats(); break;
                case 3: JOptionPane.showMessageDialog(null, "See you!", TITLE, JOptionPane.INFORMATION_MESSAGE); System.exit(0); break;
            }
        }
    }

    private static void stats(){
        String stats = String.format("Name: %s\nClass: %s\n\nLevel: %d\nGold: %d\n\nHP: %,.0f/%,.0f\nMP: %,.0f/%,.0f\nEXP: %,.0f/%,.0f\n\nAttack: %d\nDefense: %d\nAccuracy: %d\nEvade: %d", player.getName(), player.getRole(), player.getLevel(), player.getGold(), player.getCurrentHP(), player.getMaxHP(), player.getCurrentMP(), player.getMaxMP(), player.getCurrentEXP(), player.getMaxEXP(), player.getAttack(), player.getDefense(), player.getAccuracy(), player.getEvade());
        JOptionPane.showMessageDialog(null, stats, TITLE, JOptionPane.INFORMATION_MESSAGE);
    }
}