package src;

public class Line {
    public Vector2d p1;
    public Vector2d p2;
    public boolean isHalfLine = false;

    public Line(Vector2d p1, Vector2d p2){
        if(p1.equals(p2)){ System.out.println("wrong points: " + p1.toString() + " and " + p2.toString());}
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line(Vector2d p1, Vector2d p2, boolean isHafLine){
        if(p1.equals(p2)){ System.out.println("wrong points: " + p1.toString() + " and " + p2.toString());}
        this.p1 = p1;
        this.p2 = p2;
        this.isHalfLine = isHafLine;
    }

    public boolean areParallel(Line other){
        Vector2d v = this.p2.sub(this.p1);
        Vector2d u = other.p2.sub(other.p1);

        return v.unit().equals(u.unit()) || v.unit().equals(u.neg().unit());
    }

    public boolean areCollinear(Line other){
        if(!this.areParallel(other)){
            return false;
        }

        Vector2d v = this.p2.sub(this.p1);
        Vector2d u = this.p2.sub(other.p1);

        return v.unit().equals(u.unit()) || v.unit().equals(u.neg().unit());
    }

    public Vector2d intersection(Line other){
        Vector2d p1 = this.p1;
        Vector2d q1 = other.p1;
        Vector2d q2 = other.p2;
        Vector2d v = this.p2.sub(this.p1);
        Vector2d u = other.p2.sub(other.p1);
        
        if(this.areParallel(other)){
            if(this.areCollinear(other)){
                double t1, t2;

                if(v.x != 0){
                    t1 = (q1.x - p1.x) / v.x;
                    t2 = (q2.x - p1.x) / v.x;
                }
                else{
                    t1 = (q1.y - p1.y) / v.y;
                    t2 = (q2.y - p1.y) / v.y;
                }

                if(t1 < 0 && t2 < 0){
                    return null;
                }

                if(t1 * t2 < 0){
                    return p1;
                }

                if(t1 <= t2 && t1 <= 1){
                    return p1.sum(v.mul(t1));
                }
                else if(t2 < t1 && t2 <= 1){
                    return p1.sum(v.mul(t2));
                }
            }
            return null;
        }

        if(v.y*u.x - v.x*u.y == 0){
            return null;
        }
        double t = (u.y * p1.x - u.y*q1.x - u.x*p1.y + u.x*q1.y)/(v.y*u.x - v.x*u.y);
        double s;
        if(u.x != 0){
            s = (p1.x - q1.x + v.x * t)/u.x;
        }
        else{
            s = (p1.y - q1.y + v.y * t)/u.y;
        }

        if(0 <= t && (t <= 1 || this.isHalfLine) && 0 <= s && s <= 1){
            return p1.sum(v.mul(t));
        }

        return null;

    }

    public String toString(){
        return "{ " + this.p1.toString() +  " - " + this.p2.toString() + " }";
    }
}
