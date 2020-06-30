package ecuacionii;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Vista extends JFrame implements ActionListener {

    JPanel panelBotones;
    JPanel panelCentral;
    JButton calcular;
    JTextField txt1;
    JTextField txt2;
    JTextField txt3;
    JScrollPane scroll;
    JTextArea area;
    JRadioButton ecuacion1;
    JRadioButton ecuacion2;
    JRadioButton ecuacion3;
    ButtonGroup grupo;
    Logica logic = new Logica();
    int numero1;
    int numero2;
    int numero3;

    public Vista() {
        setBounds(10, 10, 625, 500);
        setDefaultCloseOperation(3);
        setResizable(false);
        setTitle("Ecuaciones en recurrencia");

        panelBotones = new JPanel();
        add(panelBotones, BorderLayout.NORTH);
        calcular = new JButton("Calcular");
        calcular.addActionListener(this);
        txt1 = new JTextField("numA");
        txt2 = new JTextField("numB");
        txt3 = new JTextField("numC");
        ecuacion1 = new JRadioButton("Fn = A + B * Fn/2");
        ecuacion2 = new JRadioButton("Fn = nA + B * Fn/2");
        ecuacion3 = new JRadioButton("Fn = A + nB + 2 * Fn/2");

        grupo = new ButtonGroup();
        grupo.add(ecuacion1);
        grupo.add(ecuacion2);
        grupo.add(ecuacion3);

        panelBotones.add(ecuacion1);
        panelBotones.add(ecuacion2);
        panelBotones.add(ecuacion3);

        panelBotones.add(txt1);
        panelBotones.add(txt2);
        panelBotones.add(txt3);
        panelBotones.add(calcular);

        panelCentral = new JPanel();
        panelCentral.setLayout(null);
        add(panelCentral, BorderLayout.CENTER);
        area = new JTextArea();
        scroll = new JScrollPane(area);
        panelCentral.add(scroll);
        scroll.setBounds(55, 10, 500, 410);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == calcular) {

            numero1 = Integer.parseInt(txt1.getText());
            numero2 = Integer.parseInt(txt2.getText());
            numero3 = Integer.parseInt(txt3.getText());

            System.out.println("Botón");
            if (ecuacion1.isSelected()) {
                area.setText("");//Limpia el área
                area.setText(logic.forma1(numero1, numero2, area));

            } else if (ecuacion2.isSelected()) {
                area.setText("");//Limpia el área
                area.setText(logic.forma2(numero1, numero2, area));

            } else if (ecuacion3.isSelected()) {
                area.setText("");//Limpia el área
                area.setText(logic.forma3(numero1, numero2, numero3, area));

            } else {
                JOptionPane.showMessageDialog(null, "Debe elegir una opción");
            }

        }
    }
}
