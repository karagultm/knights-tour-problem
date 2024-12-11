
public class Main {

    public static void main(String[] args) {

        int size = 5;
        //inputları kullanıcıdan almamız gerekiyor.

        KnightsTour knightsTour = new KnightsTour(size);
        knightsTour.printSolution();

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
    }

   
}
