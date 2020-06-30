package ecuacionii;

import javax.swing.JTextArea;

public class Logica {

    double constante1 = 0;
    double constante2 = 0;
    double auxiliar1 = 0;
    double auxiliar2 = 0;
    double numA2;
    double numB2;
    double numC2;
    double coeficientes[] = new double[2];
    double coeficientes3[][] = new double[3][4];
    String s;

    public String forma1(double numA, double numB, JTextArea area) {
        double indiceFn = 1;
        double indiceFn2;
        double condicion1 = 1;
        double condicion2 = numA + numB;
        s = "";

        s += "**********FORMA 1**********\n";

        s += "Solucion Paso a Paso\n";
        s += "Fn = " + numA + "+ " + numB + "*F_{n/2}\n";
        s += "Considerando que: n= 2^i\n";
        s += indiceFn + "F_{2^i} + (" + (numB * (-1)) + "*F_{2^i-1}) = " + numA + "\n";
        s += "Cambiando los subindices en funcion i:";
        s += indiceFn + " F_i + (" + (numB * (-1)) + "*F_{i-1}) = " + numA + "\n";
        s += "Remplazandoi por i-1 y  Multiplicando por (-1)  se obtiene:\n";

        numA2 = numA * (-1);
        numB2 = numB * (-1);
        indiceFn2 = indiceFn * (-1);

        s += indiceFn2 + " F_{i-1} + (" + (numB2 * (-1)) + "*F_{i-2}) = " + numA2 + "\n";
        s += "Sumando las ecuaciones para obtener la homogenea:\n";

        double b = (numB * (-1)) + indiceFn2;
        double c = numB2 * (-1);

        s += indiceFn + " F_{i} + (" + b + "*F_{i-1})+ (" + c + "*F_{i-2}) = 0\n";

        double[] raiz = new double[2];
        raiz[0] = (-b + Math.sqrt((Math.pow(b, 2)) - (4 * indiceFn * c))) / (2 * indiceFn);
        raiz[1] = (-b - Math.sqrt((Math.pow(b, 2)) - (4 * indiceFn * c))) / (2 * indiceFn);

        s += "Las dos raices son (" + raiz[0] + "," + raiz[1] + ")\n";

        if (raiz[0] == raiz[1]) {
            s += "La solucion General es:\n";
            s += "** Se presenta Multiplicidad**\n";
            s += "F(i) =  C1*" + raiz[0] + "^i + i*C2*" + raiz[1] + "^i\n";
            s += "Remplazando i por lg(n)\n";
            s += "F(n) =  C1*" + raiz[0] + "^lg(n) + lg(n)*C2*" + raiz[1] + "^lg(n)\n";
            s += "Las condiciones iniciales se calculan con:\n";
            s += "Fn = " + numA + "+ " + numB + "*F_{n/2}\n";
            s += "F(1) = " + condicion1 + "\n";
            s += "F(2) = " + condicion2 + "\n";
            s += "Las ecuaciones para calcular C1 y C2 son:\n";
            s += "C1*1^lg(" + raiz[0] + ") + C2*lg(1)*1^lg(" + raiz[1] + ") = " + condicion1 + "\n";
            s += "C1*2^lg(" + raiz[0] + ") + C2*lg(2)*2^lg(" + raiz[1] + ") = " + condicion2 + "\n";
            s += "C1*" + raiz[1] + " + C2*lg(1)*" + raiz[1] + " = " + condicion1 + "\n";
            s += "C1*" + raiz[0] + " + C2*lg(2)*" + raiz[1] + " = " + condicion2 + "\n";
            s += "Matriz para Gauss\n";

            auxiliar1 = Math.log10(1) / Math.log10(2);
            auxiliar2 = Math.log10(2) / Math.log10(2);

            s += "\n";
            s += "" + raiz[1] + " | " + auxiliar1 + " | " + condicion1 + "\n";
            s += "" + raiz[0] + " | " + auxiliar2 + " | " + condicion2 + "\n";
            s += "\n";

            coeficientes = Gauss(raiz[1], auxiliar1, condicion1, raiz[0], auxiliar2, condicion2);

            s += "Donde C1 y C2 son:\n";
            s += "C1 = " + coeficientes[0] + "\n";
            s += "C2 = " + coeficientes[1] + "\n";
            s += "La solucion es: \n";
            s += "F(n) = " + (coeficientes[0] * Math.pow(raiz[0], Math.log10(raiz[1]) / Math.log10(2))) + "+(" + coeficientes[1] + ")* lg(n)\n";

        } else {
            s += ("La solucion General es:\n");
            s += ("F(i) =  C1*" + raiz[0] + "^i + i*C2*" + raiz[1] + "^i\n");
            s += ("Remplazando i por lg(n)\n");
            s += ("F(n) =  C1*" + raiz[0] + "^lg(n) + lg(n)*C2*" + raiz[1] + "^lg(n)\n");
            s += ("Las condiciones iniciales se calculan con:\n");
            s += ("Fn = " + numA + "+ " + numB + "*F_{n/2}\n");
            s += ("F(1) = " + condicion1 + "\n");
            s += ("F(2) = " + condicion2 + "\n");
            s += ("Las ecuaciones para calcular C1 y C2 son:\n");
            s += ("C1*1^lg(" + raiz[0] + ") + C2*1^lg(" + raiz[1] + ") = " + condicion1 + "\n");
            s += ("C1*2^lg(" + raiz[0] + ") + C2*2^lg(" + raiz[1] + ") = " + condicion2 + "\n");
            s += ("C1*" + raiz[1] + " + C2* " + raiz[1] + " = " + condicion1 + "\n");
            s += ("C1*" + raiz[0] + " + C2*" + raiz[1] + " = " + condicion2 + "\n");
            s += ("Matriz para Gauss\n");
            s += "\n";
            s += ("" + raiz[1] + " | " + raiz[1] + " | " + condicion1 + "\n");
            s += ("" + raiz[0] + " | " + raiz[1] + " | " + condicion2 + "\n");
            s += "\n";
            coeficientes = Gauss(raiz[1], raiz[1], condicion1, raiz[0], raiz[1], condicion2);
            s += ("Donde C1 y C2 son:\n");
            s += ("C1 = " + coeficientes[0] + "\n");
            s += ("C2 = " + coeficientes[1] + "\n");
            s += ("La solucion es:\n");
            s += ("F(n) = " + (coeficientes[0] * Math.pow(raiz[0], Math.log10(raiz[1]) / Math.log10(2))) + "* n^" + (Math.log10(raiz[0]) / Math.log10(2)) + "+(" + coeficientes[1] + ")\n");
        }
        s += "\n";
        return s;
    }

