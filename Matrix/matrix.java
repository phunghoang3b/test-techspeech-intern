public class Test {

    private final int[][] arr;

    private Test(int[][] arr) {
        this.arr = arr;
    }

    private Test(int row, int col, int min, int max) {
        this.arr = gen(row, col, min, max);
    }

    private int[][] gen(int row, int col, int min, int max) {
        int[][] res = new int[row][col];
        List<Integer> flat = new ArrayList<>();
        int total = row*col;
        for (int i = 0; i < total; i++) {
            flat.add(MathUtils.random(min, max));
        }
        Collections.shuffle(flat);
        for (int i = 0; i < flat.size(); i++)
            res[i/col][i%col] = flat.get(i);
        return res;
    }

    private int maxTotalCheck(){
        int totalR = totalRow(maxRow());
        int totalC = totalCol(maxCol());
        int totalD1 = arr.length == arr[0].length ? totalCheoChinh() : 0;
        int totalD2 = arr.length == arr[0].length ? totalCheoPhu() : 0;

        if (totalR > totalC && totalR > totalD1 && totalR > totalD2) return 1;
        if (totalC > totalR && totalC > totalD1 && totalC > totalD2) return 2;
        if (totalD1 > totalD2) return 3;
        return 4;
    }

    private int maxRow(){
        int max = 0;
        int row = 0;
        for (int rowStep = 0; rowStep < arr.length; rowStep++){
            int totalRow = totalRow(rowStep);
            if (totalRow > max) {
                row = rowStep;
                max = totalRow;
            }
        }
        return row;
    }

    private int maxCol(){
        int max = 0;
        int col = 0;
        for (int colStep = 0; colStep < arr[0].length; colStep++){
            int totalCol = totalCol(colStep);
            if (totalCol > max) {
                max = totalCol;
                col = colStep;
            }
        }
        return col;
    }

    private int totalCheoChinh(){
        int total = 0;
        for (int i = 0; i < arr.length; i++){
            total += arr[i][i];
        }
        return total;
    }

    private int totalCheoPhu(){
        int total = 0;
        for (int i = 0; i < arr.length; i++){
            total += arr[i][arr.length - 1 - i];
        }
        return total;
    }

    private int totalRow(int row){
        int total = 0;
        for (int colStep = 0; colStep < arr[0].length; colStep++){
            total += arr[row][colStep];
        }
        return total;
    }

    private int totalCol(int col){
        int total = 0;
        for (int rowStep = 0; rowStep < arr.length; rowStep++){
            total += arr[rowStep][col];
        }
        return total;
    }

    private void showArr(){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }
    }

    private void showMax(){
        int check = maxTotalCheck();
        System.out.println("Chuoi lon nhat la: ");
        if (check == 1){
            for (int i = 0; i < arr[0].length; i++) {
                System.out.print(arr[maxRow()][i] + ",");
            }
        }
        else if (check == 2){
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i][maxCol()] + ",");
            }
        }else if (check == 3){
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i][i] + ",");
            }
        }else {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i][arr.length - 1 - i] + ",");
            }
        }
    }

    public static void main(String[] args) {
        Test test = new Test(4, 5, 0, 5);
        test.showArr();
        test.showMax();
    }
}