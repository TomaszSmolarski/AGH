public class Square extends Figure implements Print
{
    public Square(double a){
    if(a<=0){throw new IllegalArgumentException("a musi byc >= 0: " + a);}
    this.a=a; calculatePerimeter(); calculateArea();}
    public double a,perimeter,area;
    @Override
    public double calculateArea(){return area=a*a;}
    @Override
    public double calculatePerimeter(){return perimeter=4*a;}
    @Override
    public void print(){System.out.println("kwadrat: Pole="+this.area+" Obwód="+this.perimeter+" bok="+this.a);}
}