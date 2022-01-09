package machine;

import java.util.Scanner;

enum State {
    FILL,
    BUY,
    TAKE,
    REMAINING,
    EXIT
}

public class CoffeeMachine {

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        machine.interact();
    }

    private static final Scanner scanner = new Scanner(System.in);

    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;
    private State state;

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;

    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getBeans() {
        return beans;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void interact() {

        boolean quit = false;
        while (!quit) {
            state = getState();
            switch (state) {
                case FILL:
                    fillMachine();
                    break;
                case BUY:
                    chooseCoffeeType();
                    break;
                case TAKE:
                    withdraw();
                    break;
                case REMAINING:
                    showResources();
                    break;
                case EXIT:
                    quit = true;
                default:
            }
        }
    }

    private State getState() { // validate if input is correct Enum state
        boolean stateValid = false;
        while (!stateValid) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String tempState = scanner.nextLine().toUpperCase();
            for (State s : State.values()) {
                String memberOfEnum = s.name();
                if (memberOfEnum.equals(tempState)) {
                    state = s;
                    stateValid = true;
                }
            }
            if (!stateValid) {
                System.out.println("Choose correct action.");
            }
        }
        return state;
    }

    public void chooseCoffeeType() {
        boolean decided = false;
        do {
            System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
            if (scanner.hasNextInt()) {
                int typeOfCoffee = scanner.nextInt();
                buy(typeOfCoffee);
                decided = true;
                scanner.nextLine(); // takes empty line after integer
            } else {
                String user = scanner.nextLine();
                String goBack = "back";
                if (user.equals(goBack)) {
                    decided = true;
                } else {
                    System.out.println("Choose correct option.");
                }
            }
        } while (!decided);
    }

    public void buy(int typeOfCoffee) {

        if (typeOfCoffee == 1) {
            calculate(250, 0, 16, 1, 4);
        } else if (typeOfCoffee == 2) {
            calculate(350, 75, 20, 1, 7);
        } else if (typeOfCoffee == 3) {
            calculate(200, 100, 12, 1, 6);
        }
    }

    public void fillMachine() {

        System.out.println("\nWrite how many ml of water do you want to add: ");
        int water = scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        int milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        int beans = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        int cup = scanner.nextInt();
        setWater(getWater() + water);
        setMilk(getMilk() + milk);
        setBeans(getBeans() + beans);
        setCups(getCups() + cup);
        scanner.nextLine(); // takes empty line after integer
    }

    public void withdraw() {
        System.out.println("I gave you $" + getMoney() + "\n");
        setMoney(0);
    }

    public void calculate(int water, int milk, int beans, int cup, int money) {

        if (getWater() - water < 0) {
            System.out.println("Sorry, not enough water!");
        } else if (getMilk() - milk < 0) {
            System.out.println("Sorry, not enough milk!");
        } else if (getBeans() - beans < 0) {
            System.out.println("Sorry, not enough beans");
        } else if (getCups() - cup < 0) {
            System.out.println("Sorry, not enough cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!\n");
            setWater(getWater() - water);
            setMilk(getMilk() - milk);
            setBeans(getBeans() - beans);
            setCups(getCups() - cup);
            setMoney(getMoney() + money);
        }
    }

    public void showResources() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(getWater() + " of water");
        System.out.println(getMilk() + " of milk");
        System.out.println(getBeans() + " of coffee beans");
        System.out.println(getCups() + " of disposable cups");
        System.out.println("$" + getMoney() + " of money\n");
    }

}
