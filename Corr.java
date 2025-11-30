import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class Corr {

    public static void main(String[] args) throws Exception {

        String fname;
        Scanner scan, Inp;
        int i, j, k, rij2, N, M;
        int[] x, y, hyst;
        float dist, Rmin, Rmax, Rmin2, Rmax2, RL, h, d;
        float X, Y, SumX, SumY, SumXX, SumXY, beta, b;
        File path = new File("f.dat");

        Inp = new Scanner(System.in);

        System.out.print("Give Number of particles in the cluster, N:");
        N   = Inp.nextInt();
        System.out.print("Give Number of hystogram bins, M:");
        M   = Inp.nextInt();

        System.out.print("Give Rmin for the hystogram:");
        Rmin   = Inp.nextFloat();
        System.out.print("Give Rmax for the hystogram:");
        Rmax   = Inp.nextFloat();
        RL     = Rmax - Rmin;
        h      = RL / (float)M;
        Rmin2  = Rmin*Rmin;
        Rmax2  = Rmax*Rmax;

        x    = new int[N+1];
        y    = new int[N+1];

        scan = new Scanner(path);
        for(i=1;i<=N;i++){
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
        }

        hyst = new int[M+1];

        for(i=1  ; i< N; i++){
            for(j=i+1; j<=N; j++){
                rij2    = (x[i]-x[j])*(x[i]-x[j]) + (y[i]-y[j])*(y[i]-y[j]);
                if(rij2 < Rmin2 || rij2 >= Rmax2) continue;
                dist    = (float)Math.sqrt(rij2) - Rmin;
                k       = (int)(dist/h) + 1;
                hyst[k] = hyst[k] + 1;
                //	    System.out.println(dist/h+";  "+k);
            }}

        PrintWriter writer = new PrintWriter("h.dat", "UTF-8");
        for(k=1;k<=M;k++){
            d = Rmin + k*h - 0.5f*h;
            writer.println(d+"  "+(float)hyst[k]/(float)N);
        }
        writer.close();

        SumX = 0.0f; SumY=0.0f; SumXX=0.0f; SumXY=0.0f;
        int count = 0;

        for(k=1;k<=M;k++){
            if(hyst[k] > 0) {  // 跳过空的分箱，避免log(0)
                X = (float)Math.log(Rmin + k*h - 0.5f*h);
                Y = (float)Math.log((float)hyst[k]/(float)N);
                SumX  = SumX  + X  ;
                SumY  = SumY  + Y  ;
                SumXX = SumXX + X*X;
                SumXY = SumXY + X*Y;
                count++;
            }
        }
        if(count > 0) {
            SumX  =  SumX / (float)count;
            SumY  =  SumY / (float)count;
            SumXX = SumXX / (float)count;
            SumXY = SumXY / (float)count;
        }

        beta = (SumXY      - SumX *SumY) / (SumXX - SumX*SumX);
        b    = (SumXX*SumY - SumXY*SumX) / (SumXX - SumX*SumX);
        b    = (float)Math.exp(b);
        System.out.println("beta="+beta+" ;  b="+b);
        System.out.println("   D="+(1.0+beta));

    }}