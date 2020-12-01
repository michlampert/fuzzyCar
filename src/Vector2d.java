package src;

public class Vector2d {

    public static double epsilon = 1e-6;

    public final double x;
    public final double y;
    
    public Vector2d(double x, double y){
        if(x < 1e-6 && x > -1e-6){ x = 0; }
        if(y < 1e-6 && y > -1e-6){ y = 0; }
        this.x = x;
        this.y = y;
    }

    public Vector2d(){
        this(0,0);
    }

    public Vector2d sum(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d sub(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }
    
    public Vector2d mul(double num){
        return new Vector2d(this.x * num, this.y * num);
    }

    public Vector2d div(double num){
        return new Vector2d(this.x / num, this.y / num);
    }

    public Vector2d neg(){
        return new Vector2d(-this.x, -this.y);
    }

    public double len(){
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2d unit(){
        return new Vector2d(this.x / this.len(), this.y / this.len());
    }

    public double scalar(Vector2d other){
        return this.x * other.x + this.y * other.y;
    }

    public Vector2d transverse(){
        Vector2d v;
        if(this.x < Vector2d.epsilon && this.x > -Vector2d.epsilon && this.y < Vector2d.epsilon && this.y > -Vector2d.epsilon){
            return new Vector2d();
        }
        else if(this.x < Vector2d.epsilon && this.x > -Vector2d.epsilon){
            v = new Vector2d(1, 0);
        }
        else if(this.y < Vector2d.epsilon && this.y > -Vector2d.epsilon){
            v = new Vector2d(0, 1);
        }
        else{
            v = new Vector2d(this.x + 1, - (this.x) * (this.x + 1) / this.y).unit();
        }

        double sign;
        if(this.x * v.y - this.y * v.x > 0){
            sign = 1;
        }
        else{
            sign = -1;
        }
        
        return v.mul(sign);
    }

    public Vector2d rotate(double alpha){
        
        Vector2d vt = this.transverse();

        double n = Math.tan(alpha) * this.len() / vt.len();

        return this.sum(vt.mul(n)).unit().mul(this.len());
    }

    public boolean equals(Vector2d other){
        return this.sub(other).x == 0 && this.sub(other).y == 0;
    }

    public String toString(){
        return "(" + Double.toString(this.x) + "," + Double.toString(this.y) + ")";
    }
}