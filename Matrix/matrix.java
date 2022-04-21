import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MATRIX{
    private final int[][] arr;

    public MATRIX(int[][] arr) {
        this.arr = arr;
    }

    private MATRIX(int row, int col, int min, int max) {
        this.arr = GenMaTrix(row, col, min, max);
    }

    private int[][] GenMaTrix(int row, int col, int min, int max) {
        int[][] res = new int[row][col];
        List<Integer> flat = new ArrayList<>();
        int total = row*col;
        Random r = new Random();
        for (int i = 0; i < total; i++) {
            int result = r.nextInt(max-min) + min;
            flat.add(result);
        }
        Collections.shuffle(flat);
        for (int i = 0; i < flat.size(); i++)
            res[i/col][i%col] = flat.get(i);
        return res;
    }

    private int KiemTraTongMax(){
        int totalR = TongDong(MaxDong());
        int totalC = TongCot(MaxCot());
        int totalD1 = arr.length == arr[0].length ? TongCheoChinh() : 0;
        int totalD2 = arr.length == arr[0].length ? TongCheoPhu() : 0;

        if (totalR > totalC && totalR > totalD1 && totalR > totalD2) return 1;
        if (totalC > totalR && totalC > totalD1 && totalC > totalD2) return 2;
        if (totalD1 > totalD2) return 3;
        return 4;
    }

    private int MaxDong(){
        int max = 0;
        int row = 0;
        for (int rowStep = 0; rowStep < arr.length; rowStep++){
            int totalRow = TongDong(rowStep);
            if (totalRow > max) {
                row = rowStep;
                max = totalRow;
            }
        }
        return row;
    }

    private int MaxCot(){
        int max = 0;
        int col = 0;
        for (int colStep = 0; colStep < arr[0].length; colStep++){
            int totalCol = TongCot(colStep);
            if (totalCol > max) {
                max = totalCol;
                col = colStep;
            }
        }
        return col;
    }

    private int TongDong(int row){
        int total = 0;
        for (int colStep = 0; colStep < arr[0].length; colStep++){
            total += arr[row][colStep];
        }
        return total;
    }

    private int TongCot(int col){
        int total = 0;
        for (int rowStep = 0; rowStep < arr.length; rowStep++){
            total += arr[rowStep][col];
        }
        return total;
    }

    private int TongCheoChinh(){
        int total = 0;
        for (int i = 0; i < arr.length; i++){
            total += arr[i][i];
        }
        return total;
    }

    private int TongCheoPhu(){
        int total = 0;
        for (int i = 0; i < arr.length; i++){
            total += arr[i][arr.length - 1 - i];
        }
        return total;
    }

    private void HienThiMang(){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }
    }

    private void HienThiMax(){
        int check = KiemTraTongMax();
        System.out.println("Chuoi Ket Qua Co Tong Lon Nhat La: ");
        if (check == 1){
            for (int i = 0; i < arr[0].length; i++) {
                System.out.print(arr[MaxDong()][i] + ",");
            }
        }
        else if (check == 2){
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i][MaxCot()] + ",");
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
        MATRIX test = new MATRIX(5, 5, -2, 8);
        test.HienThiMang();
        test.HienThiMax();
    }
}