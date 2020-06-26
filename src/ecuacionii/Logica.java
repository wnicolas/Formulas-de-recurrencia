
package ecuacionii;


public class Logica {

    double constante1 = 0;
    double constante2 = 0;
    double auxiliar1 = 0;
    double auxiliar2 = 0;
    double numA2;
    double numB2;
    double numC2;
    double coeficientes[]=new double[2];
    double coeficientes3[][]=new double[3][4];

    public void ecuacionRemplazar(double numA, double numB, double numC,String tipo) {

        if (tipo == "eq1") {
            forma1(numA, numB);

        }
        if (tipo == "eq2") {
            forma2(numA, numB);
        }
        
        if (tipo=="eq3"){
            forma3(numA, numB, numC);
        }
    }

    public void forma1(double numA, double numB) {
        double indiceFn = 1;
        double indiceFn2;
        double condicion1 = 1;
        double condicion2 = numA + numB;

        System.out.println("Solucion Paso a Paso");
        System.out.println("Fn = " + numA + "+ " + numB + "*F_{n/2}");
        System.out.println("Considerando que: n= 2^i");
        System.out.println(indiceFn + "F_{2^i} + (" + (numB * (-1)) + "*F_{2^i-1}) = " + numA);
        System.out.println("Cambiando los subindices en funcion i:");
        System.out.println(indiceFn + " F_i + (" + (numB * (-1)) + "*F_{i-1}) = " + numA);
        System.out.println("Remplazandoi por i-1 y  Multiplicando por (-1)  se obtiene:");

        numA2 = numA * (-1);
        numB2 = numB * (-1);
        indiceFn2 = indiceFn * (-1);

        System.out.println(indiceFn2 + " F_{i-1} + (" + (numB2 * (-1)) + "*F_{i-2}) = " + numA2);
        System.out.println("Sumando las ecuaciones para obtener la homogenea:");

        double b = (numB * (-1)) + indiceFn2;
        double c = numB2 * (-1);

        System.out.println(indiceFn + " F_{i} + (" + b + "*F_{i-1})+ (" + c + "*F_{i-2}) = 0");

        double[] raiz = new double[2];
        raiz[0] = (-b + Math.sqrt((Math.pow(b, 2)) - (4 * indiceFn * c))) / (2 * indiceFn);
        raiz[1] = (-b - Math.sqrt((Math.pow(b, 2)) - (4 * indiceFn * c))) / (2 * indiceFn);

        System.out.println("Las dos raices son (" + raiz[0] + "," + raiz[1] + ")");

        if (raiz[0] == raiz[1]) {
            System.out.println("La solucion General es:");
            System.out.println("** Se presenta Multiplicidad**");
            System.out.println("F(i) =  C1*" + raiz[0] + "^i + i*C2*" + raiz[1] + "^i");
            System.out.println("Remplazando i por lg(n)");
            System.out.println("F(n) =  C1*" + raiz[0] + "^lg(n) + lg(n)*C2*" + raiz[1] + "^lg(n)");
            System.out.println("Las condiciones iniciales se calculan con:");
            System.out.println("Fn = " + numA + "+ " + numB + "*F_{n/2}");
            System.out.println("F(1) = " + condicion1);
            System.out.println("F(2) = " + condicion2);
            System.out.println("Las ecuaciones para calcular C1 y C2 son:");
            System.out.println("C1*1^lg(" + raiz[0] + ") + C2*lg(1)*1^lg(" + raiz[1] + ") = " + condicion1);
            System.out.println("C1*2^lg(" + raiz[0] + ") + C2*lg(2)*2^lg(" + raiz[1] + ") = " + condicion2);
            System.out.println("C1*" + raiz[1] + " + C2*lg(1)*" + raiz[1] + " = " + condicion1);
            System.out.println("C1*" + raiz[0] + " + C2*lg(2)*" + raiz[1] + " = " + condicion2);
            System.out.println("Matriz para Gauss");

            auxiliar1 = Math.log10(1) / Math.log10(2);
            auxiliar2 = Math.log10(2) / Math.log10(2);

            System.out.println();
            System.out.println("" + raiz[1] + " | " + auxiliar1 + " | " + condicion1 + "");
            System.out.println("" + raiz[0] + " | " + auxiliar2 + " | " + condicion2 + "");
            System.out.println();

            coeficientes=Gauss(raiz[1], auxiliar1, condicion1, raiz[0], auxiliar2, condicion2);

            System.out.println("Donde C1 y C2 son:");
            System.out.println("C1 = " + coeficientes[0]);
            System.out.println("C2 = " + coeficientes[1]);
            System.out.println("La solucion es: ");
            System.out.println("F(n) = " + (coeficientes[0] * Math.pow(raiz[0], Math.log10(raiz[1]) / Math.log10(2))) + "+(" + coeficientes[1] + ")* lg(n)");
        } else {
            System.out.println("La solucion General es:");
            System.out.println("F(i) =  C1*" + raiz[0] + "^i + i*C2*" + raiz[1] + "^i");
            System.out.println("Remplazando i por lg(n)");
            System.out.println("F(n) =  C1*" + raiz[0] + "^lg(n) + lg(n)*C2*" + raiz[1] + "^lg(n)");
            System.out.println("Las condiciones iniciales se calculan con:");
            System.out.println("Fn = " + numA + "+ " + numB + "*F_{n/2}");
            System.out.println("F(1) = " + condicion1);
            System.out.println("F(2) = " + condicion2);
            System.out.println("Las ecuaciones para calcular C1 y C2 son:");
            System.out.println("C1*1^lg(" + raiz[0] + ") + C2*1^lg(" + raiz[1] + ") = " + condicion1);
            System.out.println("C1*2^lg(" + raiz[0] + ") + C2*2^lg(" + raiz[1] + ") = " + condicion2);
            System.out.println("C1*" + raiz[1] + " + C2* " + raiz[1] + " = " + condicion1);
            System.out.println("C1*" + raiz[0] + " + C2*" + raiz[1] + " = " + condicion2);
            System.out.println("Matriz para Gauss");
            
            System.out.println();
            System.out.println("" + raiz[1] + " | " + raiz[1] + " | " + condicion1 + "");
            System.out.println("" + raiz[0] + " | " + raiz[1] + " | " + condicion2 + "");
            System.out.println();
            
            coeficientes=Gauss(raiz[1], raiz[1], condicion1, raiz[0], raiz[1], condicion2);
            System.out.println("Donde C1 y C2 son:");
            System.out.println("C1 = " + coeficientes[0]);
            System.out.println("C2 = " + coeficientes[1]);
            System.out.println("La solucion es:");
            System.out.println("F(n) = " + (coeficientes[0] * Math.pow(raiz[0], Math.log10(raiz[1]) / Math.log10(2))) + "* n^" + (Math.log10(raiz[0]) / Math.log10(2)) + "+(" + coeficientes[1] + ")");
        }
        System.out.println();
    }

