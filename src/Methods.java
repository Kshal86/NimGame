import java.util.Scanner;

public class Methods {
    private static Scanner sc = new Scanner(System.in);

    public static int[] setup() {
        int[] piles = new int[4];
        for (int i = 0; i < 4; i++) {
            piles[i] = (int) (Math.random() * 5) + 4;
        }
        return piles;
    }

    public static void Display(int[] P) {
        System.out.println("Pile 1 = " + P[0] + ", Pile 2 = " + P[1] + ", Pile 3 = " + P[2] + ", Pile 4 = " + P[3]);
    }

    public static void Player1turn(int[] P, int pile, int sticks) {
        while (pile < 1 || pile > 4 || P[pile - 1] == 0) {
            System.out.println("Invalid pile. Choose again: ");
            pile = sc.nextInt();
        }
        while (sticks < 1 || sticks > P[pile - 1]) {
            System.out.println("Invalid number of sticks. Choose again: ");
            sticks = sc.nextInt();
        }

        P[pile - 1] -= sticks;
    }

    public static void Player2turn(int[] P, int pile, int sticks) {
        while (pile < 1 || pile > 4 || P[pile - 1] == 0) {
            System.out.println("Invalid pile. Choose again: ");
            pile = sc.nextInt();
        }
        while (sticks < 1 || sticks > P[pile - 1]) {
            System.out.println("Invalid number of sticks. Choose again: ");
            sticks = sc.nextInt();
        }

        P[pile - 1] -= sticks;
    }

    public static boolean Check(int[] P) {
        for (int pile : P) {
            if (pile > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean Check(int P, int S) {
        return P >= S;
    }

    public static int RemoveNumber(int P, int S) {
        if (P >= S) {
            return P - S;
        } else {
            return -1;
        }
    }

    public static boolean DetermineLoser(int[] P) {
        int count = 0;
        for (int pile : P) {
            if (pile == 0) {
                count++;
            }
        }
        return count == 3;
    }
}