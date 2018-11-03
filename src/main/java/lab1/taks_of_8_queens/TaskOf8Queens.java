package lab1.taks_of_8_queens;

public class TaskOf8Queens {
    private int[][] board;
    private int size = 8;

    public TaskOf8Queens(){
        board = new int[size][size];
    }

    private void setQueen(int i, int j){
        for(int x = 0; x < size; x++){
            board[x][j]++;//гор
            board[i][x]++;//верт
            int foo;
            foo = j - i + x;
            if(foo >= 0 && foo < size){
                board[x][foo]++;
            }
            foo = j + i - x;
            if(foo >= 0 && foo < size){
                board[x][foo]++;
            }
        }
        board[i][j] = -1;
    }

    private void resetQueen(int i, int j){
        for(int x = 0; x < size; x++){
            board[x][j]--;//гор
            board[i][x]--;//верт
            int foo;
            foo = j - i + x;
            if(foo >= 0 && foo < size){
                board[x][foo]--;
            }
            foo = j + i - x;
            if(foo >= 0 && foo < size){
                board[x][foo]--;
            }
        }
        board[i][j] = 0;
    }

    //попытка поставить ферзя на 8 позиций (строк) i-того столбца
    private boolean tryQueen(int i){
        boolean result = false;
        for(int j = 0; j < size; j++){
            if(board[i][j] == 0){
                //никто не бьет
                setQueen(i, j);
                if(i == size - 1){
                    //поставили всех королев
                    result = true;
                }
                else{
                    result = tryQueen(i + 1);//попытка поставить на след столбец
                    if(!result){
                        resetQueen(i, j);
                    }
                }
            }
            if(result)
                break;
        }
        return result;
    }

    public void solveAndPrint(){
        tryQueen(0);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(board[i][j] == -1){
                    System.out.print("*");
                }
                else
                    System.out.print(".");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TaskOf8Queens taskOf8Queens = new TaskOf8Queens();
        taskOf8Queens.solveAndPrint();
    }
}
