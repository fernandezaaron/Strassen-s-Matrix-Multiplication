import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class mainFrame extends JFrame {

    JScrollPane scroll1, scroll2, scroll3, c1scroll,c2scroll,c3scroll,c4scroll, csscroll, centermidscroll;
    JPanel upper, mother, center, centermid, centerdown, codesimulation;
    JTextArea c1,c2,c3,c4, cs;
    JLabel enterSize;
    JTable a = new JTable();
    JTable b = new JTable();
    JTable c = new JTable();
    DefaultTableModel dtm1 = new DefaultTableModel();
    DefaultTableModel dtm2 = new DefaultTableModel();
    DefaultTableModel dtm3 = new DefaultTableModel();
    JTextField row, column;
    JButton enter, calculate, clear, random, codesim,computation,next,prev;
    int clickrow = 0;
    int clickcol = 0;
    int counti = 0;
    int countj = 0;
    int csclick=0;

    public mainFrame() {
        super("Strassen's Matrix Multiplication");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        row = new JTextField();
        column = new JTextField();

        mother = new JPanel(new FlowLayout());

        center = new JPanel(new BorderLayout());

        random = new JButton(new AbstractAction("Random") {
            @Override
            public void actionPerformed(ActionEvent e) {
                double r1;
                //sets random values to Table A
                int size = Integer.parseInt(row.getText());
                for(int i = 0; i < size; i++) {
                    for(int j=0; j<size; j++) {
                        int parsingDouble;
                        r1 = Math.random() * (100 - 1 + 1) + 1;
                        parsingDouble = (int) r1;
                        dtm1.setValueAt(parsingDouble, i, j);
                    }
                }

                double r2;
                for(int i=0; i<size; i++){
                    for(int j = 0; j < size; j++) {
                        int parsingInt;
                        r2 = Math.random() * (100-1 + 1) + 1;
                        parsingInt = (int) r2;
                        dtm2.setValueAt(parsingInt, i, j);
                     }
                }
            }
        });

        enter = new JButton(new AbstractAction("Generate Table") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(row.getText());
                if(size%2 == 1) {
                    JOptionPane.showMessageDialog(null, "STRASSEN ALGO ONLY ACCEPTS SQUARED MATRICES", "WARNING", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    //creates Table A
                    dtm1 = new DefaultTableModel(size, size);
                    a = new JTable(dtm1);
                    scroll1 = new JScrollPane(a, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scroll1.getViewport().setPreferredSize(new Dimension(400, 300));

                    //creates Table B
                    dtm2 = new DefaultTableModel(size, size);
                    b = new JTable(dtm2);
                    scroll2 = new JScrollPane(b, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scroll2.getViewport().setPreferredSize(new Dimension(400, 300));

                    //creates Table C
                    dtm3 = new DefaultTableModel(size, size);
                    c = new JTable(dtm3);
                    scroll3 = new JScrollPane(c, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scroll3.getViewport().setPreferredSize(new Dimension(400, 300));

                    centermid.add(scroll1);
                    centermid.add(scroll2);
                    centermid.add(scroll3);
                    center.add(centermidscroll, BorderLayout.CENTER);
                    center.add(centerdown, BorderLayout.SOUTH);
                    add(center, BorderLayout.CENTER);
                    center.updateUI();
                }
            }
        });

        calculate = new JButton(new AbstractAction("Calculate") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(row.getText());
                int[][] MatrixA = new int[size][size];
                int[][] MatrixB = new int[size][size];
                int[][] MatrixC;

                //for loop to store the values from table A to MatrixA Array
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        String[][] ph1 = new String[size][size];
                        ph1[i][j] = dtm1.getValueAt(i, j).toString();
                        MatrixA[i][j] = Integer.parseInt(ph1[i][j]);
                    }
                }

                //for loop to store the values from table B to MatrixB Array
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        String[][] ph2 = new String[size][size];
                        ph2[i][j] = dtm2.getValueAt(i, j).toString();
                        MatrixB[i][j] = Integer.parseInt(ph2[i][j]);
                    }
                }

                //Stores the product of the two matrices to MatrixC
                MatrixC = multiply(MatrixA, MatrixB);

                //Displays MatrixC
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        dtm3.setValueAt(MatrixC[i][j], i,j);
                        System.out.print(MatrixC[i][j] + " ");
                    }
                    System.out.println();
                }
            }
        });

        codesim = new JButton(new AbstractAction("Code Simulation") {
            @Override
            public void actionPerformed(ActionEvent e) {

                c1scroll.setVisible(false);
                c2scroll.setVisible(false);
                c3scroll.setVisible(false);
                c4scroll.setVisible(false);
                csscroll.setVisible(true);

                codesimulation.updateUI();

            }
        });

        computation = new JButton(new AbstractAction("Computation") {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1scroll.setVisible(true);
                c2scroll.setVisible(true);
                c3scroll.setVisible(true);
                c4scroll.setVisible(true);
                csscroll.setVisible(false);
            }
        });

        prev = new JButton(new AbstractAction("Prev") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(row.getText());

                csclick--;

                if(csclick == 0){
                    csclick = 5;
                }
                if(csclick==1){
                    cs.setText("public int[][] multiply(int[][] A, int[][] B) {\n" +
                            "        int n = A.length;\n" +
                            "\n" +
                            "        int[][] R = new int[n][n];\n" +
                            "\n" +
                            "        if (n == 1)\n" +
                            "\n" +
                            "            R[0][0] = A[0][0] * B[0][0];\n" +
                            "\n" +
                            "        else {\n" +
                            "            int[][] A11 = new int[n / 2][n / 2];\n" +
                            "            int[][] A12 = new int[n / 2][n / 2];\n" +
                            "            int[][] A21 = new int[n / 2][n / 2];\n" +
                            "            int[][] A22 = new int[n / 2][n / 2];\n" +
                            "            int[][] B11 = new int[n / 2][n / 2];\n" +
                            "            int[][] B12 = new int[n / 2][n / 2];\n" +
                            "            int[][] B21 = new int[n / 2][n / 2];\n" +
                            "            int[][] B22 = new int[n / 2][n / 2];\n" +
                            "\n" +
                            "            split(A, A11, 0, 0);\n" +
                            "            split(A, A12, 0, n / 2);\n" +
                            "            split(A, A21, n / 2, 0);\n" +
                            "            split(A, A22, n / 2, n / 2);\n" +
                            "\n" +
                            "            split(B, B11, 0, 0);\n" +
                            "            split(B, B12, 0, n / 2);\n" +
                            "            split(B, B21, n / 2, 0);\n" +
                            "            split(B, B22, n / 2, n / 2);\n" +
                            "\n" +
                            "            int[][] P1 = multiply(add(A11, A22), add(B11, B22));\n" +
                            "            int[][] P2 = multiply(add(A21, A22), B11);\n" +
                            "            int[][] P3 = multiply(A11, sub(B12, B22));\n" +
                            "            int[][] P4 = multiply(A22, sub(B21, B11));\n" +
                            "            int[][] P5 = multiply(add(A11, A12), B22);\n" +
                            "            int[][] P6 = multiply(sub(A21, A11), add(B11, B12));\n" +
                            "            int[][] P7 = multiply(sub(A12, A22), add(B21, B22));\n" +
                            "            int[][] C11 = add(sub(add(P1, P4), P5), P7);\n" +
                            "            int[][] C12 = add(P3, P5);\n" +
                            "            int[][] C21 = add(P2, P4);\n" +
                            "            int[][] C22 = add(sub(add(P1, P3), P2), P6);\n" +
                            "\n" +
                            "            join(C11, R, 0, 0);\n" +
                            "            join(C12, R, 0, n / 2);\n" +
                            "            join(C21, R, n / 2, 0);\n" +
                            "            join(C22, R, n / 2, n / 2);\n" +
                            "        }\n" +
                            "        return R;\n" +
                            "    }");

                }
                if(csclick==2){
                    cs.setText(" public void split(int[][] P, int[][] C, int iB, int jB) {\n" +
                            "        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {\n" +
                            "            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {\n" +
                            "                C[i1][j1] = P[i2][j2];\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }");
                }
                if(csclick==3){
                    cs.setText(" public int[][] sub(int[][] A, int[][] B) {\n" +
                            "        int n = A.length;\n" +
                            "        int[][] C = new int[n][n];\n" +
                            "\n" +
                            "        for (int i = 0; i < n; i++) {\n" +
                            "            for (int j = 0; j < n; j++) {\n" +
                            "                C[i][j] = A[i][j] - B[i][j];\n" +
                            "            }\n" +
                            "        }\n" +
                            "        return C;\n" +
                            "    }\n" +
                            "    public int[][] add(int[][] A, int[][] B) {\n" +
                            "        int n = A.length;\n" +
                            "        int[][] C = new int[n][n];\n" +
                            "\n" +
                            "        for (int i = 0; i < n; i++) {\n" +
                            "            for (int j = 0; j < n; j++) {\n" +
                            "                C[i][j] = A[i][j] + B[i][j];\n" +
                            "            }\n" +
                            "        }\n" +
                            "        return C;\n" +
                            "    }");
                }
                if(csclick==4){
                    cs.setText("public void join(int[][] C, int[][] P, int iB, int jB) {\n" +
                            "        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {\n" +
                            "            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {\n" +
                            "                P[i2][j2] = C[i1][j1];\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }");
                }

                System.out.println(clickcol + " " + countj + "= " + size);
            }
        });

        next = new JButton(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(row.getText());
                int[][] MatrixC;
                int[][] MatrixA = new int[size][size];
                int[][] MatrixB = new int[size][size];
                c1.setText("");
                c2.setText("");
                c3.setText("");
                c4.setText("");

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        String[][] ph1 = new String[size][size];
                        ph1[i][j] = dtm1.getValueAt(i, j).toString();
                        MatrixA[i][j] = Integer.parseInt(ph1[i][j]);
                    }
                }

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        String[][] ph2 = new String[size][size];
                        ph2[i][j] = dtm2.getValueAt(i, j).toString();
                        MatrixB[i][j] = Integer.parseInt(ph2[i][j]);
                    }
                }

                MatrixC = multiply(MatrixA, MatrixB);


                clickcol++;
                countj++;
                csclick++;

                System.out.println(clickcol + " " + countj + "= " + size);
                if(clickcol == size || countj == size){
                    clickcol = 0;
                    countj = 0;
                    clickrow++;
                    counti++;
                }

                if(csclick==1){
                    cs.setText("public int[][] multiply(int[][] A, int[][] B) {\n" +
                            "        int n = A.length;\n" +
                            "\n" +
                            "        int[][] R = new int[n][n];\n" +
                            "\n" +
                            "        if (n == 1)\n" +
                            "\n" +
                            "            R[0][0] = A[0][0] * B[0][0];\n" +
                            "\n" +
                            "        else {\n" +
                            "            int[][] A11 = new int[n / 2][n / 2];\n" +
                            "            int[][] A12 = new int[n / 2][n / 2];\n" +
                            "            int[][] A21 = new int[n / 2][n / 2];\n" +
                            "            int[][] A22 = new int[n / 2][n / 2];\n" +
                            "            int[][] B11 = new int[n / 2][n / 2];\n" +
                            "            int[][] B12 = new int[n / 2][n / 2];\n" +
                            "            int[][] B21 = new int[n / 2][n / 2];\n" +
                            "            int[][] B22 = new int[n / 2][n / 2];\n" +
                            "\n" +
                            "            split(A, A11, 0, 0);\n" +
                            "            split(A, A12, 0, n / 2);\n" +
                            "            split(A, A21, n / 2, 0);\n" +
                            "            split(A, A22, n / 2, n / 2);\n" +
                            "\n" +
                            "            split(B, B11, 0, 0);\n" +
                            "            split(B, B12, 0, n / 2);\n" +
                            "            split(B, B21, n / 2, 0);\n" +
                            "            split(B, B22, n / 2, n / 2);\n" +
                            "\n" +
                            "            int[][] P1 = multiply(add(A11, A22), add(B11, B22));\n" +
                            "            int[][] P2 = multiply(add(A21, A22), B11);\n" +
                            "            int[][] P3 = multiply(A11, sub(B12, B22));\n" +
                            "            int[][] P4 = multiply(A22, sub(B21, B11));\n" +
                            "            int[][] P5 = multiply(add(A11, A12), B22);\n" +
                            "            int[][] P6 = multiply(sub(A21, A11), add(B11, B12));\n" +
                            "            int[][] P7 = multiply(sub(A12, A22), add(B21, B22));\n" +
                            "            int[][] C11 = add(sub(add(P1, P4), P5), P7);\n" +
                            "            int[][] C12 = add(P3, P5);\n" +
                            "            int[][] C21 = add(P2, P4);\n" +
                            "            int[][] C22 = add(sub(add(P1, P3), P2), P6);\n" +
                            "\n" +
                            "            join(C11, R, 0, 0);\n" +
                            "            join(C12, R, 0, n / 2);\n" +
                            "            join(C21, R, n / 2, 0);\n" +
                            "            join(C22, R, n / 2, n / 2);\n" +
                            "        }\n" +
                            "        return R;\n" +
                            "    }");

                }
                if(csclick==2){
                    cs.setText(" public void split(int[][] P, int[][] C, int iB, int jB) {\n" +
                            "        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {\n" +
                            "            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {\n" +
                            "                C[i1][j1] = P[i2][j2];\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }");
                }
                if(csclick==3){
                    cs.setText(" public int[][] sub(int[][] A, int[][] B) {\n" +
                            "        int n = A.length;\n" +
                            "        int[][] C = new int[n][n];\n" +
                            "\n" +
                            "        for (int i = 0; i < n; i++) {\n" +
                            "            for (int j = 0; j < n; j++) {\n" +
                            "                C[i][j] = A[i][j] - B[i][j];\n" +
                            "            }\n" +
                            "        }\n" +
                            "        return C;\n" +
                            "    }\n" +
                            "    public int[][] add(int[][] A, int[][] B) {\n" +
                            "        int n = A.length;\n" +
                            "        int[][] C = new int[n][n];\n" +
                            "\n" +
                            "        for (int i = 0; i < n; i++) {\n" +
                            "            for (int j = 0; j < n; j++) {\n" +
                            "                C[i][j] = A[i][j] + B[i][j];\n" +
                            "            }\n" +
                            "        }\n" +
                            "        return C;\n" +
                            "    }");
                }
                if(csclick==4){
                    cs.setText("public void join(int[][] C, int[][] P, int iB, int jB) {\n" +
                            "        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {\n" +
                            "            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {\n" +
                            "                P[i2][j2] = C[i1][j1];\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }");
                }
                if(csclick == 5){
                    csclick=0;
                }


            }
        });

        clear = new JButton(new AbstractAction("Clear") {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickrow = 0;
                clickcol = 0;
                counti = 0;
                countj = 0;
                csclick=0;
                centermid.removeAll();
                centermid.repaint();
                centermid.revalidate();
                c1.setText(" ");
                c2.setText(" ");
                c3.setText(" ");
                c4.setText(" ");

            }
        });
        upper = new JPanel(new GridLayout(2, 2));

        centermid = new JPanel(new FlowLayout());
        centermidscroll = new JScrollPane(centermid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        centerdown = new JPanel(new BorderLayout());
        centerdown.setPreferredSize(new Dimension(300, 270));

        enterSize = new JLabel("Enter Size : ");
        row = new JTextField();

        upper.add(enterSize);
        upper.add(row);
        upper.add(enter);
        upper.add(clear);
        codesimulation = new JPanel(new FlowLayout());

        c1 = new JTextArea();
        c1scroll = new JScrollPane(c1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c1scroll.setPreferredSize(new Dimension(300,200));
        c1.setEditable(false);
        c2 = new JTextArea();
        c2scroll = new JScrollPane(c2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c2scroll.setPreferredSize(new Dimension(300,200));
        c2.setEditable(false);
        c3 = new JTextArea();
        c3scroll = new JScrollPane(c3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c3scroll.setPreferredSize(new Dimension(300,200));
        c3.setEditable(false);
        c4 = new JTextArea();
        c4scroll = new JScrollPane(c4, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c4scroll.setPreferredSize(new Dimension(300,200));
        c4.setEditable(false);

        cs = new JTextArea();
        csscroll = new JScrollPane(cs, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        csscroll.setPreferredSize(new Dimension(300,200));
        cs.setEditable(false);
        codesimulation.add(csscroll);
        csscroll.setVisible(false);

        mother.add(random);
        mother.add(calculate);
        mother.add(prev);
        mother.add(next);
        mother.add(codesim);
        mother.add(computation);
        centerdown.add(mother, BorderLayout.NORTH);
        codesimulation.add(c1scroll);
        codesimulation.add(c2scroll);
        codesimulation.add(c3scroll);
        codesimulation.add(c4scroll);
        centerdown.add(codesimulation,BorderLayout.CENTER);
        add(upper, BorderLayout.NORTH);
        setSize(1280, 720);
        setVisible(true);
    }

    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;

        int[][] R = new int[n][n];

        if (n == 1)

            R[0][0] = A[0][0] * B[0][0]; //sets the row and column to 0 if n is 1

        else { //else do the formulas by strassen
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];
            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            //divides the matrices by 2
            split(A, A11, 0, 0);
            split(A, A12, 0, n / 2);
            split(A, A21, n / 2, 0);
            split(A, A22, n / 2, n / 2);

            split(B, B11, 0, 0);
            split(B, B12, 0, n / 2);
            split(B, B21, n / 2, 0);
            split(B, B22, n / 2, n / 2);

            //multiples the sum or difference of the matrices
            int[][] M1 = multiply(add(A11, A22), add(B11, B22));
            int[][] M2 = multiply(add(A21, A22), B11);
            int[][] M3 = multiply(A11, sub(B12, B22));
            int[][] M4 = multiply(A22, sub(B21, B11));
            int[][] M5 = multiply(add(A11, A12), B22);
            int[][] M6 = multiply(sub(A21, A11), add(B11, B12));
            int[][] M7 = multiply(sub(A12, A22), add(B21, B22));
            int[][] C11 = add(sub(add(M1, M4), M5), M7);
            int[][] C12 = add(M3, M5);
            int[][] C21 = add(M2, M4);
            int[][] C22 = add(sub(add(M1, M3), M2), M6);

            //for loop to display the computation done
            c1.append("(P1 + P4)-P5+P7\n");
            c2.append("P3+P5\n");
            c3.append("P2+P4\n");
            c4.append("(P1 + P3)-P2+P6\n");
            for(int i=0; i<C11.length; i++){
                for (int j=0; j<C11.length; j++) {
                    System.out.println("C1"+i + "= " + M1[i][j] + "+"  + M4[i][j] + "+" + M5[i][j] + "+" + M7[i][j] + "= " + C11[i][j]);
                    c1.append("C"+i+""+0 + "= (" + M1[i][j] + "+"  + M4[i][j] + ")-" + M5[i][j] + "+" + M7[i][j] + "= " + C11[i][j]+"\n");
                }
            }
            for(int i=0; i<C12.length; i++){
                for (int j=0; j<C12.length; j++) {
                    System.out.println("C1"+i + "= " + M1[i][j] + "+"  + M4[i][j] + "+" + M5[i][j] + "+" + M7[i][j] + "= " + C11[i][j]);
                    c2.append("C"+i+""+1 + "= (" + M3[i][j] + "+"  + M5[i][j] + ")" + "= " + C12[i][j]+"\n");
                }
            }
            for(int i=0; i<C12.length; i++){
                for (int j=0; j<C12.length; j++) {
                    System.out.println("C1"+i + "= " + M1[i][j] + "+"  + M4[i][j] + "+" + M5[i][j] + "+" + M7[i][j] + "= " + C11[i][j]);
                    c3.append("C"+1+""+j + "= (" + M2[i][j] + "+"  + M4[i][j] + ")" + "= " + C21[i][j]+"\n");
                }
            }
            for(int i=0; i<C11.length; i++){
                for (int j=0; j<C11.length; j++) {
                    System.out.println("C1"+i + "= " + M1[i][j] + "+"  + M3[i][j] + "+" + M5[i][j] + "+" + M7[i][j] + "= " + C11[i][j]);
                    c4.append("C"+1+""+j + "= (" + M1[i][j] + "+"  + M3[i][j] + ")-" + M2[i][j] + "+" + M6[i][j] + "= " + C22[i][j]+"\n");
                }
            }

            //merges the matrices together
            join(C11, R, 0, 0);

            join(C12, R, 0, n / 2);
            join(C21, R, n / 2, 0);
            join(C22, R, n / 2, n / 2);


        }
        return R;
    }

    //subtract function
    public int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    //addition function
    public int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    //divide function
    public void split(int[][] P, int[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                C[i1][j1] = P[i2][j2];
            }
        }
    }

    //merge function
    public void join(int[][] C, int[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                P[i2][j2] = C[i1][j1];
            }
        }
    }
}

