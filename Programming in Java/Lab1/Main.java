import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        do{
            System.out.println("Jaka figura? (0=trójkąt, 1=kwadrat, 2=koło, 3=koniec programu)");
            Scanner scan = new Scanner(System.in);

            try {
                int i=scan.nextInt();
            switch (i) {
                case 0:     {
                    System.out.println("podaj wartości boków trójkąta:");
                    System.out.print("a=");
                    double a=scan.nextDouble();
                    System.out.print("b=");
                    double b = scan.nextDouble();
                    System.out.print("c=");
                    double c = scan.nextDouble();
                    Triangle tr = new Triangle(a, b, c);
                    tr.print();
                    break;
                            }
                case 1: {
                    System.out.println("podaj wartość boku kwadratu:");
                    System.out.print("a=");
                    double a = scan.nextDouble();
                    Square kw = new Square(a);
                    kw.print();
                    break;
                        }
                case 2:     {
                    System.out.println("podaj wartość promienia koła:");
                    System.out.print("r=");
                    double r = scan.nextDouble();

                    Circle ko = new Circle(r);
                    ko.print();
                    break;
                            }
                case 3: return;
                default:
                    System.out.println("Podano złą liczbę");
            }
            }catch(Exception v) { System.out.println(v); }
        }while (true);
    }
}

