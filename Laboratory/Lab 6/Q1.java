public class Q1 {
    public static void main(String[] args) {
        Taylor[] taylors = new Taylor[3];
        taylors[0] = new Expo(7, 1);
        taylors[1] = new Sine(7, Math.PI / 4);
        taylors[2] = new Cosine(7, 1);

        for (Taylor t : taylors) {
            t.printValue();
        }
    }
}

abstract class Taylor {
    //instance variable
    private int k;
    private double x;

    //constructor
    Taylor(int k, double x) { this.k = k; this.x = x; }

    //method ว่าง
    public abstract void printValue();
    public abstract double getApprox();

    //คำนวน n!
    public int factorial(int n) {
        long fact = 1;

        for (int i = 1; i <= n; i++) { fact *= i; }
        return (int) fact;
    }

    //เพื่อคืนค่า k
    public int getIteration() { return this.k; }

    //เพื่อคืนค่า x
    public double getValue() { return this.x; }
}

class Expo extends Taylor { Expo(int k, double x) { super(k, x); }

    //ปริ้นค่าเปลียบแทบ Math.exp กับ Approx ที่เราสร้างมาเอง
    public void printValue(){
        System.out.println("\nValue from Math.exp() is " + Math.exp(getValue()));
        System.out.println("Approximated value is " + getApprox() + "\n");
    }

    //Approx ที่เราสร้างมาเอง
    public double getApprox(){
        double sum = 0.0;

        //สูตร exp
        for (int n = 0; n <= super.getIteration(); n++)
        { sum += Math.pow(getValue(), n) / factorial(n); }
        return sum;
    }
}

class Sine extends Taylor { Sine(int k, double x) { super(k, x); }

    //ปริ้นค่าเปลียบแทบ Math.sin กับ Approx ที่เราสร้างมาเอง
    public void printValue(){
        System.out.println("Value from Math.sine() is " + Math.sin(getValue()));
        System.out.println("Approximated value is " + getApprox() + "\n");
    }

    //Approx ที่เราสร้างมาเอง
    public double getApprox(){
        double sum = 0.0;

        //สูตร sin
        for (int n = 0; n <= getIteration(); n++) { sum += Math.pow(-1, n) * Math.pow(getValue(), 2*n+1) / factorial(2*n+1); }
        return sum;
    }
}

class Cosine extends Taylor { Cosine(int k, double x) { super(k, x); }


    //ปริ้นค่าเปลียบแทบ Math.cos กับ Approx ที่เราสร้างมาเอง
    public void printValue(){
        System.out.println("Value from Math.cos() is " + Math.cos(getValue()));
        System.out.println("Approximated value is " + getApprox() + "\n");
    }

    //Approx ที่เราสร้างมาเอง
    public double getApprox(){
        double sum = 0.0;

        //สูตร cos
        for (int n = 0; n <= getIteration(); n++) {
            sum += Math.pow(-1, n) * Math.pow(getValue(), 2*n) / factorial(2*n); }
        return sum;
    }
}