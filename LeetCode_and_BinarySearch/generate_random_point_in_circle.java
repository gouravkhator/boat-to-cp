// Problem Question: https://leetcode.com/problems/generate-random-point-in-a-circle/
class GenerateRandomPointInCircle {
    double radius, xcenter, ycenter;
    
    public GenerateRandomPointInCircle(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.xcenter = x_center;
        this.ycenter = y_center;
    }
    
    public double[] randPoint() {
        /*
        Logic:

        For generating uniform random point in a circle, refer the below maths behind it:
        https://stackoverflow.com/questions/5837572/generate-a-random-point-within-a-circle-uniformly
        */

        double[] xy = new double[2];
        double r = radius * Math.sqrt(Math.random());
        
        double theta = Math.random() * 2 * Math.PI;
        xy[0] = xcenter + r * Math.cos(theta);
        xy[1] = ycenter + r * Math.sin(theta);
        return xy;
    }
}

