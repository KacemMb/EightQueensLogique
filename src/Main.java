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
}//class position have at attributes row,col
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
}// class queen have as attributes position,id and a static variable "numberOfQueens"
class Matrix{
    private int[][] matrix;
    public Matrix(){
        this.matrix = new int[8][8];
        initialiseMatrix();
    }//constructor
    private void initialiseMatrix(){
        for (int i = 0;i < 8;i++){
            for (int j = 0;j < 8; j++){
                this.matrix[i][j] = 0;
            }
        }
    }//private methode used in the constructor that initialise every case in the matrix in 0
    public boolean isSafe(Position position){
        if (matrix[position.getRow()][position.getCol()] == 0){
            return true;
        }else {
            return false;
        }
    }//methode return true if the position in the attributes of the methode have the value 0
    private boolean isSafe(int row,int col){
        if (matrix[row][col] == 0){
            return true;
        }else{
            return false;
        }
    }//the same methode but in the parametre we have row and col as attributes
    public void setQueen(Queens queens){
        matrix[queens.getPosition().getRow()][queens.getPosition().getCol()] = 9;
        makeTopRightOnes(queens.getPosition());
        makeTopLeftOnes(queens.getPosition());
        makeBottomRightOnes(queens.getPosition());
        makeBottomLeftOnes(queens.getPosition());
        makeVerticalTargetOnes(queens.getPosition());
        makeHorizontalTargetOnes(queens.getPosition());
    }//method set the numbre 9 in a case and set numbre one in all cases within the target of the queen represented by 9
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
    public void showMatrix(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("    "+matrix[i][j]);
            }
            System.out.println(" ");
        }
    }// show the matrix
}
public class Main {
    public static void main(String[] args){
        Scanner read= new Scanner(System.in);
        Matrix matrix = new Matrix();
        System.out.println("welcom to my eight queens game !!");
        System.out.println("in the chessboard we habe 64 squar");
        System.out.println("you win if you put 8 queens that every queen can't kill the others");
        System.out.println("- number 0 means that the square is free");
        System.out.println("- number 1 means that the square within the target of a queen");
        System.out.println("- number 9 means that a queen placed in that square");
        System.out.println("have fun");
        Queens[] queens = new Queens[8];
        boolean test = true;
        int row;
        int col;
        do {
            System.out.println("enter the position of your queen .");
            System.out.print("row : ");
            row = read.nextInt();
            System.out.print("col : ");
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
            System.out.println("your queen is placed in a dangerous place !");
            test = false;
        }
        if (test){
            System.out.println("you can show the chessboard if you press Y");
            String c = read.nextLine();
            if (c.equals("y")){
                matrix.showMatrix();
            }
        }
        if (Queens.getNumberOfQueen() < 8 && test){
            System.out.println("you can now set the second queen");
        } else if (Queens.getNumberOfQueen() == 8 && test) {
            System.out.println("congratulation ! you win");
        }
    }
}