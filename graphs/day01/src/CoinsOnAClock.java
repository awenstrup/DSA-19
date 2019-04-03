import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.HashSet;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // TODO
        List<char[]> result = new ArrayList<>();
        char[] coins = new char[hoursInDay];
        coinHelper(0, coins, pennies, nickels, dimes, result, hoursInDay);
        return result;
    }

    public static void coinHelper(Integer position, char[] placedCoins, int pennies, int nickels, int dimes, List<char[]> solutions, int hoursPerDay) {
            //try penny
            if (pennies >= 1) {
                placedCoins[position] = 'p';

                if (isFull(placedCoins)) {
                    System.out.println("Valid Solution!");
                    printClock(placedCoins, hoursPerDay);
                    solutions.add(Arrays.copyOf(placedCoins, placedCoins.length));
                }

                //System.out.println("Coins left: " + (pennies - 1 + nickels + dimes));
                //printClock(placedCoins, hoursPerDay);
                int next = moveRight(position, 1, hoursPerDay);
                if (!(placedCoins[next] == 'p' || placedCoins[next] == 'n' || placedCoins[next] == 'd')) {
                    coinHelper(next, placedCoins, pennies - 1, nickels, dimes, solutions, hoursPerDay);
                }
                placedCoins[position] = '.';
            }

            //try nickel
            if (nickels >= 1) {
                placedCoins[position] = 'n';

                if (isFull(placedCoins)) {
                    System.out.println("Valid Solution!");
                    printClock(placedCoins, hoursPerDay);
                    solutions.add(Arrays.copyOf(placedCoins, placedCoins.length));
                }

                //System.out.println("Coins left: " + (pennies + nickels - 1 + dimes));
                //printClock(placedCoins, hoursPerDay);
                int next = moveRight(position, 5, hoursPerDay);
                if (!(placedCoins[next] == 'p' || placedCoins[next] == 'n' || placedCoins[next] == 'd'))
                    coinHelper(next, placedCoins, pennies, nickels - 1, dimes, solutions, hoursPerDay);
                placedCoins[position] = '.';
            }

            //try dime
            if (dimes >= 1) {
                placedCoins[position] = 'd';

                if (isFull(placedCoins)) {
                    System.out.println("Valid Solution!");
                    printClock(placedCoins, hoursPerDay);
                    solutions.add(Arrays.copyOf(placedCoins, placedCoins.length));
                }

                //System.out.println("Coins left: " + (pennies + nickels + dimes - 1));
                //printClock(placedCoins, hoursPerDay);
                int next = moveRight(position, 10, hoursPerDay);
                if (!(placedCoins[next] == 'p' || placedCoins[next] == 'n' || placedCoins[next] == 'd'))
                    coinHelper(next, placedCoins, pennies, nickels, dimes - 1, solutions, hoursPerDay);
                placedCoins[position] = '.';
            }
    }


    public static int moveRight(int pos, int moves, int hoursPerDay) {
        int out = pos;
        for (int i = 0; i < moves; i++) {
            out++;
            if (out == hoursPerDay)
                out = 0;
        }
        return out;
    }

    public static void printClock(char[] coins, int hoursPerDay) {
        System.out.print("Output Array: ");
        System.out.println(Arrays.toString(coins));
        System.out.print("Time on clock: ");
        for (int i = 0; i < hoursPerDay; i++)
            System.out.print(i + ", ");
        System.out.println();
        System.out.println("---------------");
    }

    public static boolean isFull(char[] coins) {
        for (char c : coins) {
            if (!(c == 'd' || c == 'n' || c == 'p'))
                return false;
        }
        return true;
    }
}




/*
for (int c = 0; c < current[0].length; c++) {
                int tempR = r;
                int tempC = c;
                current[r][c] = 'Q';
                //printBoard(current);

                if (validQueenPlacement(current, r, c))
                    nQueensHelper(r + 1, current, solutions);
                else {
                    current[r][c] = '.';
                }

                current[tempR][tempC] = '.';
            }

    */