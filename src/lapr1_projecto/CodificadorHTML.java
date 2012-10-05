package LAPR1_Projecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class CodificadorHTML {

    static Scanner in = new Scanner(System.in);

    public static void convertParametrica(float x0, float y0, float z0, float a, float b, float c, int ex, Formatter write) throws FileNotFoundException {

        if (ex == 1) {
            write.format("<br>\nEqua&ccedil;&atilde;o Param&eacute;trica:<br>\n");
        }
        write.format("x=" + x0);
        if (a >= 0) {
            write.format("+" + a + "k<br>\n");
        } else {
            write.format(a + "k<br>\n");
        }
        write.format("y=" + y0);
        if (b >= 0) {
            write.format("+" + b + "k<br>\n");
        } else {
            write.format(b + "k<br>\n");
        }
        write.format("z=" + z0);
        if (c >= 0) {
            write.format("+" + c + "k<br>\n");
        } else {
            write.format(c + "k<br>\n");
        }


        return;
    }

    public static void convertCartesiana(float x0, float y0, float z0, float a, float b, float c, int ex, Formatter write) throws FileNotFoundException {



        if (ex == 1) {
            write.format("<br>Equa&ccedil;&atilde;o Cartesiana:<br>\n");
        }
        if (x0 > 0) {
            write.format("(x-" + x0 + ")/" + a + "=");
        } else {
            write.format("(x+" + (x0 * -1) + ")/" + a + "=");
        }
        if (y0 > 0) {
            write.format("(y-" + y0 + ")/" + b + "=");
        } else {
            write.format("(y+" + (y0 * -1) + ")/" + b + "=");
        }
        if (x0 > 0) {
            write.format("(z-" + z0 + ")/" + c);
        } else {
            write.format("(z+" + (z0 * -1) + ")/" + c);
        }
        if (a == 0 || b == 0 || c == 0) {
            write.format("<br>\n<br>\nN&atilde;o &eacute; a maneira mais correcta de representar!");
        }

        return;
    }

    public static void convertVectorial(float x0, float y0, float z0, float a, float b, float c, Formatter write) {
        write.format("<br>\nEqua&ccedil;&atilde;o Vetorial:<br>\n");
        write.format("(x,y,z)=(" + x0 + "," + y0 + "," + z0 + ")+k(" + a + "," + b + "," + c + ")<br>\n");

        return;
    }

    public static void convertpontos(float x0, float y0, float z0, float a, float b, float c, int ex) throws FileNotFoundException {

        Formatter write = new Formatter(new File("4.html"));
        CorpoInicioHTML(write);
        if (ex == 1) {
            convertVectorial(x0, y0, z0, a, b, c, write);
        }
        if (ex == 2) {
            convertParametrica(x0, y0, z0, a, b, c, 1, write);
        }
        if (ex == 3) {
            convertCartesiana(x0, y0, z0, a, b, c, 1, write);
        }
        write.format("Pontos da reta:(" + x0 + "," + y0 + "," + z0 + ")<br>\n");
        write.format("Vetor diretor:(" + a + "," + b + "," + c + ")<br>\n");
        CodificadorHTML.CorpoFimHTML(write);
        write.close();

        return;
    }

    static public void calcDist(float x0, float y0, float z0, float a, float b, float c, int ex) throws FileNotFoundException {
        float px0, py0, pz0, qx0, qy0, qz0, vx, vy, vz, d, k, dist;

        Formatter write = new Formatter(new File("5.html"));

        System.out.println("Insira as coordenadas do ponto.");
        System.out.print("x=");
        px0 = in.nextFloat();
        System.out.print("y=");
        py0 = in.nextFloat();
        System.out.print("z=");
        pz0 = in.nextFloat();
        CorpoInicioHTML(write);
        if (ex == 1) {
            convertVectorial(x0, y0, z0, a, b, c, write);
        }
        if (ex == 2) {
            convertParametrica(x0, y0, z0, a, b, c, 1, write);
        }
        if (ex == 3) {
            convertCartesiana(x0, y0, z0, a, b, c, 1, write);
        }
        write.format("P(" + px0 + "," + py0 + "," + pz0 + ")<br>\n");
        write.format("Dist=|PQ|<br>\n");
        write.format("Determinar plano que passa por P, perpendicular &agrave; reta.<br>\n");
        write.format("Ax+By+Cz+D=0, em que A,B,C &eacute; o vetor diretor da reta e x,y,z o ponto P.<br>\n");
        write.format("D=-Ax-By-Cz<br>\n");
        write.format("D=-" + a * -1 + "*(" + px0 + ")");
        if (b > 0) {
            write.format(b * -1 + "*(" + py0 + ")");
        } else {
            write.format("+" + b * -1 + "*(" + py0 + ")");
        }
        if (c > 0) {
            write.format(c * -1 + "*(" + pz0 + ")<br>\n");
        } else {
            write.format("+" + c * -1 + "*(" + pz0 + ")<br>\n");
        }
        d = -a * px0 - b * py0 - c * pz0;
        write.format("D=" + d + "<br>\n");
        write.format("<br>\n<br>\nSistema de Equa&ccedil;&otilde;es para determinar K<br>\n");
        convertplCartesiana(a, b, c, d, 5, write);
        convertParametrica(x0, y0, z0, a, b, c, 5, write);
        write.format("\nDeterminar K:<br>\n");
        write.format("A(x0+ak)+B(y0+bk)+C(z0+ck)+D=0<br>\n");
        write.format("k=(-Ax0-By0-Cz0-D)/(Aa+Bb+Cc<br>\n");
        if (a > 0) {
            write.format("k=(-" + a + "*(" + x0 + ")");
        } else {
            write.format("k=(" + a + "*(" + x0 + ")");
        }
        if (b > 0) {
            write.format("-" + b + "*(" + y0 + ")");
        } else {
            write.format(b + "*(" + y0 + ")");
        }
        if (c > 0) {
            write.format("-" + c + "*(" + z0 + ")");
        } else {
            write.format(c + "*(" + z0 + ")");
        }
        if (d > 0) {
            write.format("-" + d + ")/(" + a + "*(" + a + ")");
        } else {
            write.format(d + ")/(" + a + "*(" + a + ")");
        }
        if (b >= 0) {
            write.format("+" + b + "*(" + b + ")");
        } else {
            write.format(b + "*(" + b + ")");
        }
        if (c >= 0) {
            write.format("+" + c + "*(" + c + "))<br>\n");
        } else {
            write.format(c + "*(" + c + "))<br>\n");
        }
        k = (-a * x0 - b * y0 - c * z0 - d) / (a * a + b * b + c * c);
        write.format("K=" + k + "<br>\n");
        write.format("<br>\n<br>\nDeterminar Q<br>\n");
        write.format("x=x0+ka<br>\ny=y0+kb<br>\nz=z0+kc<br>\n");
        write.format("x=" + x0);
        if (k >= 0) {
            write.format("+" + k + "*(" + a + ")<br>\n");
        } else {
            write.format(k + "*(" + a + ")<br>\n");
        }
        write.format("y=" + y0);
        if (k >= 0) {
            write.format("+" + k + "*(" + b + ")<br>\n");
        } else {
            write.format(k + "*(" + b + ")<br>\n");
        }
        write.format("z=" + z0);
        if (k >= 0) {
            write.format("+" + k + "*(" + c + ")<br>\n");
        } else {
            write.format(k + "*(" + c + ")<br>\n");
        }
        qx0 = x0 + k * a;
        qy0 = y0 + k * b;
        qz0 = z0 + k * c;
        write.format("x=" + qx0 + "<br>\n");
        write.format("y=" + qy0 + "<br>\n");
        write.format("z=" + qz0 + "<br>\n");
        write.format("<br>\n<br>\nDeterminar o vetor PQ<br>\n");
        write.format("PQ=Q-P<br>\n");
        write.format("PQ=(xq,yq,zq)-(xp,yp,zp)<br>\n");
        write.format("PQ=(xq-xp,yq-yp,zq-zp)<br>\n");
        write.format("PQ=(" + qx0);
        if (px0 < 0) {
            write.format("+" + px0 * -1 + "," + qy0);
        } else {
            write.format(px0 * -1 + "," + qy0);
        }
        if (py0 < 0) {
            write.format("+" + py0 * -1 + "," + qz0);
        } else {
            write.format(py0 * -1 + "," + qz0);
        }
        if (pz0 < 0) {
            write.format("+" + pz0 * -1 + ")<br>\n");
        } else {
            write.format(pz0 * -1 + ")<br>\n");
        }
        vx = qx0 - px0;
        vy = qy0 - py0;
        vz = qz0 - pz0;
        write.format("PQ=(" + vx + "," + vy + "," + vz + ")<br>\n");
        write.format("<br>\n<br>\nM&oacute;dulo do vetor PQ:<br>\n");
        write.format("|PQ|=v(x�+y�+z�)<br>\n");
        write.format("|PQ|=v((" + vx + ")�+(" + vy + ")�+(" + vz + ")�)<br>\n");
        dist = (float) Math.sqrt(vx * vx + vy * vy + vz * vz);
        write.format("<br>\n<br>\n<br>\nA dist&acirc;ncia &eacute; de: " + dist + ".<br>\n");

        CodificadorHTML.CorpoFimHTML(write);
        write.close();

        return;
    }

    public static void convertplCartesiana(float a, float b, float c, float d, int ex, Formatter write) {

        if (ex == 6) {
            write.format("<br>\nEqua&ccedil;&atilde;o Cartesiana:<br>\n");
        }
        write.format(a + "x");
        if (b >= 0) {
            write.format("+" + b + "y");
        } else {
            write.format(b+"y");
        }
        if (c >= 0) {
            write.format("+" + c + "z");
        } else {
            write.format(c+"z");
        }
        if (d >= 0) {
            write.format("+" + d + "=0<br>\n");
        } else {
            write.format(d + "=0<br>\n");
        }

        return;
    }

    public static void convertplParametrica(float x0, float y0, float z0, float a, float b, float c, float a1, float b1, float c1, Formatter write) {
        write.format("<br>\nEqua&ccedil;&atilde;o Param&eacute;trica:<br>\n");
        write.format("x=" + x0);
        if (a >= 0) {
            write.format("+" + a + "k");
        } else {
            write.format(a + "k");
        }
        if (a1 >= 0) {
            write.format("+" + a1 + "k1<br>\n");
        } else {
            write.format(a1 + "k1<br>\n");
        }
        write.format("y=" + y0);
        if (b >= 0) {
            write.format("+" + b + "k");
        } else {
            write.format(b + "k");
        }
        if (b1 >= 0) {
            write.format("+" + b1 + "k1<br>\n");
        } else {
            write.format(b1 + "k1<br>\n");
        }
        write.format("z=" + z0);
        if (c >= 0) {
            write.format("+" + c + "k");
        } else {
            write.format(c + "k");
        }
        if (c1 >= 0) {
            write.format("+" + c1 + "k1<br>\n");
        } else {
            write.format(c1 + "k1<br>\n");
        }

        return;
    }

    public static void convertplVectorial(float x0, float y0, float z0, float a, float b, float c, float a1, float b1, float c1, Formatter write) {
        write.format("<br>\nEqua&ccedil;&atilde;o Vetorial:<br>\n");
        write.format("(x,y,z)=(" + x0 + "," + y0 + "," + z0 + ")+k(" + a + "," + b + "," + c + ")+k1(" + a1 + "," + b1 + "," + c1 + ")<br>\n");

        return;
    }

    public static void calcplCartesiana(float x0, float y0, float z0, float a, float b, float c, float a1, float b1, float c1, int ex, Formatter write) throws FileNotFoundException {
        float ac, bc, cc, dc;
        String[][] matriz = new String[3][3];

        ac = (b * c1 - (c * b1));
        bc = -(a * c1 - (a1 * c));
        cc = (a * b1 - (a1 * b));
        dc = (-ac * x0 - b * y0 - c * z0);
        write.format("<br>\n<br>\nF&oacute;rmula para calcular Laplace:<br>\n");
        write.format("A(x-x0) + B(y-y0) + C(z-z0)=0<br>\n");
        if (x0 >= 0) {
            matriz[0][0] = "x-" + x0;
        } else {
            matriz[0][0] = "x+" + Math.abs(x0);
        }
        if (y0 >= 0) {
            matriz[0][1] = "y-" + y0;
        } else {
            matriz[0][1] = "y+" + Math.abs(y0);
        }
        if (z0 >= 0) {
            matriz[0][2] = "z-" + z0;
        } else {
            matriz[0][2] = "z+" + Math.abs(z0);
        }
        matriz[1][0] = String.valueOf(a);
        matriz[1][1] = String.valueOf(b);
        matriz[1][2] = String.valueOf(c);
        matriz[2][0] = String.valueOf(a1);
        matriz[2][1] = String.valueOf(b1);
        matriz[2][2] = String.valueOf(c1);
        //apresentarCartesiana(matriz);
        write.format("A=" + ac + "<br>\n");
        write.format("B=" + bc + "<br>\n");
        write.format("C=" + cc + "<br>\n");
        write.format("D=-Ax-By-Cz<br>\n");
        write.format("D=-" + a * -1 + "*(" + x0 + ")");
        if (b > 0) {
            write.format(b * -1 + "*(" + y0 + ")");
        } else {
            write.format("+" + b * -1 + "*(" + y0 + ")");
        }
        if (c > 0) {
            write.format(c * -1 + "*(" + z0 + ")<br>\n");
        } else {
            write.format("+" + c * -1 + "*(" + z0 + ")<br>\n");
        }
        write.format("D=" + dc + "<br>\n");


        if (ex == 6) {
            convertplCartesiana(ac, bc, cc, dc, 6, write);
        }
        if (ex == 7) {
            calcplDist(ac, bc, cc, dc, write);
        }
        if (ex == 9) {
            plCSV(ac, bc, cc, dc, write);
        }

        return;
    }

    public static void calcInvplCartesiana(float ac, float bc, float cc, float dc, Formatter write) {
        int pontos[][] = new int[3][3];
        boolean check = true;

        do {
            for (int i = 0; i < pontos.length; i++) {
                for (int j = 0; j < pontos[i].length - 1; j++) {
                    pontos[i][j] = (int) (1 + (Math.random() * 10));
                }
            }
            for (int i = 0; i < pontos.length - 1; i++) {
                pontos[i][2] = (int) ((-ac * pontos[i][0] - bc * pontos[i][1] - dc) / cc);
            }
            check = LAPR1_Projecto.VerificiarColineares(pontos[0][0], pontos[0][1], pontos[0][2], pontos[1][0], pontos[1][1], pontos[1][2], pontos[2][0], pontos[2][1], pontos[2][2]);
        } while (check == true);

        write.format("P1=(x,y,z)&nbsp;&nbsp;&nbsp;P2=(x,y,z)&nbsp;&nbsp;&nbsp;P3=(x,y,z)<br>\n");
        write.format("x e y ser&atilde;o valores aleat&oacute;rios sendo que z ser&aacute; calculado na equa&ccedil;&atilde;o cartesiana.<br>\n");
        write.format("P1=(" + pontos[0][0] + "," + pontos[0][1] + "z)&nbsp;&nbsp;&nbsp;P2=(" + pontos[1][0] + "," + pontos[1][1] + ",z)&nbsp;&nbsp;&nbsp;P3=(" + pontos[2][0] + "," + pontos[2][1] + ",z)<br>\n");
        write.format("<br>\nAx+By+Cz+D=0<br>\n");
        write.format("z=(-A*x-B*y-D)/C<br>\n");
        write.format("Pz1=(");
        write.format(ac * -1 + "*(" + pontos[0][0] + ")");
        if (bc > 0) {
            write.format(bc * -1 + "*(" + pontos[0][1] + ")");
        } else {
            write.format("+" + bc * -1 + "*(" + pontos[0][1] + ")");
        }
        if (dc > 0) {
            write.format(dc * -1 + ")/" + cc + "<br>\n");
        } else {
            write.format("+" + dc * -1 + ")/" + cc + "<br>\n");
        }
        write.format("Pz2=(");
        write.format(ac * -1 + "*(" + pontos[1][0] + ")");
        if (bc > 0) {
            write.format(bc * -1 + "*(" + pontos[1][1] + ")");
        } else {
            write.format("+" + bc * -1 + "*(" + pontos[1][1] + ")");
        }
        if (dc > 0) {
            write.format(dc * -1 + ")/" + cc + "<br>\n");
        } else {
            write.format("+" + dc * -1 + ")/" + cc + "<br>\n");
        }
        write.format("Pz3=(");
        write.format(ac * -1 + "*(" + pontos[2][0] + ")");
        if (bc > 0) {
            write.format(bc * -1 + "*(" + pontos[2][1] + ")");
        } else {
            write.format("+" + bc * -1 + "*(" + pontos[2][1] + ")");
        }
        if (dc > 0) {
            write.format(dc * -1 + ")/" + cc + "<br>\n");
        } else {
            write.format("+" + dc * -1 + ")/" + cc + "<br>\n");
        }
        write.format("<br>\nP1=(" + pontos[0][0] + "," + pontos[0][1] + "," + pontos[0][0] + ")&nbsp;&nbsp;&nbsp;P2=(" + pontos[1][0] + "," + pontos[1][1] + "," + pontos[1][2] + ")&nbsp;&nbsp;&nbsp;P3=(" + pontos[2][0] + "," + pontos[2][1] + "," + pontos[2][2] + ")<br>\n");
        write.format("x0=" + pontos[0][0] + "<br>\n");
        write.format("y0=" + pontos[0][1] + "<br>\n");
        write.format("z0=" + pontos[0][2] + "<br>\n");
        write.format("(a,b,c)=P1-P2<br>\n");
        write.format("a=" + (pontos[0][0] - pontos[1][0]) + "<br>\n");
        write.format("b=" + (pontos[0][1] - pontos[1][1]) + "<br>\n");
        write.format("c=" + (pontos[0][2] - pontos[1][2]) + "<br>\n");
        write.format("(a1,b1,c1)=P2-P3<br>\n");
        write.format("a1=" + (pontos[1][0] - pontos[2][0]) + "<br>\n");
        write.format("b1=" + (pontos[1][1] - pontos[2][1]) + "<br>\n");
        write.format("c1=" + (pontos[1][2] - pontos[2][2]) + "<br>\n");

        convertplVectorial(pontos[0][0], pontos[0][1], pontos[0][2], pontos[0][0] - pontos[1][0], pontos[0][1] - pontos[1][1], pontos[0][2] - pontos[1][2], pontos[1][0] - pontos[2][0], pontos[1][1] - pontos[2][1], pontos[1][2] - pontos[2][2], write);
        convertplParametrica(pontos[0][0], pontos[0][1], pontos[0][2], pontos[0][0] - pontos[1][0], pontos[0][1] - pontos[1][1], pontos[0][2] - pontos[1][2], pontos[1][0] - pontos[2][0], pontos[1][1] - pontos[2][1], pontos[1][2] - pontos[2][2], write);

        return;
    }

    public static void calcplDist(float a, float b, float c, float d, Formatter write) throws FileNotFoundException {
        float px0, py0, pz0, k, qx0, qy0, qz0, vx, vy, vz, dist;

        System.out.println("Insira as coordenadas do ponto.");
        System.out.print("x=");
        px0 = in.nextFloat();
        System.out.print("y=");
        py0 = in.nextFloat();
        System.out.print("z=");
        pz0 = in.nextFloat();

        write.format("P(" + px0 + "," + py0 + "," + pz0 + ")<br>\n");
        write.format("Dist=|PQ|<br>\n");
        write.format("Determinar reta que passa por P, perpendicular ao plano.<br>\n");
        write.format("A,B,C s&atilde;o os pontos da equa&ccedil;&atilde;o e P(x0,y0,z0)<br>\n");
        convertParametrica(px0, py0, pz0, a, b, c, 7, write);
        write.format("<br>\nSistema de Equa&ccedil;&atilde;es para determinar K<br>\n");
        convertplCartesiana(a, b, c, d, 7, write);
        convertParametrica(px0, py0, pz0, a, b, c, 7, write);
        write.format("<br>\nDeterminar K:<br>\n");
        write.format("A(x0+ak)+B(y0+bk)+C(z0+ck)+D=0<br>\n");
        write.format("k=(-Ax0-By0-Cz0-D)/(Aa+Bb+Cc<br>\n");
        if (a > 0) {
            write.format("k=(-" + a + "*(" + px0 + ")");
        } else {
            write.format("k=(" + a + "*(" + px0 + ")");
        }
        if (b > 0) {
            write.format("-" + b + "*(" + py0 + ")");
        } else {
            write.format(b + "*(" + py0 + ")");
        }
        if (c > 0) {
            write.format("-" + c + "*(" + pz0 + ")");
        } else {
            write.format(c + "*(" + pz0 + ")");
        }
        if (d > 0) {
            write.format("-" + d + ")/(" + a + "*(" + a + ")");
        } else {
            write.format(d + ")/(" + a + "*(" + a + ")");
        }
        if (b >= 0) {
            write.format("+" + b + "*(" + b + ")");
        } else {
            write.format(b + "*(" + b + ")");
        }
        if (c >= 0) {
            write.format("+" + c + "*(" + c + "))<br>\n");
        } else {
            write.format(c + "*(" + c + "))<br>\n");
        }
        k = (-a * px0 - b * py0 - c * pz0 - d) / (a * a + b * b + c * c);
        write.format("K=" + k + "<br>\n");
        write.format("<br>\nDeterminar Q<br>\n");
        write.format("x=x0+ka<br>\ny=y0+kb<br>\nz=z0+kc<br>\n");
        write.format("x=" + px0);
        if (k >= 0) {
            write.format("+" + k + "*(" + a + ")<br>\n");
        } else {
            write.format(k + "*(" + a + ")<br>\n");
        }
        write.format("y=" + py0);
        if (k >= 0) {
            write.format("+" + k + "*(" + b + ")<br>\n");
        } else {
            write.format(k + "*(" + b + ")<br>\n");
        }
        write.format("z=" + pz0);
        if (k >= 0) {
            write.format("+" + k + "*(" + c + ")<br>\n");
        } else {
            write.format(k + "*(" + c + ")<br>\n");
        }
        qx0 = px0 + k * a;
        qy0 = py0 + k * b;
        qz0 = pz0 + k * c;
        write.format("x=" + qx0 + "<br>\n");
        write.format("y=" + qy0 + "<br>\n");
        write.format("z=" + qz0 + "<br>\n");
        write.format("<br>\nDeterminar o vetor PQ<br>\n");
        write.format("PQ=Q-P<br>\n");
        write.format("PQ=(xq,yq,zq)-(xp,yp,zp)<br>\n");
        write.format("PQ=(xq-xp,yq-yp,zq-zp)<br>\n");
        write.format("PQ=(" + qx0);
        if (px0 < 0) {
            write.format("+" + px0 * -1 + "," + qy0);
        } else {
            write.format(px0 * -1 + "," + qy0);
        }
        if (py0 < 0) {
            write.format("+" + py0 * -1 + "," + qz0);
        } else {
            write.format(py0 * -1 + "," + qz0);
        }
        if (pz0 < 0) {
            write.format("+" + pz0 * -1 + ")<br>\n");
        } else {
            write.format(pz0 * -1 + ")<br>\n");
        }
        vx = qx0 - px0;
        vy = qy0 - py0;
        vz = qz0 - pz0;
        write.format("PQ=(" + vx + "," + vy + "," + vz + ")<br>\n");
        write.format("<br>\nM&oacute;dulo do vetor PQ:<br>\n");
        write.format("|PQ|=v(x�+y�+z�)<br>\n");
        write.format("|PQ|=v((" + vx + ")�+(" + vy + ")�+(" + vz + ")�)<br>\n");
        dist = (float) Math.sqrt(vx * vx + vy * vy + vz * vz);
        write.format("<br>\n<br>\nA dist�ncia &eacute; de: " + dist + ".<br>\n");

        return;
    }

    public static void CSV(float x0, float y0, float z0, float a, float b, float c, Formatter write) throws FileNotFoundException {
        float em, em1, eb, eb1, tmpx, tmpy, zcont;
        int zmin, zmax, zpon;

        em = a / c;
        eb = -(z0 * a) / c + x0;  //x=mz+b
        em1 = b / c;
        eb1 = -(z0 * b) / c + y0; //y=mz+b

        System.out.println("Insira o Limite Inferior do eixo de ZZ.");
        zmin = in.nextInt();
        System.out.println("Insira o Limite Superior do eixo de ZZ.");
        zmax = in.nextInt();
        System.out.println("Insira o n�mero de pontos que pretende visualizar no eixo de XX.");
        zpon = in.nextInt();
        zcont = (zmax - zmin + 1) / zpon;

        write.format("<br>\nGrupo DKL04 - LAPR1 2011-12<br>\n<br>\n");
        write.format("Representa&ccedil;&atilde;o da recta x=%fz", em);
        if (eb >= 0) {
            write.format("+%f", eb);
        } else {
            write.format("%f", eb);
        }
        write.format("&nbsp;&nbsp;&nbsp;y=%fz", em1);
        if (eb1 >= 0) {
            write.format("+%f<br>\n", eb1);
        } else {
            write.format("%f<br>\n", eb1);
        }
        write.format("Dimens&otilde;es<br>\n<br>\nEixo ZZ<br>\n");
        write.format("Limite Inferior:%d<br>\n", zmin);
        write.format("Limite Superior:%d<br>\n", zmax);
        write.format("N&uacute;mero de pontos:%d<br>\n<br>\n", zpon);
        write.format("Proje&ccedil;&atilde;o no eixo XZ<br>\n<br>\n");

        //Tabela HTML
        write.format("<table border=1><br>\n");
        write.format("<tr><td>&nbsp;</td>");
        for (int x = zmin; x < zmax; x += zcont) {
            write.format("<td>%d</td>", x);
        }
        write.format("</tr>");
        write.format("<br>\n<tr><td>x</td>");
        for (int x = zmin; x < zmax; x += zcont) {
            tmpx = em * x + eb;
            write.format("<td>%f</td>", tmpx);
        }
        write.format("</tr><br>\n");
        write.format("</table>");
        write.format("\nProje��o no eixo do YZ<br>\n\n");
        write.format("<table border=1><br>\n");
        write.format("<tr><td>&nbsp;</td>");
        for (int y = zmin; y < zmax; y += zcont) {
            write.format("<td>%d</td>", y);
        }
        write.format("<br>\n<tr><td>y</td>");
        for (int y = zmin; y < zmax; y += zcont) {
            tmpy = em1 * y + eb1;
            write.format("<td>%f</td>", tmpy);
        }
        write.format("</tr><br>\n");
        write.format("</table><br>\n");

        return;
    }

    public static void plCSV(float a, float b, float c, float d, Formatter write) throws FileNotFoundException {
        int xmin, xmax, ymin, ymax, xpon, ypon;
        float contx, conty;

        System.out.println("Insira o Limite Inferior do eixo de XX.");
        xmin = in.nextInt();
        System.out.println("Insira o Limite Superior do eixo de XX.");
        xmax = in.nextInt();
        System.out.println("Insira o n�mero de pontos que pretende visualizar no eixo de XX.");
        xpon = in.nextInt();
        contx = (xmax - xmin + 1) / xpon;
        System.out.println("Insira o Limite Inferior do eixo de YY.");
        ymin = in.nextInt();
        System.out.println("Insira o Limite Superior do eixo de YY.");
        ymax = in.nextInt();
        System.out.println("Insira o n�mero de pontos que pretende visualizar no eixo de YY.");
        ypon = in.nextInt();
        conty = (ymax - ymin + 1) / ypon;
        write.format("<br>\nGrupo DKL04 - LAPR1 2011-12<br>\n<br>\n");
        write.format("Representa&ccedil;&atilde;o do plano z=(%f/%f)x+(%f/%f)y+(%f/%f)<br>\n", a, c, b, c, d, c);
        write.format("<br>\nDimens&otilde;es<br>\n<br>\nEixo XX<br>\n");
        write.format("Limite Inferior:%d<br>\nLimite Superior:%d<br>\n", xmin, xmax);
        write.format("N&uacute;mero de pontos:%d<br>\n<br>\n", xpon);
        write.format("Eixo YY<br>\n");
        write.format("Limite Inferior:%d<br>\nLimite Superior:%d<br>\n", ymin, ymax);
        write.format("N&uacute;mero de pontos:%d<br>\n<br>\n", ypon);
        //Tabela HTML
        write.format("Valores<br>");
        write.format("<table border=1><br>\n");
        write.format("<tr><td>&nbsp;&nbsp;</td>");
        for (float y = ymin; y < ypon; y += conty) {
            write.format("<td>%.2f</td>", y);
        }
        write.format("</tr><br>\n");
        for (float x = xmin; x < xpon; x += contx) {
            write.format("<tr><td>%.2f</td>", x);
            for (float y = ymin; y < ypon; y += conty) {
                write.format("<td>%.2f</td>", ((a / c) * x + (b / c) * y + (d / c)));
            }
            write.format("</tr><br>\n");
        }
        write.format("</table>\n");

        return;
    }

    public static void CorpoInicioHTML(Formatter Ex) throws FileNotFoundException {

        String titulo;

        System.out.println("Introduza o t�tulo que deseja para o write!");
        titulo = in.next();
        Ex.format("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n\"http://www.w3.org/TR/html4/loose.dtd\">\n");
        Ex.format("<html>\n");
        Ex.format("<head><title>" + titulo + "</title>\n");
        Ex.format("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" >\n</head>");
        Ex.format("<body>\n");

        return;
    }

    public static void CorpoFimHTML(Formatter Ex) throws FileNotFoundException {
		Ex.format("Grupo DKL04 -  <a href = \"http://www.dei.isep.ipp.pt\">Departamento de Engenharia Informática</a><br>\n");
    	Ex.format("<a href=\"http://www.isep.ipp.pt\" ><img src=\"http://portugal.vortal.biz/files/casos_sucesso/logo_ISEP.jpg\"alt=\"Instituto Superior de Engenharia do Porto\"></a><br>\n");
        Ex.format("</body>\n");
        Ex.format("</html>");

        return;
    }

    public static void codificarHTML2(char opcaoS, char opcaoO, float a, float b, float c, float d, float e, float f, float r1, float r2, float r3) throws FileNotFoundException {


        Formatter ex2 = new Formatter(new File("FichEx2.html"));
        CorpoInicioHTML(ex2);
        if (opcaoS == '1') {
            ex2.format("Equa&ccedil;&atilde;o Vectorial: <br><br>\n");
            ex2.format("PontoA = (" + a + "," + b + "," + c + ")<br>\n");
            ex2.format("PontoB = (" + d + "," + e + "," + f + ")<br>\n");
            ex2.format("&nbsp;&nbsp;u = B - A = (" + d + "," + e + "," + f + ") - (" + a + "," + b + "," + c + ") = (" + (r1) + "," + (r2) + "," + (r3) + ")<br>\n\n");
            ex2.format("Recta: <br>\n");
            ex2.format("&nbsp;&nbsp;(x,y,z) = (" + a + "," + b + "," + c + ") + k(" + (r1) + "," + (r2) + "," + (r3) + ")<br>\n");
        } else if (opcaoS == '2') {
            ex2.format("Equa&ccedil;&atilde;o Vectorial: <br><br>\n");
            ex2.format("(x,y,z) = (" + a + "," + b + "," + c + ") + k(" + (r1) + "," + (r2) + "," + (r3) + ")<br>\n");
        }

        ex2.format("<br>Equa&ccedil;&atilde;o Param&eacute;trica: <br><br>\n");


        if (r1 >= 0) {
            ex2.format("&nbsp;&nbsp;x = " + a + " + " + (r1) + "k<br>\n");

        } else {
            ex2.format("&nbsp;&nbsp;x = " + a + (r1) + "k<br>\n");

        }
        if (r2 >= 0) {
            ex2.format("&nbsp;&nbsp;y = " + b + " + " + (r2) + "k<br>\n");

        } else {
            ex2.format("&nbsp;&nbsp;y = " + b + (r2) + "k<br>\n");

        }
        if (r3 >= 0) {
            ex2.format("&nbsp;&nbsp;z = " + c + " + " + (r3) + "k<br>\n");

        } else {
            ex2.format("&nbsp;&nbsp;z = " + c + (r3) + "k<br>\n");

        }

        ex2.format("<br>Equa&ccedil;&atilde;o Cartesiana: <br><br>\n");

        ex2.format("&nbsp;&nbsp;u = (" + (r1) + "," + (r2) + "," + (r3) + ")<br>\n");

        ex2.format("&nbsp;&nbsp;P = (" + a + "," + b + "," + c + ")<br>\n");

        ConverterValores(a, b, c, r1, r2, r3, ex2);
        if (r1 == 0 || r2 == 0 || r3 == 0) {
            ex2.format("&nbsp;&nbsp;N&atilde;o &eacute; a forma mais correcta de apresentar!!!<br>\n");
        }

        CorpoFimHTML(ex2);
        ex2.close();


    }

    public static void ConverterValores(float a, float b, float c, float r1, float r2, float r3, Formatter Ex) {

        if (a >= 0) {
            Ex.format("X - " + a + "/" + r1 + "<br>\n");
        } else {
            Ex.format("X + " + a + "/" + r1 + "<br>\n");
        }
        if (b >= 0) {
            Ex.format("Y - " + b + "/" + r2 + "<br>\n");
        } else {
            Ex.format("Y + " + b + "/" + r2 + "<br>\n");
        }
        if (c >= 0) {
            Ex.format("Z - " + c + "/" + r3 + "<br>\n");
        } else {
            Ex.format("Z + " + c + "/" + r3 + "<br>\n");
        }

    }

    public static void codificarHTML3(char opcao, char op2, float a, float b, float c, float d, float e, float f, float g, float h, float i, float r1, float r2, float r3, float r4, float r5, float r6, String[][] matriz) throws FileNotFoundException {


        int x1, x2, x3, tam1, tam2, tam3;
        String for1, for2, for3, v1, v2, v3, v4, v5, v6, v7, v8, v9, mensa1, mensa2, mensa3;
        int z1, z2, z3, z4, z5, z6, z7, z8, z9, res1, res2, res3, res4, res5, res6, res7, res8, res9;

        Formatter ex3 = new Formatter(new File("FichEx3.html"));

        CorpoInicioHTML(ex3);
        if (opcao == '1') {
            ex3.format("Ponto A: (" + a + "," + b + "," + c + ")<br>\n");
            ex3.format("Ponto B: (" + d + "," + e + "," + f + ")<br>\n");
            ex3.format("Ponto C: (" + g + "," + h + "," + i + ")<br>\n");
            ex3.format("Vetor criado a partir do Ponto A e B: <br>\n");
            ex3.format("u = (" + r1 + "," + r2 + "," + r3 + ")<br>\n");
            ex3.format("Vetor criado a partir do Ponto B e C: <br>\n");
            ex3.format("v = (" + r4 + "," + r5 + "," + r6 + ")<br>\n");
            ex3.format("Equa&ccedil;&atilde;o vetorial no plano: <br>\n");
            ex3.format("&nbsp;&nbsp;a: (x,y,z) = (" + a + "," + b + "," + c + ") + k1(" + d + "," + e + "," + f + ") + k2(" + g + "," + h + "," + i + ")<br>\n");
        } else if (opcao == '2') {
            ex3.format("Ponto A: (" + a + "," + b + "," + c + ")<br>\n");
            ex3.format("Vetor B: (" + d + "," + e + "," + f + ")<br>\n");
            ex3.format("Vetor C: (" + g + "," + h + "," + i + ")<br>\n");

        } else if (opcao == '3') {

            ex3.format("Ponto A: (" + a + "," + b + "," + c + ")<br>\n");
            ex3.format("Ponto B: (" + d + "," + e + "," + f + ")<br>\n");
            ex3.format("Vetor C: (" + g + "," + h + "," + i + ")<br>\n");
            ex3.format("\n");
            r1 = a - d;
            r2 = b - e;
            r3 = c - f;
            ex3.format("Vetor criado a partir do ponto A e B: ");
            ex3.format("u = (" + r1 + "," + r2 + "," + r3 + ")");

        }
        ex3.format("<br>Equa&ccedil;&atilde;o Param&eacute;trica no plano: <br>\n");
        if (d >= 0 && g >= 0) {
            ex3.format("X = " + a + " + " + d + "k1 + " + g + "k2<br>\n");
        } else if (d < 0 && g >= 0) {
            ex3.format("X = " + a + " " + d + "k1 + " + g + "k2<br>\n");
        } else if (d < 0 && g < 0) {
            ex3.format("X = " + a + " " + d + "k1 " + g + "k2<br>\n");
        } else {
            ex3.format("X = " + a + " + " + d + "k1 " + g + "k2<br>\n");
        }
        if (e >= 0 && h >= 0) {
            ex3.format("Y = " + b + " + " + e + "k1 + " + h + "k2<br>\n");
        } else if (e < 0 && h >= 0) {
            ex3.format("Y = " + b + " " + e + "k1 + " + h + "k2<br>\n");
        } else if (e < 0 && h < 0) {
            ex3.format("Y = " + b + " " + e + "k1 " + h + "k2<br>\n");
        } else {
            ex3.format("Y = " + b + " + " + e + "k1 " + h + "k2<br>\n");
        }
        if (f >= 0 && i >= 0) {
            ex3.format("Z = " + c + " + " + f + "k1 + " + i + "k2<br>\n");
        } else if (f < 0 && i >= 0) {
            ex3.format("Z = " + c + " " + f + "k1 + " + i + "k2<br>\n");
        } else if (f < 0 && i < 0) {
            ex3.format("Z = " + c + " " + f + "k1 " + i + "k2<br>\n");
        } else {
            ex3.format("Z = " + c + " + " + f + "k1 " + i + "k2<br>\n");
        }
        
        x1 = (int) Float.parseFloat(matriz[1][0]);
        x2 = (int) Float.parseFloat(matriz[1][1]);
        x3 = (int) Float.parseFloat(matriz[1][2]);

        if (x1 >= 0) {
            for1 = "=0<=>" + matriz[1][0] + "*(-1)^2+1*";
        } else {
            for1 = "=0<=>" + matriz[1][0] + "*(-1)^2+1*";
        }
        if (x2 >= 0) {
            for2 = "+" + matriz[1][1] + "*(-1)^2+1*";
        } else {
            for2 = matriz[1][1] + "*(-1)^2+1*";
        }
        if (x3 >= 0) {
            for3 = "+" + matriz[1][2] + "*(-1)^2+1*";
        } else {
            for3 = matriz[1][2] + "*(-1)^2+1*";
        }

        tam1 = for1.length();
        tam2 = for2.length();
        tam3 = for3.length();

        v1 = matriz[0][0];
        v2 = matriz[0][1];
        v3 = matriz[0][2];
        v4 = matriz[1][0];
        v5 = matriz[1][1];
        v6 = matriz[1][2];
        v7 = matriz[2][0];
        v8 = matriz[2][1];
        v9 = matriz[2][2];

        z1 = v1.length();
        z2 = v2.length();
        z3 = v3.length();
        z4 = v4.length();
        z5 = v5.length();
        z6 = v6.length();
        z7 = v7.length();
        z8 = v8.length();
        z9 = v9.length();
        res1 = z1 + z2 + z3 + 2;
        res2 = z4 + z5 + z6 + 2;
        res3 = z7 + z8 + z9 + 2;
        res4 = z2 + z3 + 1;
        res5 = z8 + z9 + 1;
        res6 = z1 + z3 + 1;
        res7 = z7 + z9 + 1;
        res8 = z1 + z2 + 1;
        res9 = z7 + z8 + 1;

        mensa1 = mensagem1(res4, res5, z2, z3, z8, z9);
        mensa2 = mensagem2(res6, res7, z1, z3, z7, z9);
        mensa3 = mensagem3(res8, res9, z1, z2, z7, z8);


        ex3.format("<table border=0><br>\n");

        if (res1 >= res2 && res1 >= res3) {
            ex3.format("&nbsp;&nbsp;");
            ex3.format("<tr><td>|%" + z1 + "s</td><td>%" + z2 + "s</td><td>%" + z3 + "s|</td><td>|%s|</td><td>%" + mensa1 + "</td><td>%" + tam2 + "s|</td><td>%" + mensa2 + "</td><td>%" + tam3 + "s|</td><td>%" + mensa3 + "</td></tr><br>\n", matriz[0][0], matriz[0][1], matriz[0][2], " ", matriz[0][1], matriz[0][2], " ", matriz[1][0], matriz[1][2], " ", matriz[1][0], matriz[1][1]);
            ex3.format("&nbsp;&nbsp;");
            System.out.println("---->" + tam1);
            ex3.format("<tr><td>|%" + z1 + "s</td><td>%" + z2 + "s</td><td>%" + z3 + "s|</td><td>%" + tam1 + "s|</td><td>%" + mensa1 + "</td><td>%" + tam2 + "s|</td><td>%" + mensa2 + "</td><td>%" + tam3 + "s|</td><td>%" + mensa3 + "</td></tr><br>\n", matriz[1][0], matriz[1][1], matriz[1][2], for1, " ", " ", for2, " ", " ", for3, " ", " ");
            ex3.format("&nbsp;&nbsp;");
            ex3.format("<tr><td>|%" + z1 + "s</td><td>%" + z2 + "s</td><td>%" + z3 + "s|</td><td>%s|</td><td>%" + mensa1 + "</td><td>%" + tam2 + "s|</td><td>%" + mensa2 + "</td><td>%" + tam3 + "s|</td><td>%" + mensa3 + "</td></tr><br>\n", matriz[2][0], matriz[2][1], matriz[2][2], " ", matriz[2][1], matriz[2][2], " ", matriz[2][0], matriz[2][2], " ", matriz[2][0], matriz[2][1]);
            ex3.format("</table><br>3\n");
        } else if (res2 >= res1 && res2 >= res3) {
            ex3.format("&nbsp;&nbsp;");
            ex3.format("<tr><td>|%" + z4 + "s</td><td>%" + z5 + "s</td><td>%" + z6 + "s|</td><td>%s|</td><td>%" + mensa1 + "</td><td>%" + tam2 + "s|%" + mensa2 + "</td><td>%" + tam3 + "s|</td><td>%" + mensa3 + "</td></tr><br>\n", matriz[0][0], matriz[0][1], matriz[0][2], " ", matriz[0][1], matriz[0][2], " ", matriz[1][0], matriz[1][2], " ", matriz[1][0], matriz[1][1]);
            ex3.format("&nbsp;&nbsp;");
            ex3.format("<tr><td>|%" + z4 + "s</td><td>%" + z5 + "s</td><td>%" + z6 + "s|</td><td>%" + tam1 + "s|</td><td>%" + mensa1 + "</td><td>%" + tam2 + "s|%" + mensa2 + "</td><td>%" + tam3 + "s|</td><td>%" + mensa3 + "</td></tr><br>\n", matriz[1][0], matriz[1][1], matriz[1][2], for1, " ", " ", for2, " ", " ", for3, " ", " ");
            ex3.format("&nbsp;&nbsp;");
            ex3.format("<tr><td>|%" + z4 + "s</td><td>%" + z5 + "s</td><td>%" + z6 + "s|</td><td>%s|</td><td>%" + mensa1 + "</td><td>%" + tam2 + "s|%" + mensa2 + "</td><td>%" + tam3 + "s|</td><td>%" + mensa3 + "</td></tr><br>\n", matriz[2][0], matriz[2][1], matriz[2][2], " ", matriz[2][1], matriz[2][2], " ", matriz[2][0], matriz[2][2], " ", matriz[2][0], matriz[2][1]);
            ex3.format("</table><br>3\n");
        } else if (res3 >= res1 && res3 >= res2) {
            ex3.format("&nbsp;&nbsp;");
            ex3.format("<tr><td>|%" + z7 + "s</td><td>%" + z8 + "s</td><td>%" + z9 + "s|</td><td>%s|</td><td>%" + mensa1 + "</td><td>%" + tam2 + "s|%" + mensa2 + "</td><td>%" + tam3 + "s|</td><td>%" + mensa3 + "</td></tr><br>\n", matriz[0][0], matriz[0][1], matriz[0][2], " ", matriz[0][1], matriz[0][2], " ", matriz[1][0], matriz[1][2], " ", matriz[1][0], matriz[1][1]);
            ex3.format("&nbsp;&nbsp;");
            ex3.format("<tr><td>|%" + z7 + "s</td><td>%" + z8 + "s</td><td>%" + z9 + "s|</td><td>%s|</td><td>%" + mensa1 + "</td><td>%" + tam2 + "s|%" + mensa2 + "</td><td>%" + tam3 + "s|</td><td>%" + mensa3 + "</td></tr><br>\n", matriz[1][0], matriz[1][1], matriz[1][2], for1, " ", " ", for2, " ", " ", for3, " ", " ");
            ex3.format("&nbsp;&nbsp;");
            ex3.format("<tr><td>|%" + z7 + "s</td><td>%" + z8 + "s</td><td>%" + z9 + "s|</td><td>%s|</td><td>%" + mensa1 + "</td><td>%" + tam2 + "s|%" + mensa2 + "</td><td>%" + tam3 + "s|</td><td>%" + mensa3 + "</td></tr><br>\n", matriz[2][0], matriz[2][1], matriz[2][2], " ", matriz[2][1], matriz[2][2], " ", matriz[2][0], matriz[2][2], " ", matriz[2][0], matriz[2][1]);
            ex3.format("</table><br>3\n");
        }

        CorpoFimHTML(ex3);
        ex3.close();

    }

    public static String mensagem1(int res4, int res5, int z2, int z3, int z8, int z9) throws FileNotFoundException {

        String mens1 = null;

        if (res4 >= res5) {
            mens1 = z2 + "s  </td><td>%" + z3 + "s|";
        } else {
            mens1 = z8 + "s  </td><td>%" + z9 + "s|";
        }

        return mens1;

    }

    public static String mensagem2(int res6, int res7, int z1, int z3, int z7, int z9) throws FileNotFoundException {

        String mens1 = null;

        if (res6 >= res7) {
            mens1 = z1 + "s  </td><td>%" + z3 + "s|";
        } else {
            mens1 = z7 + "s  </td><td>%" + z9 + "s|";
        }

        return mens1;

    }

    public static String mensagem3(int res8, int res9, int z1, int z2, int z7, int z8) throws FileNotFoundException {

        String mens1 = null;

        if (res8 >= res9) {
            mens1 = z1 + "s  </td><td>%" + z2 + "s|";
        } else {
            mens1 = z7 + "s  </td><td>%" + z8 + "s|";
        }

        return mens1;

    }
}
