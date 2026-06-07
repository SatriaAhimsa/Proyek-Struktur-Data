import java.util.*;

public class matrix {

    static Scanner sc = new Scanner(System.in);

    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void sortRowWise(int[][] matrix) {
        for (int[] row : matrix) {
            Arrays.sort(row);
        }
    }

    static void sortColumnWise(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int col = 0; col < cols; col++) {

            int[] temp = new int[rows];

            for (int row = 0; row < rows; row++) {
                temp[row] = matrix[row][col];
            }

            Arrays.sort(temp);

            for (int row = 0; row < rows; row++) {
                matrix[row][col] = temp[row];
            }
        }
    }

    static int[][] rotate90(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][rows - 1 - i] = matrix[i][j];
            }
        }

        return result;
    }

    static int[][] rotate180(int[][] matrix) {
        return rotate90(rotate90(matrix));
    }

    static void rowTraversal(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
        }
        System.out.println();
    }

    static void columnTraversal(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
    }

    static void spiralPrint(int[][] matrix) {

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            for (int i = left; i <= right; i++)
                System.out.print(matrix[top][i] + " ");
            top++;

            for (int i = top; i <= bottom; i++)
                System.out.print(matrix[i][right] + " ");
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    System.out.print(matrix[bottom][i] + " ");
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    System.out.print(matrix[i][left] + " ");
                left++;
            }
        }

        System.out.println();
    }

    static int[][] transpose(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.print("Jumlah baris: ");
        int rows = sc.nextInt();

        System.out.print("Jumlah kolom: ");
        int cols = sc.nextInt();

        int[][] matrix = new int[rows][cols];

        System.out.println("Input matrix:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        while (true) {

            System.out.println("\nMENU");
            System.out.println("1a. Sort row-wise");
            System.out.println("1b. Sort column-wise");
            System.out.println("2c. Rotate 90");
            System.out.println("2d. Rotate 180");
            System.out.println("3a. Row-wise traversal");
            System.out.println("3b. Column-wise traversal");
            System.out.println("4. Spiral form");
            System.out.println("5. Transpose");
            System.out.println("6. Quit");

            System.out.print("Pilih menu: ");
            String choice = sc.next();

            switch (choice) {

                case "1a":
                    sortRowWise(matrix);
                    printMatrix(matrix);
                    break;

                case "1b":
                    sortColumnWise(matrix);
                    printMatrix(matrix);
                    break;

                case "2c":
                    matrix = rotate90(matrix);
                    printMatrix(matrix);
                    break;

                case "2d":
                    matrix = rotate180(matrix);
                    printMatrix(matrix);
                    break;

                case "3a":
                    rowTraversal(matrix);
                    break;

                case "3b":
                    columnTraversal(matrix);
                    break;

                case "4":
                    spiralPrint(matrix);
                    break;

                case "5":
                    matrix = transpose(matrix);
                    printMatrix(matrix);
                    break;

                case "6":
                    System.out.println("Program selesai");
                    return;

                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }
}