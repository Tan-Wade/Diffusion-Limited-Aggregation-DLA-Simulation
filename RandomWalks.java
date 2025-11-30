import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;

public class RandomWalks {
    static private final int     LCanvas = 1000;
    static private final double  step    = 1.0;
    static private final double  pi2     = 2.0*Math.PI;
    static private       int     N, L;

    public static void main(String[] args) throws Exception
    {

        int t;
        double x1, y1, x2, y2, r, r2, phi;
        long seed1=1, seed2=1;

        Random      stream1, stream2;
        Scanner     Inp;
        PrintWriter Wrt;

        Inp = new Scanner(System.in);
        Wrt = new PrintWriter("f.dat");

        System.out.print("Give size of the field, L:");
        L = Inp.nextInt();
        System.out.print("Give number of steps, N:");
        N = Inp.nextInt();
        System.out.print("Give seed1:");
        seed1 = Inp.nextInt();
        System.out.print("Give seed2:");
        seed2 = Inp.nextInt();

        StdDraw.setCanvasSize(LCanvas,LCanvas);
        StdDraw.setXscale(-L, L);
        StdDraw.setYscale(-L, L);
        StdDraw.clear(StdDraw.LIGHT_GRAY);
        StdDraw.setPenColor(StdDraw.RED);

        stream1 = new Random(seed1);
        stream2 = new Random(seed2);

        x1=0.0; y1=0.0; r=0.0;
        for(t=0; r<L; t++){
            phi = pi2*stream1.nextDouble();
            x2 = x1 + step*Math.cos(phi);
            y2 = y1 + step*Math.sin(phi);
            r2 = x2*x2 + y2*y2; r = Math.sqrt(r2);
            Wrt.println(t+", "+r);
            StdDraw.line(x1,y1,x2,y2);
            x1=x2; y1=y2;
        }

    }
}