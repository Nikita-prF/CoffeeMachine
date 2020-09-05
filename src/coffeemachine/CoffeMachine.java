package coffeemachine;
import java.util.*;

/**
 * The programme simulates the basic operation cycle of the coffee machine with features input User's commands
 *
 * @author Nikita Filimonov
 */

class CoffeeMachine {

    /**
     * Main class that contained a resource
     * {@link Resource#machine} available coffee machine resources
     * {@link Resource#espRes} required resources for make espresso
     * {@link Resource#latRes} required resources for make latte
     * {@link Resource#capRes} required resources for make cappuccino
     */
    static class Resource {
        int water;
        int milk;
        int beans;
        int cups;
        int money;

        Resource(int water, int milk, int beans, int cups, int money) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cups = cups;
            this.money = money;
        }
    }

    Resource espRes = new Resource(250, 0, 16, 1, 4);
    Resource latRes = new Resource(350, 75, 20, 1, 7);
    Resource capRes = new Resource(200, 100, 12, 1, 6);
    Resource machine = new Resource(400, 540, 120, 9, 550);


    /**
     * Main work cycle
     * Take string command as argument and run related method
     * @param cmd given string command from User
     */
    void startWork(String cmd) {
        System.out.println();
        switch (cmd) {
            case "buy":
                buyProcess(machine, espRes, latRes, capRes);
                break;
            case "fill":
                fillMachine(machine);
                break;
            case "take":
                gaveMoney(machine);
                break;
            case "remaining":
                printStatus(machine);
                break;
            case "exit":
                System.exit(1);
            default:
                System.out.println("Unknown command");
                break;
        }
        System.out.println();
    }


    /**
     * The process of "give" all accumulated money to the user
     * @param machine available coffee machine resources
     */
    void gaveMoney (Resource machine) {
        System.out.printf("I gave you $%d\n", machine.money);
        machine.money = 0;
    }


    /**
     * Coffee machine's resources filling process by User
     * @param machine available coffee machine resources
     */
    void fillMachine (Resource machine) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Write how many ml of water do you want to add:");
        machine.water += scan.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        machine.milk += scan.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        machine.beans += scan.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        machine.cups += scan.nextInt();
    }

    /**
     * Chosen coffee making Process
     * @param machine available coffee machine resources
     * @param res required resources of chosen coffee
     */
    void coffeeMake(Resource machine, Resource res) {
        System.out.println("I have enough resources, making you a coffee!");
        machine.water -= res.water;
        machine.milk -= res.milk;
        machine.beans -= res.beans;
        machine.cups -= res.cups;
        machine.money += res.money;
    }

    /**
     * Check availability resources for make chosen coffee
     * @param machine available coffee machine resources
     * @param res required resources of chosen coffee
     * @return string with the name of the missing product or "ok" if there are enough resources
     */
    String checkResource(Resource machine, Resource res) {
        if (machine.water < res.water) { return "water"; }
        if (machine.milk < res.milk) { return "milk"; }
        if (machine.beans < res.beans) { return "coffee beans"; }
        if (machine.cups < res.cups) { return "disposable cups"; }
        return "ok";
    }


    /**
     * Print amount of resources at the current time
     * @param machine available coffee machine resources
     */
    void printStatus(Resource machine) {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n", machine.water);
        System.out.printf("%d of milk\n", machine.milk);
        System.out.printf("%d of coffee beans\n", machine.beans);
        System.out.printf("%d of disposable cups\n", machine.cups);
        System.out.printf("$%d of money\n", machine.money);
    }

    /**
     * Main buying process
     * @param machine available coffee machine resources
     * @param espRes required resources for make espresso
     * @param latRes required resources for make latte
     * @param capRes required resources for make cappuccino
     */
    void buyProcess(Resource machine, Resource espRes, Resource latRes, Resource capRes) {
        Scanner scan = new Scanner(System.in);

        System.out.println("What do you want to buy? 1 - espresso," +
                " 2 - latte, 3 - cappuccino, back - to main menu:");

        switch (scan.nextLine()) {
            case "1":
                String checkEsp = checkResource(machine, espRes);
                if (!checkEsp.equals("ok")) {
                    System.out.printf("Sorry, not enough %s!\n", checkEsp);
                } else { coffeeMake(machine, espRes); }
                break;
            case "2":
                String checkLat = checkResource(machine, latRes);
                if (!checkLat.equals("ok")) {
                    System.out.printf("Sorry, not enough %s!\n", checkLat);
                } else { coffeeMake(machine, latRes); }
                break;
            case "3":
                String checkCap = checkResource(machine, capRes);
                if (!checkCap.equals("ok")) {
                    System.out.printf("Sorry, not enough %s!\n", checkCap);
                } else { coffeeMake(machine, capRes); }
                break;
            case "back":
                break;
            default:
                System.out.println("Unknown num. Please enter num again");
                break;
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        CoffeeMachine machine = new CoffeeMachine();
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            machine.startWork(scan.nextLine());
        }
    }
}
