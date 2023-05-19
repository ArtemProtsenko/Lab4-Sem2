import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static boolean isInteger(String string) {
        return string.matches("-?\\d+");
    }

    static String condition(boolean isCarNew){
        if(isCarNew){
            return "New";
        }
        return "Used";
    }

    public static class Car{
        private int cost;
        private boolean isNew;
        private int maxSpeed;
        private int weight;
        private int horsepower;

        public Car(int carCost, boolean isCarNew, int carMaxSpeed, int carWeight, int carHorsepower){
            cost = carCost;
            isNew = isCarNew;
            maxSpeed = carMaxSpeed;
            weight = carWeight;
            horsepower = carHorsepower;
        }

        public int getCost() { return cost; }

        public boolean getCondition() { return isNew; }

        public int getMaxSpeed() { return maxSpeed; }

        public int getWeight() { return weight; }

        public int getHorsepower() { return horsepower; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Car[] cars = {
                new Car(4900, true, 100, 1700, 175),
                new Car(3000000, true, 380, 1995, 1500),
                new Car(10000, false, 160, 1400, 160)};

        Comparator<Car> comparator;

        System.out.print("Id's of comparing qualities are: Cost - 1; Condition - 2; Max speed - 3, Weight - 4, " +
                "Horsepower - 5.\n" +
                "To sort enter the id of element you want to sort by: ");
        String userIdInput = scanner.next();

        while(!isInteger(userIdInput)){
            System.out.print("Input is not an integer.\n" +
                    "Enter id: ");
            userIdInput = scanner.next();
        }

        while(Integer.parseInt(userIdInput) < 1 || Integer.parseInt(userIdInput) > 5){
            System.out.print("Input is out of range.\n" +
                    "Enter id: ");
            userIdInput = scanner.next();
            while(!isInteger(userIdInput)){
                System.out.print("Input is not an integer.\n" +
                        "Enter id: ");
                userIdInput = scanner.next();
            }
        }

        System.out.print("To select order enter 'ascending' for ascending order and 'descending' for descending." +
                "Enter preferred order: ");
        String userSeqInput = scanner.next();

        while(!Objects.equals(userSeqInput, "ascending") && !Objects.equals(userSeqInput, "descending")){
            System.out.print("Order entered incorrectly." +
                    "Enter preferred order: ");
            userSeqInput = scanner.next();
        }

        if(Integer.parseInt(userIdInput) == 1){ comparator = Comparator.comparingInt(Car::getCost); }
        else if(Integer.parseInt(userIdInput) == 2){ comparator = Comparator.comparing(Car::getCondition); }
        else if(Integer.parseInt(userIdInput) == 3){ comparator = Comparator.comparingInt(Car::getMaxSpeed); }
        else if(Integer.parseInt(userIdInput) == 4){ comparator = Comparator.comparingInt(Car::getWeight); }
        else{ comparator = Comparator.comparingInt(Car::getHorsepower); }

        if(Objects.equals(userSeqInput, "descending")){ comparator = comparator.reversed(); }

        Arrays.sort(cars, comparator);

        for(Car obj : cars){
            System.out.printf("Cost: %d; Condition: %s; Max speed: %d; Weight: %d; Horsepower: %d.%n", obj.getCost(), condition(obj.getCondition()), obj.getMaxSpeed(), obj.getWeight(), obj.getHorsepower());
        }
    }
}