    public String forma2(double numA, double numB, JTextArea area) {
        double indiceFn = 1;
        double indiceFn2;
        double condicion1 = 1;
        double condicion2 = numB + Math.pow(2, numA);
        s = "";
        s += "**********FORMA 2**********\n";
        s += ("Solucion Paso a Paso\n");
        s += ("Fn = n^" + numA + "+ " + numB + "*F_{n/2}\n");
        s += ("Considerando que: n= 2^i\n");
        s += (indiceFn + "F_{2^i} + (" + (numB * (-1)) + "*F_{2^i-1}) = (" + indiceFn + ")2^" + numA + "i\n");
        s += ("Cambiando los subindices en funcion i:\n");
        s += (indiceFn + " F_i + (" + (numB * (-1)) + "*F_{i-1}) = (" + indiceFn + ")2^" + numA + "i\n");
        s += ("Remplazandoi por i-1 y  Multiplicando por (-1)  se obtiene:\n");

        numA2 = Math.pow(2, numA);
        numB2 = numB * (-1);
        indiceFn2 = indiceFn * (-1);

        s += (indiceFn2 + " F_{i-1} + (" + (numB2 * (-1)) + "*F_{i-2}) = (" + indiceFn2 + ")2^(" + numA + "i)/" + numA2 + "\n");

        s += (indiceFn2 + " F_{i-1} + (" + (numB2 * (-1)) + "*F_{i-2}) = (" + indiceFn2 + ")2^(" + numA + "i)" + numA2 + "\n");

        s += (indiceFn2 * numA2 + " F_{i-1} + (" + (numB2 * (-1)) * numA2 + "*F_{i-2}) = (" + indiceFn2 + ")2^(" + numA + "i)\n");
        s += ("Sumando las ecuaciones para obtener la homogenea:\n");

        double b = (numB * (-1)) + (indiceFn2 * numA2);
        double c = numB2 * (-1) * numA2;

        s += (indiceFn + " F_{i} + (" + b + "*F_{i-1})+ (" + c + "*F_{i-2}) = 0\n");

        double[] raiz = new double[2];

        raiz[0] = (-b + Math.sqrt((Math.pow(b, 2)) - (4 * indiceFn * c))) / (2 * indiceFn);
        raiz[1] = (-b - Math.sqrt((Math.pow(b, 2)) - (4 * indiceFn * c))) / (2 * indiceFn);

        s += ("Las dos raices son (" + raiz[0] + "," + raiz[1] + ")\n");

        if (raiz[0] == raiz[1]) {
            s += ("La solucion General es:\n");
            s += ("** Se presenta Multiplicidad**\n");
            s += ("F(i) =  C1*" + raiz[0] + "^i + i*C2*" + raiz[1] + "^i\n");
            s += ("Remplazando i por lg(n)\n");
            s += ("F(n) =  C1*" + raiz[0] + "^lg(n) + lg(n)*C2*" + raiz[1] + "^lg(n)\n");
            s += ("Las condiciones iniciales se calculan con:\n");
            s += ("Fn = n^" + numA + "+ " + numB + "*F_{n/2}\n");
            s += ("F(1) = " + condicion1 + "\n");
            s += ("F(2) = " + condicion2 + "\n");
            s += ("Las ecuaciones para calcular C1 y C2 son:\n");
            s += ("C1*1^lg(" + raiz[0] + ") + C2*lg(1)*1^lg(" + raiz[1] + ") = " + condicion1 + "\n");
            s += ("C1*2^lg(" + raiz[0] + ") + C2*lg(2)*2^lg(" + raiz[1] + ") = " + condicion2 + "\n");
            s += ("C1*" + raiz[1] + " + C2*lg(1)*" + raiz[1] + " = " + condicion1 + "\n");
            s += ("C1*" + raiz[0] + " + C2*lg(2)*" + raiz[1] + " = " + condicion2 + "\n");
            s += ("Matriz para Gauss");

            auxiliar1 = Math.log10(1) / Math.log10(2);
            auxiliar2 = (Math.log10(2) / Math.log10(2)) * raiz[1];

            s += "\n";
            s += ("" + indiceFn + " | " + auxiliar1 + " | " + condicion1 + "\n");
            s += ("" + raiz[0] + " | " + auxiliar2 + " | " + condicion2 + "\n");
            s += "\n";

            coeficientes = Gauss(indiceFn, auxiliar1, condicion1, raiz[0], auxiliar2, condicion2);

            s += ("Donde C1 y C2 son:\n");
            s += ("C1 = " + coeficientes[0] + "\n");
            s += ("C2 = " + coeficientes[1] + "\n");
            s += ("La solucion es: \n");
            s += ("F(n) = 1*n^" + numA + "+1*lg(n)*n^" + numA + "\n");
            s += ("F(n)=" + coeficientes[0] + "*n^" + (Math.log(raiz[0]) / Math.log(2)) + " + " + coeficientes[1] + "*lg(n)*n^" + (Math.log(raiz[1]) / Math.log(2)) + "\n");
        } else {
            s += ("La solucion General es:\n");
            s += ("F(i) =  C1*" + raiz[0] + "^i + C2*" + raiz[1] + "^i\n");
            s += ("Remplazando i por lg(n)\n");
            s += ("F(n) =  C1*" + raiz[0] + "^lg(n) + C2*" + raiz[1] + "^lg(n)\n");
            s += ("Las condiciones iniciales se calculan con:\n");
            s += ("Fn = n^" + numA + "+ " + numB + "*F_{n/2}\n");
            s += ("F(1) = " + condicion1 + "\n");
            s += ("F(2) = " + condicion2 + "\n");
            s += ("Las ecuaciones para calcular C1 y C2 son:\n");
            s += ("C1*1^lg(" + raiz[0] + ") + C2*1^lg(" + raiz[1] + ") = " + condicion1 + "\n");
            s += ("C1*2^lg(" + raiz[0] + ") + C2*2^lg(" + raiz[1] + ") = " + condicion2 + "\n");
            s += ("C1*" + indiceFn + " + C2*" + indiceFn + " = " + condicion1 + "\n");
            s += ("C1*" + raiz[0] + " + C2*" + raiz[1] + " = " + condicion2 + "\n");
            s += ("Matriz para Gauss\n");

            auxiliar1 = Math.log10(1) / Math.log10(2);
            auxiliar2 = (Math.log10(2) / Math.log10(2)) * raiz[1];

            s += "\n";
            s += ("" + indiceFn + " | " + indiceFn + " | " + condicion1 + "\n");
            s += ("" + raiz[0] + " | " + raiz[1] + " | " + condicion2 + "\n");
            s += "\n";

            coeficientes = Gauss(indiceFn, indiceFn, condicion1, raiz[0], raiz[1], condicion2);

            s += ("Donde C1 y C2 son:\n");
            s += ("C1 = " + coeficientes[0] + "\n");
            s += ("C2 = " + coeficientes[1] + "\n");
            s += ("La solucion es: \n");
            s += ("F(n) = " + (coeficientes[0] * Math.pow(raiz[0], Math.log10(indiceFn) / Math.log10(2))) + "* n^" + (Math.log10(raiz[0]) / Math.log10(2)) + "+(" + coeficientes[1] + ")* n^" + (Math.log10(raiz[1]) / Math.log10(2)) + "\n");
        }
        return s;
    }

