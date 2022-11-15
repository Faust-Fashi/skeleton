/*2022/11/15 10:46
 * by IDEA
 */
public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readplanetNet(String fileName){
        In in = new In(fileName);
        int N = in.readInt();
        in.readDouble();
        Planet[] planetNet = new Planet[N];
        for (int i = 0; i < N; i++) {
            planetNet[i] = new Planet(in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readString());
        }
        return planetNet;
    }

    public static void main(String[] args) {
        double T = new Double(args[0]);
        double dt = new Double(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);
        Planet[] planetNet = readplanetNet(fileName);
        int num_planet = planetNet.length;
        double[] xForces = new double[num_planet];
        double[] yForces = new double[num_planet];

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        double t = 0;
        while(t < T){
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < num_planet; i++) {
                xForces[i] = planetNet[i].calcNetForceExertedByX(planetNet);
                yForces[i] = planetNet[i].calcNetForceExertedByY(planetNet);
            }for (int i = 0; i < num_planet; i++){
                planetNet[i].update(dt, xForces[i], yForces[i]);
                planetNet[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", planetNet.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planetNet.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planetNet[i].xxPos, planetNet[i].yyPos, planetNet[i].xxVel,
                    planetNet[i].yyVel, planetNet[i].mass, planetNet[i].imgFileName);
        }
    }
}
