public class Main {
    public static void main(String[] args) {

        final int size = 4;
        double[] result = new double[size];
        double[] previousValue = new double[size];
        double accuracy = 0.0001;
        int iteration = 0;
        double[][] matrix = {
                {9.89, 2.45, 3.35, 2.28, 10.98},
                {1.5, 6.53, -0.3, -0.21, -1.83},
                {2.25, 1.32, 5.08, 0.49, 6.97},
                {30.07, -0.35, 1.05, 38.54, -3.56}
        };

        System.out.println("Матриця:");
        printMatrix(matrix);
        for (int i = 0; i < size; i++)
            result[i] = 1;

        while (!conditionOfEnd(result, previousValue, accuracy)) {
            System.arraycopy(result, 0, previousValue, 0, size);
            for (int i = 0; i < matrix.length; i++) {
                double temp = 0;
                for (int j = 0; j < result.length; j++) {
                    if (j != i)
                        temp = temp + (matrix[i][j] * result[j]);
                }
                result[i] = (matrix[i][matrix[i].length-1] - temp) / matrix[i][i];
            }
            iteration++;
            if (iteration <= 3 || conditionOfEnd(result, previousValue, accuracy)) {
                System.out.println("\n"  +iteration + " Ітерація: " );
                for (int i = 0; i < size; i++)
                    System.out.printf("x%d = %.5f\n", (i+1), result[i]);
                double[] r = calculateNeviazka(matrix, result);
                System.out.println("Вектор нев'язки:" );
                printMatrix(r);
            }
        }
    }
    public static boolean conditionOfEnd(double[] result, double[] previousValue, double accuracy) {
        double temp = 0;
        for (int i = 0; i < result.length; i++)
            temp += Math.abs(result[i] - previousValue[i]);
        return temp < accuracy;
    }
    public static void printMatrix(double[][] matrix) {
        for (double[] doubles : matrix) {
            for (int j = 0; j < doubles.length; j++) {
                if (j == 0)
                    System.out.printf("%7.4f", doubles[j]);
                else
                    System.out.printf("%11.4f", doubles[j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printMatrix(double[] matrix) {
        for (double v : matrix) {
            System.out.printf("%.6f ", v);
        }
        System.out.println();
    }

    public static double[] calculateNeviazka(double[][] matrix, double[] x) {
        int n = x.length;
        double[] r = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum = sum + matrix[i][j] * x[j];
            }
            r[i] = sum;
        }
        for (int i = 0; i < n; i++) {
            r[i] = matrix[i][n] - r[i];
        }
        return r;
    }
}
