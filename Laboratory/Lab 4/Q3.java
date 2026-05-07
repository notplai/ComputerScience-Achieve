import java.util.Random;

class Mtx {
    private final int[][] matrix;
    private final int rows;
    private final int cols;

     Mtx(int[][] matrix2D) {
        this.matrix = matrix2D;
        this.rows = matrix2D.length;
        this.cols = matrix2D[0].length;
    }

     boolean equalSize(Mtx other) {
        return this.rows == other.rows && this.cols == other.cols;
    }

     boolean compatible(Mtx other) {
        return this.cols == other.rows;
    }

     Mtx add(Mtx other) {
        if (!this.equalSize(other)) {
            return null;
        }
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = this.matrix[i][j] + other.matrix[i][j];
            }
        }
        return new Mtx(result);
    }

     Mtx mul(Mtx other) {
        if (!this.compatible(other)) {
            return null;
        }
        int[][] result = new int[this.rows][other.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                result[i][j] = 0;
                for (int k = 0; k < this.cols; k++) {
                    result[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        return new Mtx(result);
    }

     Mtx mul(int n) {
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = this.matrix[i][j] * n;
            }
        }
        return new Mtx(result);
    }

     void print(String name) {
        System.out.println(name + " =");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Q3 {
     static void main(String[] args) {
        Random rand = new Random();

        int[][] arrA = new int[10][5];
        int[][] arrB = new int[10][5];
        int[][] arrC = new int[5][3];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                arrA[i][j] = rand.nextInt(201) - 100;
                arrB[i][j] = rand.nextInt(201) - 100;
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                arrC[i][j] = rand.nextInt(201) - 100;
            }
        }

        Mtx A = new Mtx(arrA);
        Mtx B = new Mtx(arrB);
        Mtx C = new Mtx(arrC);

        A.print("A");
        B.print("B");
        C.print("C");

        Mtx aplusb = A.add(B);
        if (aplusb != null) {
            aplusb.print("A+B");
        } else {
            System.out.println("A and B cannot be added\n");
        }

        Mtx twob = B.mul(2);
        twob.print("2*B");

        Mtx atimesb = A.mul(B);
        if (atimesb != null) {
            atimesb.print("A*B");
        } else {
            System.out.println("A and B are not compatible\n");
        }

        Mtx atimesc = A.mul(C);
        if (atimesc != null) {
            atimesc.print("A*C");
        } else {
            System.out.println("A and C are not compatible\n");
        }
    }
}
