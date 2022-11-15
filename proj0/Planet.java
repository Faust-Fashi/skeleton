/*2022/11/15 9:41
 * by IDEA
 */
public class Planet {
    double xxPos, yyPos;
    double xxVel, yyVel;
    double mass;
    String imgFileName;
    // deep copy of a instance
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    public double calcDistance(Planet p){
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        // use "pow" for a faster speed
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
    public double calcForceExertedBy(Planet p){
        double distance = calcDistance(p);
        double G = 6.67 * 1e-11;
        return (G * this.mass * p.mass / Math.pow(distance, 2));
    }
    public double calcForceExertedByX(Planet p){
        double F = calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        double r = calcDistance(p);
        return ((F * dx) / r);
    }
    public double calcForceExertedByY(Planet p){
        double F = calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        double r = calcDistance(p);
        return ((F * dy) / r);
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double totalForceX = 0;
        for(Planet p : allPlanets){
            if(!this.equals(p)){
                totalForceX += this.calcForceExertedByX(p);
            }
        }
        return totalForceX;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double totalForceY = 0;
        for(Planet p : allPlanets){
            if(!this.equals(p)){
                totalForceY += this.calcForceExertedByY(p);
            }
        }
        return totalForceY;
    }
    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