    public String forma3(double numA, double numB, double numC, JTextArea area) {

        double f1 = numC;
        double f2 = (numA + (4 * numB) + (2 * numC));
        double f3 = (numA + (8 * numB) + (2 * (numA + (4 * numB) + (2 * numC))));
        s = "";
        s += "**********FORMA 3**********\n";
        s += ("Solución paso a paso: \n");
        s += ("f2= " + f1 + "\n");
        s += ("f4= " + f2 + "\n");
        s += ("f8= " + f3 + "\n");
        s += ("\n");
        s += ("El sistema de ecuaciones es:\n");
        s += ("C1+2C2+2C3=" + f1 + "\n");
        s += ("C1+4C2+8C3=" + f2 + "\n");
        s += ("C1+8C2+24C3=" + f3 + "\n");
        s += "\n";
        double[][] matriz = {{1, 2, 2, f1}, {1, 4, 8, f2}, {1, 8, 24, f3}};

        coeficientes3 = GaussJordan(matriz);

        double finalCoeficientes[] = new double[3];

        for (int i = 0; i < 3; i++) {
            finalCoeficientes[i] = coeficientes3[i][3];
        }

        s += ("Aplicando Gauss tenemos: \n");
        s += ("C1 = " + finalCoeficientes[0] + "\n");
        s += ("C2 = " + finalCoeficientes[1] + "\n");
        s += ("C3 = " + finalCoeficientes[2] + "\n");

        s += ("Por lo cual la solución es: \n");
        s += ("fn= ( " + finalCoeficientes[0] + ")+n*(" + finalCoeficientes[1] + ")+(" + finalCoeficientes[2] + ")*n*lg (n)\n");
        return s;
    }

