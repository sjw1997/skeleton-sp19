/**
 *  Project 0.
 *  Class Body.
 */
import java.lang.Math;

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body d) {
        this.xxPos = d.xxPos;
        this.yyPos = d.yyPos;
        this.xxVel = d.xxVel;
        this.yyVel = d.yyVel;
        this.mass = d.mass;
        this.imgFileName = d.imgFileName;
    }

    public double calcDistance(Body d) {
        return Math.sqrt((d.xxPos - this.xxPos) * (d.xxPos - this.xxPos) +
        (d.yyPos - this.yyPos) * (d.yyPos - this.yyPos));
    }

    public double calcForceExertedBy(Body d) {
        double distance = calcDistance(d);
        return G * this.mass * d.mass / Math.pow(distance, 2);
    }

    public double calcForceExertedByX(Body d) {
        double force = calcForceExertedBy(d);
        double dx = d.xxPos - this.xxPos;
        double r = calcDistance(d);
        
        return force * dx / r;
    }

    public double calcForceExertedByY(Body d) {
        double force = calcForceExertedBy(d);
        double dy = d.yyPos - this.yyPos;
        double r = calcDistance(d);

        return force * dy / r;
    }

    public double calcNetForceExertedByX(Body[] allBodys) {
        double netForceX = 0;
        for (Body d : allBodys) {
            if (!this.equals(d)) {
                netForceX += calcForceExertedByX(d);
            }
        }

        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] allBodys) {
        double netForceY = 0;
        for (Body d : allBodys) {
            if (!this.equals(d)) {
                netForceY += calcForceExertedByY(d);
            }
        }

        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + aX * dt;
        yyVel = yyVel + aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt; 
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}