   public void forma2(double numA, double numB) {
        double indiceFn = 1;
        double indiceFn2;
        double condicion1 = 1;
        double condicion2 = numB + Math.pow(2, numA);

        System.out.println("Solucion Paso a Paso");
        System.out.println("Fn = n^" + numA + "+ " + numB + "*F_{n/2}");
        System.out.println("Considerando que: n= 2^i");
        System.out.println(indiceFn + "F_{2^i} + (" + (numB * (-1)) + "*F_{2^i-1}) = (" + indiceFn + ")2^" + numA + "i");
        System.out.println("Cambiando los subindices en funcion i:");
        System.out.println(indiceFn + " F_i + (" + (numB * (-1)) + "*F_{i-1}) = (" + indiceFn + ")2^" + numA + "i");
        System.out.println("Remplazandoi por i-1 y  Multiplicando por (-1)  se obtiene:");

        numA2 = Math.pow(2,numA);
        numB2 = numB * (-1);
        indiceFn2 = indiceFn * (-1);

<<<<<<< HEAD
        System.out.println(indiceFn2 + " F_{i-1} + (" + (numB2 * (-1)) + "*F_{i-2}) = (" + indiceFn2 + ")2^(" + numA + "i)/" + numA2);
=======
        System.out.println(indiceFn2 + " F_{i-1} + (" + (numB2 * (-1)) + "*F_{i-2}) = (" + indiceFn2 + ")2^(" + numA + "i)" + numA2);
>>>>>>> b9d1886dc9377138a5dddf539e17ff0e1abba3dd
        System.out.println(indiceFn2 * numA2 + " F_{i-1} + (" + (numB2 * (-1)) * numA2 + "*F_{i-2}) = (" + indiceFn2 + ")2^(" + numA + "i)");
        System.out.println("Sumando las ecuaciones para obtener la homogenea:");

        double b = (numB * (-1)) + (indiceFn2 * numA2);
        double c = numB2 * (-1) * numA2;

        System.out.println(indiceFn + " F_{i} + (" + b + "*F_{i-1})+ (" + c + "*F_{i-2}) = 0");

        double[] raiz = new double[2];

        raiz[0] = (-b + Math.sqrt((Math.pow(b, 2)) - (4 * indiceFn * c))) / (2 * indiceFn);
        raiz[1] = (-b - Math.sqrt((Math.pow(b, 2)) - (4 * indiceFn * c))) / (2 * indiceFn);

        System.out.println("Las dos raices son (" + raiz[0] + "," + raiz[1] + ")");

        if (raiz[0] == raiz[1]) {
            System.out.println("La solucion General es:");
            System.out.println("** Se presenta Multiplicidad**");
            System.out.println("F(i) =  C1*" + raiz[0] + "^i + i*C2*" + raiz[1] + "^i");
            System.out.println("Remplazando i por lg(n)");
            System.out.println("F(n) =  C1*" + raiz[0] + "^lg(n) + lg(n)*C2*" + raiz[1] + "^lg(n)");
            System.out.println("Las condiciones iniciales se calculan con:");
            System.out.println("Fn = n^" + numA + "+ " + numB + "*F_{n/2}");
            System.out.println("F(1) = " + condicion1);
            System.out.println("F(2) = " + condicion2);
            System.out.println("Las ecuaciones para calcular C1 y C2 son:");
            System.out.println("C1*1^lg(" + raiz[0] + ") + C2*lg(1)*1^lg(" + raiz[1] + ") = " + condicion1);
            System.out.println("C1*2^lg(" + raiz[0] + ") + C2*lg(2)*2^lg(" + raiz[1] + ") = " + condicion2);
            System.out.println("C1*" + raiz[1] + " + C2*lg(1)*" + raiz[1] + " = " + condicion1);
            System.out.println("C1*" + raiz[0] + " + C2*lg(2)*" + raiz[1] + " = " + condicion2);
            System.out.println("Matriz para Gauss");

            auxiliar1 = Math.log10(1) / Math.log10(2);
            auxiliar2 = (Math.log10(2) / Math.log10(2)) * raiz[1];
            
            System.out.println();
            System.out.println("" + indiceFn + " | " + auxiliar1 + " | " + condicion1 + "");
            System.out.println("" + raiz[0] + " | " + auxiliar2 + " | " + condicion2 + "");
            System.out.println();

<<<<<<< HEAD
            coeficientes=Gauss(indiceFn, auxiliar1, condicion1, raiz[0], auxiliar2, condicion2);
           
=======
            coeficientes=Gauss(raiz[1], auxiliar1, condicion1, raiz[0], auxiliar2, condicion2);

>>>>>>> b9d1886dc9377138a5dddf539e17ff0e1abba3dd
            System.out.println("Donde C1 y C2 son:");
            System.out.println("C1 = " + coeficientes[0]);
            System.out.println("C2 = " + coeficientes[1]);
            System.out.println("La solucion es: ");
            System.out.println("F(n) = 1*n^" + numA + "+1*lg(n)*n^" + numA + "");
            System.out.println("F(n)="+coeficientes[0]+"*n^"+(Math.log(raiz[0])/Math.log(2))+" + "+coeficientes[1]+"*lg(n)*n^"+(Math.log(raiz[1])/Math.log(2)));
        } else {
            System.out.println("La solucion General es:");
            System.out.println("F(i) =  C1*" +  raiz[0]+"^i + C2*" + raiz[1]+"^i");
            System.out.println("Remplazando i por lg(n)");
            System.out.println("F(n) =  C1*" +  raiz[0]+"^lg(n) + C2*" + raiz[1]+"^lg(n)");
            System.out.println("Las condiciones iniciales se calculan con:");
            System.out.println("Fn = n^" + numA + "+ " + numB + "*F_{n/2}");
            System.out.println("F(1) = " + condicion1);
            System.out.println("F(2) = " + condicion2);
            System.out.println("Las ecuaciones para calcular C1 y C2 son:");
            System.out.println("C1*1^lg("+  raiz[0]+") + C2*1^lg(" + raiz[1]+") = "+ condicion1);
            System.out.println("C1*2^lg("+  raiz[0]+") + C2*2^lg(" + raiz[1]+") = "+ condicion2);
            System.out.println("C1*"+ indiceFn+" + C2*" + indiceFn+" = "+ condicion1);
            System.out.println("C1*"+  raiz[0]+" + C2*" + raiz[1]+" = "+ condicion2);
            System.out.println("Matriz para Gauss");

            auxiliar1 = Math.log10(1) / Math.log10(2);
            auxiliar2 = (Math.log10(2) / Math.log10(2)) * raiz[1];
            
            System.out.println();
            System.out.println("" + indiceFn + " | " + indiceFn + " | " + condicion1 + "");
            System.out.println("" + raiz[0] + " | " + raiz[1] + " | " + condicion2 + "");
            System.out.println();
            
            coeficientes=Gauss(indiceFn,indiceFn,condicion1,raiz[0],raiz[1],condicion2);

            System.out.println("Donde C1 y C2 son:");
            System.out.println("C1 = " + coeficientes[0]);
            System.out.println("C2 = " + coeficientes[1]);
            System.out.println("La solucion es: ");
            System.out.println("F(n) = "+(coeficientes[0]*Math.pow(raiz[0],Math.log10(indiceFn) / Math.log10(2)))+ "* n^"+(Math.log10(raiz[0]) / Math.log10(2)) +"+("+coeficientes[1]+")* n^"+(Math.log10(raiz[1]) / Math.log10(2)));
        }
    }
    