    public double[] Gauss(double n1, double n2, double n3, double n4, double n5, double n6) {
        double nAp1;
        double nAp2;
        double nAp3;
        if (n1 != 1) {

            n2 = n2 / n1;
            n3 = n3 / n1;
            n1 = n1 / n1;

        }
        if (n4 != 0) {

            nAp1 = n4 * n1;
            nAp2 = n4 * n2;
            nAp3 = n4 * n3;

            n4 = n4 - nAp1;
            n5 = n5 - nAp2;
            n6 = n6 - nAp3;
        }
        if (n5 != 1) {

            n4 = n4 / n5;
            n6 = n6 / n5;
            n5 = n5 / n5;

        }
        constante2 = n6;

        constante1 = n3 - (n2 * constante2);

        double coe[] = new double[2];
        coe[0] = constante1;
        coe[1] = constante2;
        return coe;

    }

    public static double[][] GaussJordan(double[][] mat) {

        int piv = 0;
        int var = mat.length;

        for (int i = 0; i < var; i++) {
            mat = pivote(mat, piv, var);
            mat = ceros(mat, piv, var);
            piv++;
        }
        return mat;
    }

    public static double[][] pivote(double mat[][], int piv, int var) {
        double temp = mat[piv][piv];
        for (int y = 0; y < (var + 1); y++) {
            mat[piv][y] = mat[piv][y] / temp;
        }
        return mat;
    }

    public static double[][] ceros(double mat[][], int piv, int var) {
        for (int x = 0; x < var; x++) {
            if (x != piv) {
                double c = mat[x][piv];
                for (int z = 0; z < (var + 1); z++) {
                    mat[x][z] = ((-c) * mat[piv][z]) + mat[x][z];
                }
            }
        }
        return mat;
    }

}
