package modelo;

public class Regresion {

    private double[] x;
    private double[] y;
    private int n;
    public double a, b;

    public Regresion(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        n = x.length; 
    }

    @SuppressWarnings("unused")
	public void lineal() {
        double pxy, sx, sy, sx2, sy2;
        pxy = sx = sy = sx2 = sy2 = 0.0;
        for (int i = 0; i < n; i++) {
            sx += x[i];
            sy += y[i];
            sx2 += x[i] * x[i];
            sy2 += y[i] * y[i];
            pxy += x[i] * y[i];
        }
        b = (n * pxy - sx * sy) / (n * sx2 - sx * sx);
        a = (sy - b * sx) / n;
        
    }

    public double correlacion() {
        double suma = 0.0;
        for (int i = 0; i < n; i++) {
            suma += x[i];
        }
        double mediaX = suma / n;

        suma = 0.0;
        for (int i = 0; i < n; i++) {
            suma += x[i];
        }
        double mediaY = suma / n;


        double pxy, sx2, sy2;
        pxy = sx2 = sy2 = 0.0;
        for (int i = 0; i < n; i++) {
            pxy += (x[i] - mediaX) * (y[i] - mediaY);
            sx2 += (x[i] - mediaX) * (x[i] - mediaX);
            sy2 += (y[i] - mediaY) * (y[i] - mediaY);
        }
        return pxy / Math.sqrt(sx2 * sy2);
    }

}
