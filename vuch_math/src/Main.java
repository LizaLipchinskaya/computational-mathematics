import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите параметры");

        double a = Double.parseDouble(scanner.nextLine());
        double b = Double.parseDouble(scanner.nextLine());
        double h = Double.parseDouble(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        if (k != 4) {
            int j = 0;
            double prevY = 0.0;
            double prevX = 0.0;

            while (j * h + a <= b) {
                if (j == 0) {
                    prevX = a;
                    prevY = y(prevX);
                } else if (j > 0) {
                    prevY = formResult(j * h + a, prevY, new double[]{prevX}, h, k);
                    prevX = j * h + a;
                }
                System.out.printf(
                        "%d  %f  %f  %f  %f%n",
                        j,
                        h*j + a,
                        prevY,
                        y(j*h + a),
                        Math.abs(y(j*h + a) - prevY)
                );
                j++;
            }
        }

        if (k == 4) {
            int j = 0;
            LinkedList<Double> y = new LinkedList<>();
            y.addLast(y(j * h + a));
            y.addLast(y.get(0) + h / 12 * (8 * g(a) + 5 * g(a + h) - g(a - h)));

            while (j * h + a <= b) {
                if (j > 1) {
                    form4Result(y, j, h, a);
                }
                System.out.printf(
                        "%d  %f  %f  %f  %23.16f%n",
                        j,
                        j * h + a,
                        y.get(j),
                        y(j*h + a),
                        Math.abs(y.get(j) - y(j * h + a))
                );
                j++;
            }
        }
    }


    public static double g(double x) {
        return Math.cos(x) * Math.exp(x);
    }

    public static  double y(double x) { //точное решение
        return Math.exp(x) * (Math.cos(x) + Math.sin(x)) / 2 - 0.5;
    }

    public static double formResult(double x, double prevY, double[] prevX, double h, int k) {
        switch (k) {
            case 1 : {
                return (h * g(prevX[0]) + prevY);
            }

            case 2 : {
                return (h * (g(x) + g(prevX[0])) / 2 + prevY);
            }
        }
        throw new RuntimeException("Invalid k");
    }

    public static void form4Result(LinkedList<Double> prevY, int j, double h, double a) {
        prevY.addLast(prevY.get(j - 2) + (g(j * h + a) + 4 * g((j-1) * h + a) + g((j-2) * h + a)) * h / 3);
    }
}