import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;

public class AggregationDynamic {
    static private final double  a  = 0.5;
    static private final double  pi = Math.PI;
    static private final int     LCanvas = 800;
    static private       int     L, L2, Lmax, Lmin, N;

    public static void main(String[] args) throws Exception
    {

        int M, i, j, n, k, ip1, im1, jp1, jm1, iter;
        long seed1, seed2;
        boolean[][] Point, Trap;
        boolean free, connect;
        double x, y, ran, phi, R, R2, Rmax, Rmax2, pTr, fact;
        Random stream1, stream2;
        Scanner Inp;

        Inp = new Scanner(System.in);
        System.out.print("Give size of lattice L:");
        L   = Inp.nextInt();
        System.out.print("Give number of particles N:");
        N   = Inp.nextInt();
        System.out.print("Give the probability of traps pTr:");
        pTr   = Inp.nextDouble();
        System.out.print("Give the multiplicative factor for dyamic R adjustment, fact:");
        fact  = Inp.nextDouble();
        // seed1   = 362459;
        // seed2   = 534387;
        seed1   = 1;
        seed2   = 1;
        System.out.print("Give seed1:");
        seed1 = Inp.nextLong();
        System.out.print("Give seed2:");
        seed2 = Inp.nextLong();

        L2 = 2*L;

        StdDraw.setCanvasSize(LCanvas,LCanvas);
        StdDraw.setXscale(0, L2);
        StdDraw.setYscale(0, L2);
        StdDraw.clear(StdDraw.LIGHT_GRAY);
        StdDraw.setPenColor(StdDraw.RED);

        stream1 = new Random(seed1);
        stream2 = new Random(seed2);

        Point = new boolean[L2+1][L2+1];
        Trap  = new boolean[L2+1][L2+1];

        for(i=0; i<=L2; i++){
            for(j=0; j<=L2; j++){
                Point[i][j] = false;
                ran = stream1.nextDouble();
                Trap[i][j] = false;
                if(ran <= pTr) Trap[i][j] = true;
            }}

        if(Trap[L][L]) {
            System.out.println("The site (L,L) is a trap; try different seeds for random streams");
            return;
        }

        if(Trap[L][L+1]) {
            System.out.println("The site (L,L+1) is a trap; try different seeds for random streams");
            return;
        }

        if(Trap[L][L-1]) {
            System.out.println("The site (L,L-1) is a trap; try different seeds for random streams");
            return;
        }

        if(Trap[L+1][L]) {
            System.out.println("The site (L+1,L) is a trap; try different seeds for random streams");
            return;
        }

        if(Trap[L-1][L]) {
            System.out.println("The site (L-1,L) is a trap; try different seeds for random streams");
            return;
        }

        if(Trap[L-1][L-1]) {
            System.out.println("The site (L-1,L-1) is a trap; try different seeds for random streams");
            return;
        }

        if(Trap[L-1][L+1]) {
            System.out.println("The site (L-1,L+1) is a trap; try different seeds for random streams");
            return;
        }

        if(Trap[L+1][L-1]) {
            System.out.println("The site (L+1,L-1) is a trap; try different seeds for random streams");
            return;
        }

        if(Trap[L+1][L+1]) {
            System.out.println("The site (L+1,L+1) is a trap; try different seeds for random streams");
            return;
        }


        Point[L][L] = true;
        M = 1;
        StdDraw.filledCircle(L, L, a);

        Rmax  = 1.0;
        Rmax2 = 1.0;
        // R=L/2;

        for(n=1; M<=N; n++){

            R    = fact*Rmax;

            Lmax = Math.min(L+(int)(fact*R), L2);
            Lmin = Math.max(L-(int)(fact*R), 0 );

            ran = stream1.nextFloat();
            phi = 2.0*pi*ran;
            i = L + (int)(R*Math.cos(phi));
            j = L + (int)(R*Math.sin(phi));
            //	if(Trap[i][j]) break;

            free = true;
            while(free){

                k = stream2.nextInt(4);

                if      (k==0) i++;
                else if (k==1) i--;
                else if (k==2) j++;
                else if (k==3) j--;
                else           {System.out.println("Wrong k:"+k); return;}

	    /* if(i>L2) i = 0;
	    if(i<0 ) i = L2;
	    if(j>L2) j = 0;
	    if(j<0 ) j = L2; */

                if(i>Lmax) i = Lmin;
                if(i<Lmin) i = Lmax;
                if(j>Lmax) j = Lmin;
                if(j<Lmin) j = Lmax;

                if(Trap[i][j]) break;

                if(isConnected(Point,i,j)) {
                    M++;
                    Point[i][j]=true;
                    R2 = (double)((i-L)*(i-L) + (j-L)*(j-L));
                    if(R2 > Rmax2) {Rmax2 = R2; Rmax=Math.sqrt(R2);}
                    free = false;
                    System.out.println("M:"+M);
                    StdDraw.filledCircle(i, j, a);
                }

            } // End while(free) loop
        } // End for(n=1; n<=N; n++) loop

        dumpCoord(Point);

    }      // End main method

    public static void dumpCoord(boolean[][] P) throws Exception{
        PrintWriter writer = new PrintWriter("f.dat", "UTF-8");
        int i,j;
        for(i=0;i<=L2;i++){
            for(j=0;j<=L2;j++){
                if(P[i][j]) writer.println(i+"  "+j);
            }}
        writer.close();
    }      // End dumpCoord method

    public static boolean isConnected(boolean[][] P, int i, int j)
    {
        int ip1, im1, jp1, jm1;

        ip1 = i+1;
        im1 = i-1;
        jp1 = j+1;
        jm1 = j-1;

        if(ip1>L2) ip1 = 0;
        if(im1<0 ) im1 = L2;
        if(jp1>L2) jp1 = 0;
        if(jm1<0 ) jm1 = L2;

        return P[ip1][j] || P[im1][j] || P[i][jp1] || P[i][jm1];

    }      // End isConnected method

}          // End Aggregation_1 class