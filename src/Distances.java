
public class Distances {
    public double left;
    public double front;
    public double right;

    public Distances(double left, double front, double right){
        this.left = left;
        this.front = front;
        this.right = right;
    }

    public void minUpdate(Distances other){
        if(other.left < this.left){
            this.left = other.left;
        }
        if(other.front < this.front){
            this.front = other.front;
        }
        if(other.right < this.right){
            this.right = other.right;
        }
    }

    public String toString(){
        return "dist: " + Double.toString(this.left) + ", " + Double.toString(this.front) + ", " + Double.toString(this.right);
    }
}
