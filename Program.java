import java.util.ArrayList;
import java.util.Scanner;

/**
 * Prevent chickens from dying
 */
class Program {
    ArrayList<Package> startIsland;
    ArrayList<Package> endIsland;

    String maryLocation;

    Program(int chickenCount, int foxCount, int grainCount) {
        this.maryLocation = "start";
        this.startIsland = new ArrayList<>();
        this.endIsland = new ArrayList<>();

        for (int i = 0; i < chickenCount; i++) {
            Chicken c = new Chicken();

            this.startIsland.add(c);
        }

        for (int i = 0; i < grainCount; i++) {
            Grain g = new Grain();

            this.startIsland.add(g);
        }

        for (int i = 0; i < foxCount; i++) {
            Fox f = new Fox();

            this.startIsland.add(f);
        }
    }

    void killChickens() {

    }

    void transfer(String type, ArrayList<Package> src, ArrayList<Package> dest) {
        int idx = -1;
        for (int i = 0; i < src.size(); i++) {
            Package p = src.get(i);

            if (type == "chicken") {
                if (p instanceof Chicken) {
                    idx = i;
                }
            } else if (type == "grain") {
                if (p instanceof Grain) {
                    idx = i;
                }
            } else {
                if (p instanceof Fox) {
                    idx = i;
                }
            }
        }

        if (idx == -1) {
            throw new RuntimeException("Package of type " + type + " is not found in src.");
        }

        Package p = src.remove(idx);
        dest.add(p);
    }

    void toggleLocation() {
        if (this.maryLocation == "start") {
            this.maryLocation = "end";
        } else {
            this.maryLocation = "start";
        }
    }

    void transfer(String type) {
        if (type == null) {
            this.toggleLocation();
            return;
        }

        ArrayList<Package> dest, src;
        if (this.maryLocation == "start") {
            src = this.startIsland;
            dest = this.endIsland;
        } else {
            src = this.endIsland;
            dest = this.startIsland;
        }

        transfer(type, src, dest);
        toggleLocation();
    }

    boolean checkKilled(String location) {
        if (this.maryLocation == location) {
            return false;
        }

        ArrayList<Package> island;
        if (location == "start") {
            island = this.startIsland;
        } else {
            island = this.endIsland;
        }

        boolean aChicken = false;
        boolean aFox = false;

        for (int i = 0; i < island.size(); i++) {
            Package p = island.get(i);

            if (p instanceof Chicken) {
                aChicken = true;
            }

            if (p instanceof Fox) {
                aFox = true;
            }

            if (aChicken && aFox) {
                return true;
            }
        }

        boolean aGrain = false;
        aChicken = false;

        for (int i = 0; i < island.size(); i++) {
            Package p = island.get(i);

            if (p instanceof Chicken) {
                aChicken = true;
            }

            if (p instanceof Grain) {
                aGrain = true;
            }

            if (aChicken && aGrain) {
                return true;
            }
        }

        return false;
    }

    static Scanner kbd = new Scanner(System.in);
    public static String prompt(String currentLocation) {
        System.out.println("Current location: " + currentLocation);
        System.out.println("Choose what to transfer (A: Chicken, B: Fox, C: Grain)");
        System.out.print(">>> ");
        String input = kbd.nextLine().trim();
        System.out.println();

        switch (input) {
            case "A": return "chicken";
            case "B": return "fox";
            case "C": return "grain";
            default: return prompt(currentLocation);
        }
    }

    public static void main(String[] args) {
        Program prog = new Program(1, 1, 1);

        System.out.println();
        System.out.println("-----------" + "Start");
        System.out.println("Killed in start " + prog.checkKilled("start"));
        System.out.println("Killed in end " + prog.checkKilled("end"));
        System.out.println("Start location: " + prog.startIsland);
        System.out.println("End location: " + prog.endIsland);

        while (!prog.checkKilled("start") && !prog.checkKilled("end")) {
            System.out.println("");
            String input = prompt(prog.maryLocation);
            System.out.println("Transferring a " + input);
            try {
                prog.transfer(input);

                System.out.println();
                System.out.println("-----------" + "After transfer");
                System.out.println("Killed in start " + prog.checkKilled("start"));
                System.out.println("Killed in end " + prog.checkKilled("end"));
                System.out.println("Start location: " + prog.startIsland);
                System.out.println("End location: " + prog.endIsland);
            } catch (Exception e) {
                System.out.println("Cannot move " + input);
            }
        }
    }
}

