import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class mainFrame2 extends JFrame
{
    JScrollPane mainScroll;
    JPanel upper, upper2, upper3, center, contentPanel, row, centerPanel, rightPanel, indexNumberHeader, storedValuePanel;
    JLabel enterNumber, indexNumber;
    JTextField numberEntered;
    JButton enter, clear, calculate;

    JButton[] storedValue = new JButton[1000];

    JTextField[] gh = new JTextField[1000];

    int[] array = new int[1000];

    public mainFrame2()
    {
        super("Interpolation Algorithm");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //First Panel
        enterNumber = new JLabel("Enter Array Count: ");
        enterNumber.setForeground(Color.WHITE);
        enterNumber.setBounds(10,10,300,40);
        enterNumber.setVisible(true);

        numberEntered = new JTextField(5);
        numberEntered.setBounds(120,20,50,20);
        numberEntered.setVisible(true);

        //Panel Settings
        //1
        upper = new JPanel();
        upper.setBackground(Color.WHITE);
        upper.setLayout(new BorderLayout());

        //2
        upper2 = new JPanel();
        upper2.setLayout(new BorderLayout());
        upper2.setBackground(Color.BLACK);
        upper2.setPreferredSize(new Dimension(1280,50));

        //3
        center = new JPanel();
        center.setLayout(new BorderLayout());
        center.setBackground(Color.ORANGE);
        center.setPreferredSize(new Dimension(0,0));

        //4
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.ORANGE);

        //5
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.GRAY);

        //6
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(1150,0));
        rightPanel.setBackground(Color.ORANGE);

        //7
        indexNumberHeader = new JPanel();
        indexNumberHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
        indexNumberHeader.setBackground(Color.BLACK);

        //8
        upper3 = new JPanel();
        upper3.setLayout(new FlowLayout());
        upper3.setBackground(Color.BLACK);
        upper3.setPreferredSize(new Dimension(600,50));

        //9
        storedValuePanel = new JPanel();
        storedValuePanel.setLayout(new BoxLayout(storedValuePanel, BoxLayout.Y_AXIS));
        storedValuePanel.setBackground(Color.RED);

        indexNumber = new JLabel("Index No.");
        indexNumber.setForeground(Color.WHITE);

        mainScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScroll.setPreferredSize(new Dimension(0,0));

        indexNumberHeader.add(indexNumber);

        upper2.add(upper3, BorderLayout.CENTER);
        upper2.add(indexNumberHeader, BorderLayout.WEST);

        upper3.add(enterNumber);
        upper3.add(numberEntered);

        center.add(mainScroll, BorderLayout.CENTER);

        upper.add(upper2, BorderLayout.NORTH);
        upper.add(center, BorderLayout.CENTER);

        add(upper);

        contentPanel.add(centerPanel);

        //Enter button settings
        enter = new JButton(new AbstractAction("Enter")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int size = Integer.parseInt(numberEntered.getText());

                if(size > 0)
                {
                    enter.setEnabled(false);
                    numberEntered.setEditable(false);
                    upper2.setVisible(true);

                    row = new JPanel();
                    row.setLayout(new GridBagLayout());
                    GridBagConstraints numberHeader = new GridBagConstraints();
                    numberHeader.fill = GridBagConstraints.BOTH;
                    numberHeader.gridx = 0;
                    numberHeader.weighty = 1;
                    row.setBackground(Color.ORANGE);

                    mainScroll.setRowHeaderView(row);

                    for (int i = 1; i <= size; i++)
                    {
                        String n = String.valueOf(i);

                        gh[i] = new JTextField();
                        gh[i].setPreferredSize(new Dimension(20,20));

                        JLabel bh = new JLabel(n);
                        bh.setPreferredSize(new Dimension(40,20));

                        centerPanel.add(gh[i]);
                        row.add(bh,numberHeader);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                contentPanel.add(rightPanel, BorderLayout.EAST);
//                clear.setEnabled(true);
            }
        });

        enter.setBounds(10,50,70,20);
        enter.setBackground(Color.LIGHT_GRAY);
        enter.setFocusable(false);
        enter.setOpaque(true);
        enter.setVisible(true);

        //calculate button
        calculate = new JButton(new AbstractAction("Calculate")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int size2 = Integer.parseInt(numberEntered.getText());

                for(int i = 1; i <= size2; i++)
                {
                    Integer.parseInt(gh[i].getText());
                    array[i] = Integer.parseInt(gh[i].getText());
                    System.out.println(array[i]);

                    gh[i].setEditable(false);
                }

                for(int i = 1; i <= size2; i++)
                {
                    storedValue[i] = new JButton(String.valueOf(i));
//                    storedValue[i].setEnabled(false);
                    storedValuePanel.add(storedValue[i]);
                }
                rightPanel.add(storedValuePanel, BorderLayout.WEST);
                rightPanel.updateUI();
            }
        });

        calculate.setBounds(0,0,70,20);
        calculate.setBackground(Color.LIGHT_GRAY);
        calculate.setFocusable(false);
        calculate.setOpaque(true);
        calculate.setVisible(true);

        //clear button
//        clear = new JButton(new AbstractAction("Clear")
//        {
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                numberEntered.setText("");
//                numberEntered.setEditable(true);
//                enter.setEnabled(true);
//                row.removeAll();
//                row.revalidate();
//                row.repaint();
//                contentPanel.removeAll();
//                contentPanel.revalidate();
//                contentPanel.repaint();
//            }
//        });
//        clear.setBounds(0,0,70,20);
//        clear.setBackground(Color.LIGHT_GRAY);
//        clear.setFocusable(false);
//        clear.setOpaque(true);
//        clear.setEnabled(false);
//        clear.setVisible(true);

        upper3.add(enter);
        upper3.add(calculate);
//        upper3.add(clear);
        setResizable(false);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static int interpolationSearch(int[] arr, int x)
    {
        for (int i = 1; i < arr.length; i++)
        {
            for (int j = 1 + 1; j < arr.length; j++)
            {
                int temp = 0;

                if(arr[i] > arr[j])
                {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            int low = 0;
            int high = arr.length - 1;

            while(x >= arr[low] && x <= arr[high] && low <= high)
            {
                int position = low + (high - low) * (x - arr[low]) / (arr[high] - arr[low]);

                if(high == low)
                {
                    if(arr[low] == x)
                    {
                        return low;
                    }
                    else
                    {
                        return -1;
                    }
                }

                if(arr[position] == x)
                {
                    return position;
                }

                if(arr[position] < x)
                {
                    low = position + 1;
                }
                else
                {
                    high = position -1;
                }
            }
        }
        return -1;
    }
}