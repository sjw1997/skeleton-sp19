/**
 *  This class that has no constructor is to simulate a universe
 *  specified in one of data files.
 */

 public class NBody {
    public static double readRadius(String fileString) {
        In in = new In(fileString);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies(String fileString) {
        In in = new In(fileString);
        int length = in.readInt();
        Body[] bodies = new Body[length];
        in.readDouble();

        for (int i = 0; i < length; i++) {
            bodies[i] = new Body(in.readDouble(), in.readDouble(),
                                in.readDouble(), in.readDouble(),
                                in.readDouble(), in.readString());
        }

        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);
        Body[] bodies = readBodies(fileName);
        
        StdDraw.setXscale(0, radius);
        StdDraw.setYscale(0, radius);
        StdDraw.enableDoubleBuffering();
        

        double t = 0;
        while (t <= T) {
            //create an xForces array and yForces array.
            double[] xForces = new double [bodies.length];
            double[] yForces = new double [bodies.length];

            //calculate the net x and y forces for each Body
            //and storing these in the arrays.
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            //update each of the Bodies
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            //Draw the background image
            StdDraw.picture(radius / 2, radius / 2, "images/starfield.jpg");

            //Draw all the bodies
            for (Body d : bodies) {
                d.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                        bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }
}