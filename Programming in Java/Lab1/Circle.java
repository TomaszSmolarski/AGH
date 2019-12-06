public class Circle extends Figure implements Print
{
    public Circle(double r){
    if(r<=0){throw new IllegalArgumentException("r musi byc >= 0: " + r);}
    this.r=r; calculatePerimeter(); calculateArea();}
    public double r,perimeter,area;
    @Override
    public double calculateArea() {return area=Math.PI*r*r;}
    @Override
    public double calculatePerimeter(){return perimeter=2*Math.PI*r;}
    @Override
    public void print(){System.out.println("koło: Pole="+this.area+" Obwód="+this.perimeter+" promień="+this.r);}
}