    public void forma3(double numA, double numB, double numC){
        
        
        double f1=numC;
        double f2=(numA+(4*numB)+(2*numC));
        double f3=(numA+(8*numB)+(2*(numA+(4*numB)+(2*numC))));
        System.out.println("Solución paso a paso: ");
        System.out.println("f2= "+f1);
        System.out.println("f4= "+f2);
        System.out.println("f8= "+f3);
        System.out.println(" ");
        System.out.println("El sistema de ecuaciones es:");
        System.out.println("C1+2C2+2C3="+f1);
        System.out.println("C1+4C2+8C3="+f2);
        System.out.println("C1+8C2+24C3="+f3);
        System.out.println("");
        double[][] matriz={{1,2,2,f1},{1,4,8,f2},{1,8,24,f3}};
        
        
        
        coeficientes3=GaussJordan(matriz);
        
        double finalCoeficientes[]=new double[3];
        
        for (int i = 0; i < 3; i++) {
            finalCoeficientes[i]=coeficientes3[i][3];
        }
        
        System.out.println("Aplicando Gauss tenemos: ");
        System.out.println("C1 = " + finalCoeficientes[0]);
        System.out.println("C2 = " + finalCoeficientes[1]);
        System.out.println("C3 = " + finalCoeficientes[2]);
        //System.out.println("C1 = " + coeficientes[2]);
           
        System.out.println("Por lo cual la solución es: ");
        System.out.println("fn= ( "+finalCoeficientes[0]+")+n*("+finalCoeficientes[1]+")+("+finalCoeficientes[2]+")*n*lg (n)");
        
        
        
        
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
        
        double coe[]=new double[2];
        coe[0]=constante1;
        coe[1]=constante2;
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
