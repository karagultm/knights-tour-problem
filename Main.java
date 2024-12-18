
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // inputları kullanıcıdan almamız gerekiyor.
        try (Scanner scanner = new Scanner(System.in)) { // try-with-resources

            System.out.println("\nEnter the size of the board: ");
            int size = scanner.nextInt();

            System.out.println("Which algorithm do you want to use?");
            System.out.println("a- Breadth First Search");
            System.out.println("b- Depth First Search");
            System.out.println("c- Depth First Search with Heuristic h1b");
            System.out.println("d- Depth First Search with Heuristic h2");
            String algorithm = scanner.next();

            if (!algorithm.equals("a") && !algorithm.equals("b") && !algorithm.equals("c") && !algorithm.equals("d")) {
                System.out.println("Invalid algorithm");
                System.exit(0);
            }
            System.out.println("Enter the time limit in minutes: ");
            int timeLimitMinutes = scanner.nextInt();
            long timeLimit = timeLimitMinutes * 60 * 1000;

            KnightsTour knightsTour = new KnightsTour(size);
            Node startNode = new Node(0, 0); //başlangıç konumu oluşturuldu
            
            System.out.println("Search Method: " + knightsTour.getAlgorithmName(algorithm));
            System.out.println("Board Size: " + knightsTour.getSize());

            TreeSearch treeSearch = new TreeSearch();
            treeSearch.treeSearch(knightsTour, startNode, algorithm, timeLimit);

        } catch (OutOfMemoryError e) {
            System.out.println("Out of Memory.");
        }
    }
}
