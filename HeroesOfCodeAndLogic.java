import java.util.HashMap;
import java.util.Scanner;

public class HeroesOfCodeAndLogic {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        HashMap<String, Integer> hPointsMap = new HashMap<>();
        HashMap<String, Integer> mPointsMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = scan.nextLine().split("\\s+");
            String name = input[0];
            int hPoints = Integer.parseInt(input[1]);
            int mPoints = Integer.parseInt(input[2]);
            hPointsMap.put(name, hPoints);
            mPointsMap.put(name, mPoints);
        }

        String command = scan.nextLine();
        while (!"End".equals(command)) {
            String[] tokens = command.split(" - ");
            String name = tokens[1];

            switch (tokens[0]) {
                case "CastSpell":
                    int mpNeeded = Integer.parseInt(tokens[2]);
                    String spellName = tokens[3];
                    castSpell(mPointsMap, name, mpNeeded, spellName);
                    break;

                case "TakeDamage":
                    int hpDamage = Integer.parseInt(tokens[2]);
                    String attacker = tokens[3];
                    int leftHPoints = hPointsMap.get(name) - hpDamage;
                    if (leftHPoints > 0) {
                        System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n",
                                name, hpDamage, attacker, leftHPoints);
                        hPointsMap.put(name, leftHPoints);
                    } else {
                        System.out.printf("%s has been killed by %s!%n", name, attacker);
                        hPointsMap.remove(name);
                        mPointsMap.remove(name);
                    }
                    break;

                case "Recharge": {
                    int amount = Integer.parseInt(tokens[2]);
                    int newAmount = mPointsMap.get(name) + amount;
                    if (newAmount > 200) {
                        System.out.printf("%s recharged for %d MP!%n", name, 200 - mPointsMap.get(name));
                        mPointsMap.put(name, 200);
                    } else {
                        mPointsMap.put(name, newAmount);
                        System.out.printf("%s recharged for %d MP!%n", name, amount);
                    }
                }
                    break;
                case "Heal":
                    int amount = Integer.parseInt(tokens[2]);
                    int newAmount = hPointsMap.get(name) + amount;
                    if (newAmount > 100) {
                        System.out.println(String.format("%s healed for %d HP!", name, 100 - hPointsMap.get(name) ));
                        hPointsMap.put(name, 100);

                    } else {
                        hPointsMap.put(name, newAmount);
                        System.out.println(String.format("%s healed for %d HP!", name, amount));
                    }
                    break;
            }

            command = scan.nextLine();
        }

        hPointsMap
                .entrySet()
                .stream()
                .sorted((p1,p2)->{
                    int result = p2.getValue().compareTo(p1.getValue());
                    if (result==0){
                        result = p1.getKey().compareTo(p2.getKey());
                        return result;
                    }
                    return result;
                })
                .forEach(h->{
                    System.out.println(h.getKey());
                    System.out.println(String.format("  HP: %d", h.getValue()));
                    System.out.println(String.format("  MP: %d", mPointsMap.get(h.getKey())));

                });
    }

    private static void castSpell(HashMap<String, Integer> mPointsMap, String name, int mpNeeded, String spellName) {
        if (mpNeeded <= mPointsMap.get(name)) {
            System.out.println(String.format(
                    "%s has successfully cast %s and now has %d MP!",
                    name, spellName, mPointsMap.get(name) - mpNeeded));
            mPointsMap.put(name,mPointsMap.get(name) - mpNeeded);
        } else {
            System.out.printf("%s does not have enough MP to cast %s!%n", name, spellName);
        }
    }
}
