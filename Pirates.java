import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Pirates {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        HashMap<String, Integer> population = new HashMap<>();
        TreeMap<String, Integer> gold = new TreeMap<>();

        String targets = scan.nextLine();
        while (!"Sail".equals(targets)) {
            String[] tokens = targets.trim().split("\\|\\|");
            String city = tokens[0];
            int populationCity = Integer.parseInt(tokens[1]);
            int goldCity = Integer.parseInt(tokens[2]);

            population.putIfAbsent(city, 0);
            int currentPopulation = population.get(city);
            population.put(city, currentPopulation + populationCity);

            gold.putIfAbsent(city, 0);
            int currentGold = gold.get(city);
            gold.put(city, currentGold + goldCity);

            targets = scan.nextLine();
        }

        String events = scan.nextLine();
        while (!"End".equals(events)) {
            String[] tokens = events.trim().split("=>");
            switch (tokens[0]) {
                case "Plunder": {
                    String town = tokens[1];
                    int people = Integer.parseInt(tokens[2]);
                    int goldPlunder = Integer.parseInt(tokens[3]);

                    System.out.println(String.format("%s plundered! %d gold stolen, %d citizens killed.",
                            town, goldPlunder, people));

                    int currentPopulation = population.get(town) - people;
                    int currentGold = gold.get(town) - goldPlunder;
                    population.put(town, currentPopulation);
                    gold.put(town, currentGold);
                    if (currentGold <= 0 || currentPopulation <= 0) {
                        System.out.printf("%s has been wiped off the map!%n", town);
                        population.remove(town);
                        gold.remove(town);
                    }
                }
                    break;

                case "Prosper":
                    String town = tokens[1];
                    int goldIncrease = Integer.parseInt(tokens[2]);

                    if (goldIncrease < 0){
                        System.out.println("Gold added cannot be a negative number!");
                    } else if(goldIncrease > 0) {
                        int totalGold = gold.get(town) + goldIncrease;
                        System.out.printf("%d gold added to the city treasury. " +
                                "%s now has %d gold.%n", goldIncrease, town, totalGold );
                        gold.put(town,totalGold);
                    }
                    break;
            }

            events = scan.nextLine();
        }

        if (gold.size() > 0) {
            System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:%n", gold.size());

            gold
                    .entrySet()
                    .stream()
                    .sorted((t1, t2) -> {
                        return t2.getValue().compareTo(t1.getValue());
                    })
                    .forEach(t -> {
                        String town = t.getKey();
                        int citizens = population.get(town);
                        System.out.printf("%s -> Population: %d citizens, Gold: %d kg%n",
                                t.getKey(), citizens, t.getValue());
                    });

        } else {
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");
        }
    }
}
