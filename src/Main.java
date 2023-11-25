import java.util.Scanner;

class Position{
    private int row;
    private int col;

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    public Position(int row,int col){
        this.row = row;
        this.col = col;
    }
}
class Queens{
    private int id;
    private Position position;
    private static int numberOfQueen;
    public Queens(Position position,int id){
        this.position = position;
        this.id = id;
        numberOfQueen++;
    }

    public Position getPosition() {
        return position;
    }

    public static int getNumberOfQueen() {
        return numberOfQueen;
    }
}
class Matrix{
    private int[][] matrix;
    public Matrix(){
        this.matrix = new int[8][8];
        initialiseMatrix();
    }
    private void initialiseMatrix(){
        for (int i = 0;i < 8;i++){
            for (int j = 0;j < 8; j++){
                this.matrix[i][j] = 0;
            }
        }
    }
    public boolean isSafe(Position position){
        if (matrix[position.getRow()][position.getCol()] == 0){
            return true;
        }else {
            return false;
        }
    }
    private boolean isSafe(int row,int col){
        if (matrix[row][col] == 0){
            return true;
        }else{
            return false;
        }
    }
    public void setQueen(Queens queens){
        matrix[queens.getPosition().getRow()][queens.getPosition().getCol()] = 9;
        makeTopRightOnes(queens.getPosition());
        makeTopLeftOnes(queens.getPosition());
        makeBottomRightOnes(queens.getPosition());
        makeBottomLeftOnes(queens.getPosition());
        makeVerticalTargetOnes(queens.getPosition());
        makeHorizontalTargetOnes(queens.getPosition());
    }
    private void makeTopRightOnes(Position position){
        int i = position.getRow();
        int j = position.getCol();
        while (i>0 && j<7){
            i--;
            j++;
            if (isSafe(i, j)){
                matrix[i][j]=1;
            }
        }
    }
    private void makeTopLeftOnes(Position position){
        int i = position.getRow();
        int j = position.getCol();
        while (i>0 && j>0){
            i--;
            j--;
            if (isSafe(i, j)){
                matrix[i][j]=1;
            }
        }
    }
    private void makeBottomRightOnes(Position position){
        int i = position.getRow();
        int j = position.getCol();
        while (i<7 && j<7){
            i++;
            j++;
            if (isSafe(i, j)){
                matrix[i][j]=1;
            }
        }
    }
    private void makeBottomLeftOnes(Position position){
        int i = position.getRow();
        int j = position.getCol();
        while (i<7 && j>0){
            i++;
            j--;
            if (isSafe(i, j)){
                matrix[i][j]=1;
            }
        }
    }
    private void makeVerticalTargetOnes(Position position){
        int queenPositionRow = position.getRow();
        int j = position.getCol();
        for (int i = 0;i<8;i++){
            if (i != queenPositionRow){
                if (isSafe(i, j)){
                    matrix[i][j]=1;
                }
            }
        }
    }
    private void makeHorizontalTargetOnes(Position position){
        int i = position.getRow();
        int queenPositionCol = position.getCol();
        for (int j = 0; j < 8; j++) {
            if (j != queenPositionCol){
                if (isSafe(i, j)){
                    matrix[i][j]=1;
                }
            }
        }
    }
    public void showMatrix() {
        System.out.println("Chessboard Matrix:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }// show the matrix
}
public class Main {
    public static void main(String[] args){
        Scanner read= new Scanner(System.in);
        Matrix matrix = new Matrix();
        System.out.println("Welcome to my Eight Queens Game!!");
        System.out.println("In the chessboard, we have 64 squares.");
        System.out.println("You win if you put 8 queens, and each queen cannot attack the others.");
        System.out.println("- Number 0 means that the square is free.");
        System.out.println("- Number 1 means that the square is within the attack range of a queen.");
        System.out.println("- Number 9 means that a queen is placed in that square.");
        System.out.println("Have fun!");
        Queens[] queens = new Queens[8];
        boolean test = true;
        do {
            int row;
            int col;
            do {
                System.out.println("Enter the position of your queen:");
                System.out.print("Row (0-7): ");
                row = read.nextInt();
                System.out.print("Column (0-7): ");
                col = read.nextInt();
            }while (row > 7 || row < 0 || col > 7 || col < 0);
            Position position = new Position(row,col);
            int idOfQueens = Queens.getNumberOfQueen();
            queens[idOfQueens] = new Queens(position,idOfQueens+1);
            if (matrix.isSafe(queens[idOfQueens].getPosition())){
                System.out.println("your queen number "+(idOfQueens + 1)+" is placed in the chessboard !");
                matrix.setQueen(queens[idOfQueens]);
            }else {
                System.out.println("Game Over !");
                System.out.println("Your queen is placed in a dangerous place!");
                test = false;
            }
            if (test){
                System.out.println("you can show the chessboard if you press Y");
                read.nextLine();
                String c = read.nextLine();
                if (c.equals("y")){
                    matrix.showMatrix();
                }
            }
            if (Queens.getNumberOfQueen() < 8 && test){
                System.out.println("score = "+Queens.getNumberOfQueen());
                System.out.println("you can now set the second queen");
            } else if (Queens.getNumberOfQueen() == 8 && test) {
                System.out.println("congratulation ! you win");
            }
        }while (Queens.getNumberOfQueen()<8 && test == true);
        System.out.println("this is the matrix : ");
        matrix.showMatrix();
    }
}