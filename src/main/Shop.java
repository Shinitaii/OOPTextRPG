package main;

import javax.swing.JOptionPane;

import creature.player.Player;
import creature.player.body.*;
import inventory.Inventory;
import inventory.item.*;

public class Shop {

    private Player player;
    private Inventory inventory;
    private String TITLE;

    public Shop(Player player, Inventory inventory, String TITLE){
        this.player = player;
        this.inventory = inventory;
        this.TITLE = TITLE;
    }

    private void buyItem(Item selectedItem){
        player.setGold(player.getGold() - selectedItem.getPrice());
        inventory.addItem(selectedItem);
        JOptionPane.showMessageDialog(null, "Successfully bought " + selectedItem.getName() + "!", TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    public void shopMenu(){
        Object[] shopOptions = {"Equipment", "Consumables", "Exit"};
        int shopOptionsChoice = -1;
        while(shopOptionsChoice != 2){
            shopOptionsChoice = JOptionPane.showOptionDialog(null, "Welcome to the Shop! You have " + player.getGold() + " gold.\nWhat would you like to buy?", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, shopOptions, shopOptions[0]);
            switch(shopOptionsChoice){
                case 0: equipment(); break;
                case 1: consumables(); break;
                case 2: App.menu(); break;
            }
        }
    }

    private void equipment(){
        Object[] shopOptions = {"Weapons", "Armor", "Exit"};
        int shopOptionsChoice = JOptionPane.showOptionDialog(null, "Select which type of equipment you want to buy:", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, shopOptions, shopOptions[0]);
        switch(shopOptionsChoice){
            case 0: weapons(); break;
            case 1: armor(); break;
            case 2: shopMenu(); break;
        }
    }

    private void weapons(){
        //warrior weapon
        Weapon longSword = new Weapon("Long Sword", 10, 0, -2, 200);
        Weapon dagger = new Weapon("Dagger", 5, 2, 2, 300);
        Weapon greatSword = new Weapon("Greatsword", 15, -2, -3, 500);
        Weapon knightSword = new Weapon("Knight Sword", 12, 2, 1, 800);
        Weapon royalSword = new Weapon("Royal Sword", 20, 5, 0, 1500);
        //archer
        Weapon woodenBow = new Weapon("Wooden Bow", 5, 5, 0, 400);
        Weapon steelBow = new Weapon("Steel Bow", 10, 5, -3, 700);
        Weapon royalBow = new Weapon("Royal Bow", 20, 10, -5, 2000);
        //mage
        Weapon woodenStaff = new Weapon("Wooden Staff", 5, 0, 0, 500);
        Weapon gemstoneStaff = new Weapon("Gemstone Staff", 10, 2, 0, 1200);
        Weapon grandStaff = new Weapon("Grand Staff", 15, 5, 0, 2750);
        Weapon royalStaff = new Weapon("Royal Staff", 25, 10, 0, 7500);

        Weapon[][] weapons = {{longSword, dagger, greatSword, knightSword, royalSword}, {woodenBow, steelBow, royalBow}, {woodenStaff, gemstoneStaff, grandStaff, royalStaff}};
        String[] weaponOptions = new String[weapons[player.getRoleIndex()].length + 1];
        weaponOptions[weapons[player.getRoleIndex()].length] = "Exit";
        int i = 0;
        for (Weapon weapon : weapons[player.getRoleIndex()]) {
            if(weapon != null) weaponOptions[i++] = weapon.getName();
        }
        int weaponOptionsChoice = JOptionPane.showOptionDialog(null, "Select the weapon you would like to buy: ", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, weaponOptions, weaponOptions[0]);
        if(weaponOptionsChoice == weaponOptions.length - 1) equipment();
        else {
            Weapon selectedWeapon = (Weapon) weapons[player.getRoleIndex()][weaponOptionsChoice];
            int choice = JOptionPane.showConfirmDialog(null, "You have selected " + selectedWeapon.getName() + ".\nThis has " + selectedWeapon.getAttack() + " attack, " + selectedWeapon.getAccuracy() + " accuracy, and " + selectedWeapon.getEvade() + " evade.\nThis weapon costs " + selectedWeapon.getPrice() + " gold.\nWould you like to buy this weapon?", TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(choice == JOptionPane.YES_OPTION && player.getGold() >= selectedWeapon.getPrice()){
                buyItem(selectedWeapon);
                player.setWeapon(selectedWeapon);
            } else if (choice == JOptionPane.YES_OPTION && player.getGold() < selectedWeapon.getPrice()) {
                JOptionPane.showMessageDialog(null, "You do not have enough gold to buy " + selectedWeapon.getName() + "!", TITLE, JOptionPane.INFORMATION_MESSAGE);
            } 
            weapons();
        }
    }

    private void armor(){
        Object[] armorParts = {"Head", "Chest", "Legs", "Feet", "Exit"};
        int armorPartsChoice = JOptionPane.showOptionDialog(null, "Select which part would you like to buy?", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, armorParts, armorParts[0]);
        switch(armorPartsChoice){
            case 0: head(); break;
            case 1: chest(); break;
            case 2: legs(); break;
            case 3: feet(); break;
            case 4: equipment(); break;
        }
    }

    private void head(){
        //warrior
        Head ironHelmet = new Head("Iron Helmet", 10, 0, 5, -1, 200);
        Head goldHelmet = new Head("Gold Helmet", 20, 0, 7, 0, 500);
        Head royalHelmet = new Head("Royal Helmet", 20, 5, 10, -2, 1000);
        //archer
        Head leatherCap = new Head("Leather Cap", 5, 5, 2, 2, 200);
        Head royalCap = new Head("Royal Cap", 10, 5, 5, 5, 700);
        //mage
        Head brownHat = new Head("Brown Hat", 5, 10, 0, 1, 500);
        Head wizardHat = new Head("Wizard Hat", 5, 20, 3, 3, 1000);
        Head royalHat = new Head("Royal Hat", 10, 30, 7, 5, 1500);

        Head[][] helmets = {{ironHelmet, goldHelmet, royalHelmet}, {leatherCap, royalCap}, {brownHat, wizardHat, royalHat}};
        selectArmor(helmets, "helmet");
    }

    private void chest(){
        Chest ironChestplate = new Chest("Iron Chestplate", 10, 5, 3, -3, 400);
        Chest goldChestplate = new Chest("Gold Chestplate", 15, 10, 6, 0, 1000);
        Chest royalChestplate = new Chest("Royal Chestplate", 20, 20, 10, 3, 2500);

        Chest leatherVest = new Chest("Leather Vest", 5, 0, 2, 1, 500);
        Chest royalVest = new Chest("Royal Vest", 15, 5, 5, 3, 1500);

        Chest magicRobe = new Chest("Magic Robe", 5, 10, 2, 2, 400);
        Chest wizardRobe = new Chest("Wizard Robe", 10, 20, 5, 3, 1000);
        Chest royalRobe = new Chest("Royal Robe", 15, 30, 8, 6, 2500);

        Chest[][] chests = {{ironChestplate, goldChestplate, royalChestplate}, {leatherVest, royalVest}, {magicRobe, wizardRobe, royalRobe}};
        selectArmor(chests, "chest");
    }

    private void legs(){
        Legs ironLeggings = new Legs("Iron Leggingss", 5, 0, 5, -2, 400);
        Legs goldLeggings = new Legs("Gold Leggingss", 10, 0, 10, -3, 800);
        Legs royalLeggings = new Legs("Royal Leggings", 20, 0, 15, -6, 1500);

        Legs leatherPants = new Legs("Leather Pants", 5, 5, 2, 1, 500);
        Legs royalPants = new Legs("Royal Pants", 10, 0, 5, 4, 1200);

        Legs magicPants = new Legs("Magic Pants", 5, 10, 3, 1, 300);
        Legs wizardPants = new Legs("Wizard Pants", 10, 15, 6, 4, 900);
        Legs royalWizardPants = new Legs("Royal Wizard Pants", 20, 25, 10, 3, 2000);

        Legs[][] legs = {{ironLeggings, goldLeggings, royalLeggings}, {leatherPants, royalPants}, {magicPants, wizardPants, royalWizardPants}};
        selectArmor(legs, "leggings");   
    }

    private void feet(){
        Feet ironBoots = new Feet("Iron Boots", 3, 0, 2, -2, 500);
        Feet goldBoots = new Feet("Good Boots", 5, 0, 5, -4, 800);
        Feet royalBoots = new Feet("Royal Boots", 10, 0, 7, -1, 1400);

        Feet leatherBoots = new Feet("Leather Boots", 5, 0, 2, 1, 400);
        Feet royalArcherBoots = new Feet("Royal Boots", 10, 0, 5, 3, 1000);

        Feet magicBoots = new Feet("Magic Boots", 3, 10, 5, 3, 500);
        Feet wizardBoots = new Feet("Wizard Boots", 7, 15, 7, 5, 1200);
        Feet royalWizardBoots = new Feet("Royal Boots", 15, 25, 10, 7, 2500);

        Feet[][] feet = {{ironBoots, goldBoots, royalBoots}, {leatherBoots, royalArcherBoots}, {magicBoots, wizardBoots, royalWizardBoots}};
        selectArmor(feet, "feet");
    }

    private void selectArmor(Armor[][] armorArray, String armorType) {
        String[] armorOptions = new String[armorArray[player.getRoleIndex()].length + 1];
        armorOptions[armorArray[player.getRoleIndex()].length] = "Exit";
        int i = 0;
        for(Armor armor : armorArray[player.getRoleIndex()]){
            if(armor != null) armorOptions[i++] = armor.getName();
        }
        int armorChoice = JOptionPane.showOptionDialog(null, "Select the " + armorType + " you would like to buy:", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, armorOptions, armorOptions[0]);
        if(armorChoice == armorOptions.length - 1) armor();
        else {
            Armor selectedArmor = armorArray[player.getRoleIndex()][armorChoice];
            int choice = JOptionPane.showConfirmDialog(null, "You have selected " + selectedArmor.getName() + ".\nThis has " + selectedArmor.getHp() + " health, " + selectedArmor.getMp() + " mana, " + selectedArmor.getDefense() + " defense, and" + selectedArmor.getEvade() + " evade.\nThis armor costs " + selectedArmor.getPrice() + " gold.\nWould you like to buy this armor?", TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(choice == JOptionPane.YES_OPTION && player.getGold() >= selectedArmor.getPrice()){
                buyItem(selectedArmor);
                switch(armorType){
                    case "helmet": player.setHelmet((Head)selectedArmor); break;
                    case "chest": player.setChest((Chest) selectedArmor); break;
                    case "leggings": player.setLeggings((Legs) selectedArmor); break;
                    case "feet": player.setBoots((Feet) selectedArmor); break;
                }
            } else if (choice == JOptionPane.YES_OPTION && player.getGold() < selectedArmor.getPrice()) {
                JOptionPane.showMessageDialog(null, "You do not have enough gold to buy " + selectedArmor.getName() + "!", TITLE, JOptionPane.INFORMATION_MESSAGE);
            }
            armor();
        }
    }

    private void consumables(){
        Object[] consumablesOptions = {"Potions", "Exit"};
        int consumablesOptionsChoice = JOptionPane.showOptionDialog(null, "Select which type of consumables you would like to buy: ", TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, consumablesOptions, consumablesOptions[0]);
        switch(consumablesOptionsChoice){
            case 0: potion(); break;
            case 1: shopMenu(); break;
        }
    }

    private void potion(){
        Object[] potionOptions = {"Health Potion", "Mana Potion", "Exit"};
        int potionOptionsChoice = JOptionPane.showOptionDialog(null, "Select the potion you would like to buy:", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, potionOptions, potionOptions);
        switch(potionOptionsChoice){

        }
    }
}
