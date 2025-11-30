public class Sierp{
    private final int L=255;
    private final double C=Math.sqrt(3.0)/2.0;
    private final double del=0.1;
    double stopSize;
    String colorScheme;
    int    errCode=0;

    public Sierp(String colorScheme, double stopSize){

        if     (colorScheme=="lineDrawing")
            System.out.println("Constriucting a Sierpisnki gasket of empty triangles");
        else if(colorScheme=="colorFilling")
            System.out.println("Constriucting a Sierpisnki gasket using color filling");
        else
        {System.out.println("Incorrect colorScheme - nothing to do");
            errCode=1;}

        this.colorScheme = colorScheme;

        if      (stopSize > 0.5)
        {System.out.println("stopSize is too large -- nothing to do");
            errCode=2;}
        else if(stopSize < 0.001)
        {System.out.println("stopSize is too small -- stack overflow is possible");
            errCode=-1;}
        else
            System.out.println("Using stopSize: " + stopSize);


        this.stopSize    = stopSize;

    }

    public void setupCanvas(int L){
        int Lx, Ly;
        Lx=L; Ly=(int)(L*C+0.5);
        StdDraw.setCanvasSize(Lx, Ly);
        StdDraw.clear(StdDraw.LIGHT_GRAY);

        StdDraw.setXscale(0-del, 1+del);
        StdDraw.setYscale(0-del, C+del);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.RED);

    }


    public void tri(){

        if(errCode>0) {System.out.println("Fatal error on input, errCode: "+errCode);
            return;}
        if(errCode<0) {System.out.println("Non-fatal error on input, errCode: "+errCode);}

        if     (colorScheme=="lineDrawing")  triLine (1.0, 0.0, 0.0);
        else if(colorScheme=="colorFilling") triColor(1.0, 0.0, 0.0);
        else   System.out.println("Unexpected error A");
    }

    private void triLine(double a, double x0, double y0){
        double b, c;

        if(a < stopSize) return;

        b = a/2.0; c = a*C;
        double[] p1 = {x0,x0+b,x0+a};
        double[] p2 = {y0,y0+c,y0  };

        StdDraw.polygon(p1,p2);

        triLine(b, x0,       y0      );
        triLine(b, x0+b,     y0      );
        triLine(b, x0+b/2.0, y0+c/2.0);


    } //End method TriLine


    private void triColor(double a, double x0, double y0){
        double b, c;
        int R, G, B;

        if(a < stopSize) return;

        b = a/2.0; c = a*C;
        double[] x = {x0,x0+b,x0+a};
        double[] y = {y0,y0+c,y0  };

        R=(int)(L*Math.log(a/stopSize)/Math.log(1.0/stopSize));
        G=(int)(L-R);
        B=(int)(L-R);

        StdDraw.setPenColor(R, G, B);
        StdDraw.filledPolygon(x,y);

        triColor(b, x0,       y0      );
        triColor(b, x0+b,     y0      );
        triColor(b, x0+b/2.0, y0+c/2.0);


    } // End method TriColor


} // End class Sierp