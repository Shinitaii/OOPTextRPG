package main;
import javax.swing.JOptionPane;

import creature.player.Player;
import creature.player.classes.*;
import inventory.*;

public class App {
    public static final String TITLE = "TextRPG - OOP";

    private static Player player;
    private static Inventory inventory;
    private static Battle battle;
    private static Shop shop;
    public static void main(String[] args) throws Exception {
        introduction();
        menu();
    }

    private static void introduction(){
        String name = "";
        while(true){
            name = JOptionPane.showInputDialog(null, "Welcome!\nPlease enter your name:", TITLE, JOptionPane.QUESTION_MESSAGE);
            if(name == null) {
                JOptionPane.showMessageDialog(null, "See you!", TITLE, JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } else if(name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a name!", TITLE, JOptionPane.QUESTION_MESSAGE);
                continue;
            } else break;
        }
        Object[] roles = {"Warrior", "Archer", "Mage"};
        int roleOptions = JOptionPane.showOptionDialog(null, "Welcome, " + name + "\nPlease select a class:\nNOTE: Once you clicked a class, you can't go back!", TITLE, 0, JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);
        switch(roleOptions){
            case JOptionPane.CLOSED_OPTION: System.exit(0);
            case 0: player = new Warrior(name); break;
            case 1: player = new Archer(name); break;
            case 2: player = new Mage(name); break;
        }
        JOptionPane.showMessageDialog(null, player.getName() + ", you have chosen the class: " + player.getRole() + ".", TITLE, JOptionPane.INFORMATION_MESSAGE);
        inventory = new Inventory();
        battle = new Battle(player, TITLE);
        shop = new Shop(player, inventory, TITLE); 
    }

    public static void menu(){
        player.setCurrentHP(player.getMaxHP());
        int menuOptionChoice = -1;
        while(menuOptionChoice != 3){
            Object[] menuOptions = {"Battle", "Shop", "Stats", "Exit"};
            menuOptionChoice = JOptionPane.showOptionDialog(null, "Hello, " + player.getName() + "!\nWhat do you want to do?", TITLE, 0, JOptionPane.QUESTION_MESSAGE, null, menuOptions, menuOptions[0]);
            switch(menuOptionChoice){
                case 0: battle.battleMenu(); break;
                case 1: shop.shopMenu(); break;
                case 2: stats(); break;
                case 3: JOptionPane.showMessageDialog(null, "See you!", TITLE, JOptionPane.INFORMATION_MESSAGE); System.exit(0);
            }
        }
    }

    private static void stats(){
        String stats = String.format("Name: %s\nClass: %s\n\nLevel: %d\nGold: %d\n\nHP: %,.0f/%,.0f\nMP: %,.0f/%,.0f\nEXP: %,.0f/%,.0f\n\nAttack: %d\nDefense: %d\nAccuracy: %d\nEvade: %d", player.getName(), player.getRole(), player.getLevel(), player.getGold(), player.getCurrentHP(), player.getMaxHP(), player.getCurrentMP(), player.getMaxMP(), player.getCurrentEXP(), player.getMaxEXP(), player.getAttack(), player.getDefense(), player.getAccuracy(), player.getEvade());
        JOptionPane.showMessageDialog(null, stats, TITLE, JOptionPane.INFORMATION_MESSAGE);
    }
}