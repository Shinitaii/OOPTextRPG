package main;

import javax.swing.JOptionPane;

import creature.player.Player;
import inventory.Inventory;
import inventory.item.*;
import inventory.item.armor.*;
import inventory.item.armor.bodyPart.*;

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
        JOptionPane.showMessageDialog(null, "Successfully bought " + selectedItem.getQuantity() + " " + selectedItem.getName() + "for " + (selectedItem.getPrice() * selectedItem.getQuantity()) + "!", TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    private void notEnoughGoldMessage(Item item){
        JOptionPane.showMessageDialog(null, "You do not have enough gold to buy " + item.getQuantity() + " " + item.getName() + "!", TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    public void shopMenu(){
        Object[] shopOptions = {"Equipment", "Consumables", "Exit"};
        int shopOptionsChoice = -1;
        while(shopOptionsChoice != 2){
            shopOptionsChoice = JOptionPane.showOptionDialog(null, "Welcome to the Shop! You have " + player.getGold() + " gold.\nWhat would you like to buy?", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, shopOptions, shopOptions[0]);
            switch(shopOptionsChoice){
                case 0: equipmentMenu(); break;
                case 1: consumablesMenu(); break;
                case 2: App.menu(); break;
            }
        }
    }

    private void equipmentMenu(){
        Object[] shopOptions = {"Weapons", "Armor", "Exit"};
        int shopOptionsChoice = JOptionPane.showOptionDialog(null, "Select which type of equipment you want to buy:", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, shopOptions, shopOptions[0]);
        switch(shopOptionsChoice){
            case 0: weaponsMenu(); break;
            case 1: armorMenu(); break;
            case 2: shopMenu(); break;
        }
    }

    private void weaponsMenu() {
        Weapon[][] weapons = {{new Weapon("Long Sword", 10, 0, -2, 200), new Weapon("Dagger", 5, 2, 2, 300), new Weapon("Greatsword", 15, -2, -3, 500), new Weapon("Knight Sword", 12, 2, 1, 800), new Weapon("Royal Sword", 20, 5, 0, 1500)},
                              {new Weapon("Wooden Bow", 5, 5, 0, 400), new Weapon("Steel Bow", 10, 5, -3, 700), new Weapon("Royal Bow", 20, 10, -5, 2000)},
                              {new Weapon("Wooden Staff", 5, 0, 0, 500), new Weapon("Gemstone Staff", 10, 2, 0, 1200), new Weapon("Grand Staff", 15, 5, 0, 2750), new Weapon("Royal Staff", 25, 10, 0, 7500)}};
        String[] weaponOptions = new String[weapons[player.getRoleIndex()].length + 1];
        weaponOptions[weapons[player.getRoleIndex()].length] = "Exit";
        int i = 0;
        for (Weapon weapon : weapons[player.getRoleIndex()]) {
            if (weapon != null) {
                weaponOptions[i++] = weapon.getName();
            }
        }
        int weaponOptionsChoice = JOptionPane.showOptionDialog(null, "Select the weapon you would like to buy: ", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, weaponOptions, weaponOptions[0]);
        if (weaponOptionsChoice == weaponOptions.length - 1) {
            equipmentMenu();
        } else {
            Weapon selectedWeapon = weapons[player.getRoleIndex()][weaponOptionsChoice];
            int choice = JOptionPane.showConfirmDialog(null, "You have selected " + selectedWeapon.getName() + ".\nThis has " + selectedWeapon.getAttack() + " attack, " + selectedWeapon.getAccuracy() + " accuracy, and " + selectedWeapon.getEvade() + " evade.\nThis weapon costs " + selectedWeapon.getPrice() + " gold.\nWould you like to buy this weapon?", TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION && player.getGold() >= selectedWeapon.getPrice()) {
                buyItem(selectedWeapon);
                player.setWeapon(selectedWeapon);
            } else if (choice == JOptionPane.YES_OPTION && player.getGold() < selectedWeapon.getPrice()) notEnoughGoldMessage(selectedWeapon);
            else weaponsMenu();

        }
    }

    private void armorMenu(){
        Object[] armorParts = {"Head", "Chest", "Legs", "Feet", "Exit"};
        int armorPartsChoice = JOptionPane.showOptionDialog(null, "Select which part would you like to buy?", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, armorParts, armorParts[0]);
        switch(armorPartsChoice){
            case 0: head(); break;
            case 1: chest(); break;
            case 2: legs(); break;
            case 3: feet(); break;
            case 4: equipmentMenu(); break;
        }
    }

    private void head() {
        Head[][] helmets = new Head[][]{
            {new Head("Iron Helmet", 10, 0, 5, -1, 200),
            new Head("Gold Helmet", 20, 0, 7, 0, 500),
            new Head("Royal Helmet", 20, 5, 10, -2, 1000)},

            {new Head("Leather Cap", 5, 5, 2, 2, 200),
            new Head("Royal Cap", 10, 5, 5, 5, 700)},
         
            {new Head("Brown Hat", 5, 10, 0, 1, 500),
             new Head("Wizard Hat", 5, 20, 3, 3, 1000),
             new Head("Royal Hat", 10, 30, 7, 5, 1500)}
        };
        selectArmor(helmets, "helmet");
    }

    private void chest() {
        Chest[][] chests = {{new Chest("Iron Chestplate", 10, 5, 3, -3, 400),
                            new Chest("Gold Chestplate", 15, 10, 6, 0, 1000),
                            new Chest("Royal Chestplate", 20, 20, 10, 3, 2500)},

                            {new Chest("Leather Vest", 5, 0, 2, 1, 500),
                            new Chest("Royal Vest", 15, 5, 5, 3, 1500)},

                            {new Chest("Magic Robe", 5, 10, 2, 2, 400),
                            new Chest("Wizard Robe", 10, 20, 5, 3, 1000),
                            new Chest("Royal Robe", 15, 30, 8, 6, 2500)}};
        selectArmor(chests, "chest");
    }

    private void legs() {
        Legs[][] legs = {{new Legs("Iron Leggings", 5, 0, 5, -2, 400),
                         new Legs("Gold Leggings", 10, 0, 10, -3, 800),
                         new Legs("Royal Leggings", 20, 0, 15, -6, 1500)},

                        {new Legs("Leather Pants", 5, 5, 2, 1, 500),
                         new Legs("Royal Pants", 10, 0, 5, 4, 1200)},

                        {new Legs("Magic Pants", 5, 10, 3, 1, 300),
                         new Legs("Wizard Pants", 10, 15, 6, 4, 900),
                         new Legs("Royal Wizard Pants", 20, 25, 10, 3, 2000)}};
        selectArmor(legs, "leggings");   
    }
    
    private void feet() {
        Feet[][] feet = {{new Feet("Iron Boots", 3, 0, 2, -2, 500),
                         new Feet("Gold Boots", 5, 0, 5, -4, 800),
                         new Feet("Royal Boots", 10, 0, 7, -1, 1400)},

                        {new Feet("Leather Boots", 5, 0, 2, 1, 400),
                         new Feet("Royal Archer Boots", 10, 0, 5, 3, 1000)},

                        {new Feet("Magic Boots", 3, 10, 5, 3, 500),
                         new Feet("Wizard Boots", 7, 15, 7, 5, 1200),
                         new Feet("Royal Wizard Boots", 15, 25, 10, 7, 2500)}};
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
        if(armorChoice == armorOptions.length - 1) armorMenu();
        else {
            Armor selectedArmor = armorArray[player.getRoleIndex()][armorChoice];
            int choice = JOptionPane.showConfirmDialog(null, "You have selected " + selectedArmor.getName() + ".\nThis has " + selectedArmor.getHp() + " health, " + selectedArmor.getMp() + " mana, " + selectedArmor.getDefense() + " defense, and" + selectedArmor.getEvade() + " evade.\nThis armor costs " + selectedArmor.getPrice() + " gold.\nWould you like to buy this armor?", TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(choice == JOptionPane.YES_OPTION && player.getGold() >= selectedArmor.getPrice()){
                buyItem(selectedArmor);
                switch(selectedArmor.getPartID()){
                    case 0: player.setHelmet((Head)selectedArmor); break;
                    case 1: player.setChest((Chest) selectedArmor); break;
                    case 2: player.setLeggings((Legs) selectedArmor); break;
                    case 3: player.setBoots((Feet) selectedArmor); break;
                }
            } else if (choice == JOptionPane.YES_OPTION && player.getGold() < selectedArmor.getPrice()) notEnoughGoldMessage(selectedArmor);
            else armorMenu();
        }
    }

    private void consumablesMenu(){
        Object[] consumablesOptions = {"Potions", "Exit"};
        int consumablesOptionsChoice = JOptionPane.showOptionDialog(null, "Select which type of consumables you would like to buy: ", TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, consumablesOptions, consumablesOptions[0]);
        switch(consumablesOptionsChoice){
            case 0: potionMenu(); break;
            case 1: shopMenu(); break;
        }
    }

    private void potionMenu(){
        Object[] potionOptions = {"Health Potion", "Mana Potion", "Exit"};
        int potionOptionsChoice = JOptionPane.showOptionDialog(null, "Select the potion you would like to buy:", TITLE, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, potionOptions, potionOptions);
        switch(potionOptionsChoice){
            case 0: typePotionMenu("Health", potionOptionsChoice); break;
            case 1: typePotionMenu("Mana", potionOptionsChoice); break;
            case 2: consumablesMenu(); break;
        }
    }

    private void typePotionMenu(String name, int potionType){
        Potion potion = new Potion("", 0, 0, 0, 0, 0, 0, 0, 0);
        Object[] potionOptions = {"Small "+ name +" Potion", name + " Potion", "Great "+ name +" Potion", "Greater "+ name +" Potion", "Exit"};
        
        int potionOptionsChoice = JOptionPane.showOptionDialog(null, "Select which "+ name +" potion you would like to buy:", TITLE, 0, JOptionPane.QUESTION_MESSAGE, null, potionOptions, potionOptions[0]);
        String potionName = (String) potionOptions[potionOptionsChoice];
        int increase = 0, price = 0;
        switch(potionOptionsChoice){
            case 0: increase = 20; price = 30; break;
            case 1: increase = 50; price = 60; break;
            case 2: increase = 80; price = 100; break;
            case 3: increase = 120; price = 175; break;
            case 4: potionMenu(); break;
        }
        switch(potionType) {
            case 0: buyPotionProcess(potion, potionName, increase, 0, price); break;
            case 1: buyPotionProcess(potion, potionName, 0, increase, price); break;
        }
    }

    private void buyPotionProcess(Potion potion, String name, int healthIncrease, int manaIncrease, int price){
        potion.setName(name); 
        potion.setHealthIncrease(healthIncrease); 
        potion.setManaIncrease(manaIncrease);
        potion.setPrice(price);
        int quantity = 0;
        while(true){
            try{
                String input = JOptionPane.showInputDialog(null, "How many " + potion.getName() + " you would like to buy:", TITLE, JOptionPane.QUESTION_MESSAGE);
                if (input != null) {                     
                    quantity = Integer.parseInt(input);
                    if(quantity < 0) {
                        JOptionPane.showMessageDialog(null, "Please input a number more than 0.", TITLE, JOptionPane.ERROR_MESSAGE);
                        continue;
                    } else break;
                }
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.", TITLE, JOptionPane.ERROR_MESSAGE);
            }
        }
        potion.increaseQuantity(quantity);
        int totalPrice = (potion.getPrice() * potion.getQuantity());
        String buyDetails = String.format("You're going to buy %d %s for %d gold.\nAre you sure?", potion.getQuantity(), potion.getName(), totalPrice);
        int confirmBuy = JOptionPane.showConfirmDialog(null, buyDetails, TITLE, 0, JOptionPane.QUESTION_MESSAGE);
        if(confirmBuy == JOptionPane.YES_OPTION && player.getGold() >= totalPrice){ buyItem(potion);
        } else if (confirmBuy == JOptionPane.YES_OPTION && player.getGold() < totalPrice) notEnoughGoldMessage(potion);
        else potionMenu();
    }
}