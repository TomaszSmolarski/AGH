public class Triangle extends Figure implements Print {
    Triangle(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Conajmniej jeden parametr jest ujemny");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new ArithmeticException("Z tych boków nie można zrobić trójkąta");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        calculatePerimeter();
        calculateArea();
    }

    public double a, b, c, perimeter, area;  //a,b,c - boki

    @Override
    public double calculateArea() {
        double p = this.calculatePerimeter() / 2;
        area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return area;
    }

    @Override
    public double calculatePerimeter() {
        return perimeter = a + b + c;
    }

    @Override
    public void print() {
        System.out.println("trójkąt: Pole=" + this.area + " Obwód=" + this.perimeter + " boki: a=" + this.a + " b=" + this.b + " c=" + this.c);
    }
}