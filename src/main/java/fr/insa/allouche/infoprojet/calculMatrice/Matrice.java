/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.allouche.infoprojet.calculMatrice;

/**
 *
 * @author Portable
 */
import java.util.Arrays;

public class Matrice {

    private int nbrLig;
    private int nbrCol;
    protected double[][] coeffs;

    public Matrice(int nl, int nc) {
        this.nbrLig = nl;
        this.nbrCol = nc;
        this.coeffs = new double[nl][nc];
        for (int i = 0; i < this.nbrLig; i++) {
            for (int j = 0; j < this.nbrCol; j++) {
                this.coeffs[i][j] = 0;
            }
        }
    }

    public String toString() {
        String s;
        s = "";
        for (int i = 0; i < this.nbrLig; i++) {
            s = s + "[";
            for (int j = 0; j < this.nbrCol; j++) {
                s = s + String.format("%+4.2E", this.coeffs[i][j]);
                if (j != this.nbrCol - 1) {
                    s = s + " ";
                }

            }
            s = s + "]\n";

        }
        return s;
    }

    public static Matrice matTest1() {
        int NB = 0;
        Matrice Test1 = new Matrice(3, 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Test1.coeffs[i][j] = NB;
                NB++;
            }
        }
        return Test1;
    }

    public static Matrice identite(int taille) {
        Matrice m = new Matrice(taille, taille);
        for (int i = 0; i < m.nbrLig; i++) {
            m.coeffs[i][i] = 1;
        }
        return m;
    }

    public static int aleaUnOuDeux() {
        return (int) (Math.random() * 2 + 1);
    }

    public static Matrice matAleaZeroUnDeux(int nbLig, int nbCol,
            double probaZero) {
        Matrice res;
        res = new Matrice(nbLig, nbCol);
        for (int i = 0; i < res.nbrLig; i++) {
            for (int j = 0; j < res.nbrCol; j++) {
                if (Math.random() > probaZero) {
                    res.coeffs[i][j] = Matrice.aleaUnOuDeux();
                }
            }
        }
        return res;
    }

    public static void testAffiche() {
        int i = 3;
        Matrice m = new Matrice(2, 3);
        m.coeffs[0][0] = 3.223456;
        System.out.println("coeffs de m : "
                + Arrays.deepToString(m.coeffs));
        System.out.println("m = \n" + m);

    }

    public static void testIdentite() {
        System.out.println("identité  4:");
        System.out.println(Matrice.identite(4).toString());
        System.out.println("identité 5 :");
        System.out.println(Matrice.identite(5).toString());

    }

    public static void test1() {
        Matrice m = Matrice.matAleaZeroUnDeux(4, 6, 0.33);
        System.out.println("mat alea : \n" + m);

    }

    public int getNbrLig() {
        return this.nbrLig;
    }

    public int getNbrCol() {
        return this.nbrCol;
    }

    public double get(int lig, int col) {
        return this.coeffs[lig][col];
    }

    /**
     * modifie un des coeffs de this.
     */
    public void set(int lig, int col, double nVal) {
        this.coeffs[lig][col] = nVal;
    }

    public Matrice concatLig(Matrice n) {
        if (this.getNbrCol() != n.getNbrCol()) {
            throw new Error("nombre de cols incompatible");
        }
        Matrice res = new Matrice(this.getNbrLig() + n.getNbrLig(),
                this.getNbrCol());
        for (int i = 0; i < this.getNbrLig(); i++) {
            for (int j = 0; j < this.getNbrCol(); j++) {
                res.set(i, j, this.get(i, j));
            }
        }
        for (int i = 0; i < n.getNbrLig(); i++) {
            for (int j = 0; j < n.getNbrCol(); j++) {
                res.set(i + this.getNbrLig(), j, n.get(i, j));
            }
        }
        return res;
    }

    public Matrice concatCol(Matrice n) {
        if (this.getNbrLig() != n.getNbrLig()) {
            throw new Error("nombre de lignes incompatible");
        }
        Matrice res = new Matrice(this.getNbrLig(),
                this.getNbrCol() + n.getNbrCol());
        for (int i = 0; i < this.getNbrLig(); i++) {
            for (int j = 0; j < this.getNbrCol(); j++) {
                res.set(i, j, this.get(i, j));
            }
        }
        for (int i = 0; i < n.getNbrLig(); i++) {
            for (int j = 0; j < n.getNbrCol(); j++) {
                res.set(i, j + this.getNbrCol(), n.get(i, j));
            }
        }
        return res;
    }

    public static void testConcatLig() {
        Matrice m1 = Matrice.identite(3);
        Matrice m2 = Matrice.matAleaZeroUnDeux(2, 3, 0.33);
        Matrice m3 = m1.concatLig(m2);
        System.out.println("M1 : \n" + m1);
        System.out.println("M2 : \n" + m2);
        System.out.println("M3 = M1.concatLig(M2) : \n" + m3);
    }

    public static void testConcaCol() {
        Matrice m1 = Matrice.identite(3);
        Matrice m2 = Matrice.matAleaZeroUnDeux(3, 2, 0.33);
        Matrice m3 = m1.concatCol(m2);
        System.out.println("M1 : \n" + m1);
        System.out.println("M2 : \n" + m2);
        System.out.println("M3 = M1.concatCol(M2) : \n" + m3);
    }

    public Matrice add(Matrice m2) {
        if (this.nbrLig != m2.nbrLig || this.nbrCol != m2.nbrCol) {
            throw new Error("incompatibles");
        }
        int nl = this.nbrLig;
        int nc = this.nbrCol;
        Matrice res = new Matrice(nl, nc);
        for (int i = 0; i < nl; i++) {
            for (int j = 0; j < nc; j++) {
                res.set(i, j, this.get(i, j) + m2.get(i, j));
                // ou res.coeffs[i][j] = this.coeffs[i][j] + m2.coeffs[i][j];
            }
        }
        return res;
    }

    public Matrice opp() {
        int nl = this.nbrLig;
        int nc = this.nbrCol;
        Matrice res = new Matrice(nl, nc);
        for (int i = 0; i < nl; i++) {
            for (int j = 0; j < nc; j++) {
                res.set(i, j, -this.get(i, j));
            }
        }
        return res;
    }

    public void testSubLig(int nMin, int nMax) {
        System.out.println(this.subLignes(nMin, nMax));
    }

    public void testSubCols(int nMin, int nMax) {
        System.out.println(this.subCols(nMin, nMax));
    }

    public void testtransposee() {
        System.out.println(this.transposee());
    }

    public Matrice moins(Matrice m2) {
        return this.add(m2.opp());
    }

    public Matrice subLignes(int nMin, int nMax) {
        int z = 0;
        Matrice res = new Matrice(nMax - nMin + 1, this.nbrCol);
        for (int i = nMin; i <= nMax; i++) {
            for (int j = 0; j < this.nbrCol; j++) {
                res.coeffs[z][j] = this.coeffs[i][j];
            }
            z++;
        }
        return res;

    }

    public Matrice subCols(int cMin, int cMax) {
        int z = 0;
        Matrice res = new Matrice(this.nbrLig, cMax - cMin + 1);
        for (int i = 0; i < this.nbrLig; i++) {
            for (int j = cMin; j <= cMax; j++) {
                res.coeffs[i][z] = this.coeffs[i][j];
                z++;
            }
            z = 0;
        }
        return res;
    }

    public Matrice copie() {
        Matrice res = new Matrice(this.nbrLig, this.nbrCol);
        for (int i = 0; i < this.nbrLig; i++) {
            for (int j = 0; j < this.nbrCol; j++) {
                res.coeffs[i][j] = this.coeffs[i][j];
            }
        }
        return res;
    }

    public Matrice transposee() {
        int s = 0;
        int z = 0;
        Matrice res = new Matrice(this.nbrCol, this.nbrLig);
        for (int i = 0; i < this.nbrCol; i++) {
            for (int j = 0; j < this.nbrLig; j++) {
                res.coeffs[s][z] = this.coeffs[j][i];
                z++;
            }
            s++;
            z = 0;
        }
        return res;
    }

    public Matrice metAuCarre() {
        int nl = this.nbrLig;
        int nc = this.nbrCol;
        Matrice res = new Matrice(nl + nc, nl + nc);
        Matrice Idl = new Matrice(nl, nl);
        Matrice Idc = new Matrice(nc, nc);
        Matrice transposee = new Matrice(nl, nc);

        Idl = identite(nl);
        Idc = identite(nc);
        /*
    	for (int i = 0; i < nl; i++) {
    		for (int j = 0; j < nc; j++) {
				transposee.coeffs [i][j]=this.coeffs[i][j];
				res.coeffs[i][j]=this.coeffs[i][j];
			}
		}
    	transposee.transposee();
    	
    	for (int i = 0; i < nl; i++) {
    		for (int j = nc; j < nl+nc; j++) {
				res.coeffs[i][j]=Idl.coeffs [i][j];
			}
		}
    	for (int i = nl; i < nl+nc; i++) {
    		for (int j = 0; j < nc; j++) {
				res.coeffs[i][j]=Idc.coeffs [i][j];
			}
		}
    	for (int i = nl; i < nl+nc; i++) {
    		for (int j = nc; j < nc+nl; j++) {
				res.coeffs[i][j]=transposee.coeffs [i][j];
			}
		}
         */
        for (int i = 0; i < nl + nc; i++) {
            for (int j = 0; j < nl + nc; j++) {
                if (i < nl && j < nc) {
                    res.coeffs[i][j] = this.coeffs[i][j];
                }
                if (i < nl && j >= nc) {
                    res.coeffs[i][j] = Idl.coeffs[i][j - nc];
                }
                if (i >= nl && j < nc) {
                    res.coeffs[i][j] = Idc.coeffs[i - nl][j];
                }
                if (i >= nl && j >= nc) {
                    res.coeffs[i][j] = this.transposee().coeffs[i - nl][j - nc];
                }
            }

        }
        return res;
    }

    public void testmetAuCarre() {
        System.out.println(this.metAuCarre());
    }

    public static int intAlea(int bmin, int bmax) {
        int a = (int) (Math.random() * (bmax - bmin + 1) + bmin);
        return a;
    }

    public static Matrice test2() {
        int nl = intAlea(1, 4);
        int nc = intAlea(1, 4);
        Matrice m = new Matrice(nl, nc);
        m = matAleaZeroUnDeux(nl, nc, 0.33);
        Matrice mc = new Matrice(nl + nc, nc + nl);
        mc = m.metAuCarre();
        Matrice minte = new Matrice(nl + nc, nc + nl);
        Matrice mbis = new Matrice(nl, nc);
        minte = mc.subCols(0, nc - 1);
        mbis = minte.subLignes(0, nl - 1);
        System.out.println("m= " + m);
        return m;
        /*
    	System.out.println("m= "+ m);
    	System.out.println("mc= "+ mc);
    	System.out.println("mbis= "+ mbis);
         */
    }

    public Matrice mult(Matrice n) {
        Matrice res = new Matrice(this.nbrLig, n.nbrCol);
        double a = 0;
        for (int i = 0; i < this.nbrLig; i++) {
            for (int w = 0; w < n.nbrCol; w++) {
                for (int j = 0; j < this.nbrCol; j++) {
                    a = a + this.coeffs[i][j] * n.coeffs[j][w];
                }
                res.coeffs[i][w] = a;
                a = 0;
            }
        }
        //System.out.println("res= " + res);
        return res;
    }

    public static void test3() {
        Matrice m = new Matrice(3, 3);
        Matrice mCarre = new Matrice(3, 3);
        m = matTest1();
        mCarre = m.mult(m);
        m = m.add(mCarre);
        System.out.println("test3 m= " + m);
    }

    public int permuteLigne(int i1, int i2) {
        int res = 0;
        Matrice copie = new Matrice(this.nbrLig, this.nbrCol);
        copie = this.copie();
        for (int j = 0; j < this.nbrCol; j++) {
            if (this.coeffs[i2][j] != this.coeffs[i1][j]) {
                this.coeffs[i2][j] = this.coeffs[i1][j];
                this.coeffs[i1][j] = copie.coeffs[i2][j];
            } else {
                res++;
            }
        }
        if (res == this.nbrCol) {
            res = 1;
        } else {
            res = -1;
        }
        //System.out.println("permutLigne = " +this);
        return res;
    }

    public Matrice transvection(int i1, int i2) {
        if (i1 >= this.nbrCol) {
            throw new Error("i1<nc impossible");
        }
        if (this.coeffs[i1][i1] == 0) {
            throw new Error("Le pivot est égale à zéro, errreur");
        }
        double p = (this.coeffs[i2][i1]) / (this.coeffs[i1][i1]);

        for (int i = 0; i < this.nbrCol; i++) {
            if (i == i1) {
                this.coeffs[i2][i] = 0;
            } else {
                this.coeffs[i2][i] = this.coeffs[i2][i] - p * this.coeffs[i1][i];
            }
        }

        return this;
    }

    public int lignePlusGrandPivot(int e) {
        int res = e;
        double epsilon_pivot = 0.00000001;
        for (int i = e; i < this.nbrLig; i++) {
            if ((Math.abs(this.coeffs[i][e]) > Math.abs(this.coeffs[res][e])) && Math.abs(this.coeffs[i][e]) > epsilon_pivot) {
                res = i;
                //System.out.println("res inte" + res);
            }
        }
        if (Math.abs(this.coeffs[res][e]) < epsilon_pivot) {
            res = -1;
        }
        return res;
    }
    
 

    public double Max(int e){
        double res=-1;
        double epsilon_pivot = 0.0000000000000001;
        for (int i=0; i<this.nbrLig;i++){ 
            if (epsilon_pivot<Math.abs(this.coeffs[i][e])){
                res = Math.abs(this.coeffs[i][e]);
            }
        }
        return res;
    }
    
    
    public ResGauss descenteGauss() {
        int ligPlusGrandPivot = 0;
        int nbEtape = 0;
        int signature = 0;
        Matrice ResGauss = new Matrice(this.nbrLig, this.nbrCol);
        System.out.println("============================================== \n matrice : \n" + this);

        for (int i = 0; i < this.nbrLig; i++) {
            System.out.println("------------ étape " + i + " ------------");

            ligPlusGrandPivot = this.lignePlusGrandPivot(i);

            if (ligPlusGrandPivot != -1) {
                System.out.println("ligne pivot max : " + ligPlusGrandPivot);
                this.permuteLigne(i, ligPlusGrandPivot);
                System.out.println("mat permutée = \n" + this);

                if (ligPlusGrandPivot != i) {
                    signature++;
                    System.out.println("signature = " + signature);
                }
            } else {
                System.out.println("ligne pivot max : " + i);
            }
            if (this.coeffs[i][i] == 0) {

            } else {

                for (int j = i + 1; j < this.nbrLig; j++) {
                    ResGauss = this.transvection(i, j);
                }
                System.out.println("mat après les transvections \n" + ResGauss);
                nbEtape++;
            }
        }
        System.out.println("matrice après descenteGauss : \n" + ResGauss);
        ResGauss res = new ResGauss(nbEtape, signature);
        System.out.println(res);
        return res;
    }

    public Matrice remonteeGauss() {
        Matrice ResGauss = new Matrice(this.nbrLig, this.nbrCol);
        for (int i = 0; i < this.nbrLig; i++) {
            for (int j = 0; j < this.nbrCol; j++) {
                ResGauss.coeffs[i][j] = this.coeffs[i][j] / this.coeffs[i][i];
            }
        }
        for (int i = this.nbrLig - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                ResGauss = ResGauss.transvection(i, j);
            }
        }
        System.out.println("après remontée : \n" + ResGauss);
        return ResGauss;
    }

    public static void test4() {
        double[][] vectTest1 = new double[][]{{0, 1, 2, 1}, {3, 4, 5, 2}, {6, 7, 8, 3}};
        double[][] vectTest2 = new double[][]{{0, 1, 2, 1}, {3, -4, 5, 2}, {6, 7, -8, 3}};
        Matrice matTest1 = new Matrice(3, 4);
        Matrice matTest2 = new Matrice(3, 4);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                matTest1.coeffs[i][j] = vectTest1[i][j];
                matTest2.coeffs[i][j] = vectTest2[i][j];
            }
        }
        matTest1.descenteGauss();
        matTest2.descenteGauss();
        matTest2.remonteeGauss();
    }

    public ResSup resolution(Matrice secondMembre) {
        Matrice sol;
        ResGauss etapes;
        Matrice inte;

        boolean solUnique = true;
        inte = this.concatCol(secondMembre);
        System.out.println("second mebre assemlé \n" + inte);
        etapes = inte.descenteGauss();
        if (etapes.rang < this.nbrLig) {
            System.out.println("le nombre de solution est infini");
            ResSup res1 = ResSup.pasDeSol();
            System.out.println(res1.toString1());
            return res1;
        } else {
            inte=inte.remonteeGauss();
            sol = inte.subCols(inte.nbrCol - 1, inte.nbrCol - 1);
            ResSup res2 = ResSup.solUnique(sol);
            //System.out.println(res2);
            return res2;
        }
    }

    public static void testResolution() {
        double[][] vectTest2 = new double[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        double[][] vectTest1 = new double[][]{{1}, {2}, {3}};
        Matrice matTest1 = new Matrice(3, 3);
        Matrice matTest2 = new Matrice(3, 1);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matTest1.coeffs[i][j] = vectTest2[i][j];
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                matTest2.coeffs[i][j] = vectTest1[i][j];
            }
        }
        ResSup res = matTest1.resolution(matTest2);
    }

    public static void main(String[] args) {
        // testAffiche();
        //testIdentite();
        //test1();
        //testConcatLig();
        Matrice m = new Matrice(3, 3);
        Matrice secondMembre = new Matrice(3, 0);
        /*m=matTest1();
        m.testmetAuCarre();
        m=matTest1();
        m.mult(matTest1());
        test3();*/
        //System.out.println(m.lignePlusGrandPivot(0));
        //test4();
        testResolution();

    }
}
