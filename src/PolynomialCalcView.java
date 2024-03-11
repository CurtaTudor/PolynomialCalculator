import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PolynomialCalcView extends JFrame{
    private JTextField polynomInput1 = new JTextField(15);
    private JTextField polynomInput2 = new JTextField(15);

    private JTextField polynomOutput = new JTextField(15);
    private JButton addBtn = new JButton("Add");
    private JButton subtractBtn = new JButton("Subtract");

    private JButton multiplyBtn = new JButton("Multiply");

    private JButton divideBtn = new JButton("Divide");

    private JButton derivativeBtn = new JButton("Derivative");

    private JButton integralBtn = new JButton("Integral");

    private PolynomialCalcModel model;

    public PolynomialCalcView(PolynomialCalcModel m_model)
    {
        this.model=m_model;

        this.polynomOutput.setText(model.getResult());
        this.polynomOutput.setEditable(false);

        setLocationRelativeTo(null);

        JPanel inputPanel1 = new JPanel();  // Panel pentru primul polinom (label + text field)
        JLabel polynomLabel1 = new JLabel("     First Polynomial  = ");
        inputPanel1.add(polynomLabel1);
        inputPanel1.add(polynomInput1);
        inputPanel1.setLayout(new FlowLayout());

        JPanel inputPanel2 = new JPanel(); // Panel pentru al doilea polinom (label + text field)
        JLabel polynomLabel2 = new JLabel("Second Polynomial = ");
        inputPanel2.add(polynomLabel2);
        inputPanel2.add(polynomInput2);
        inputPanel2.setLayout(new FlowLayout());

        JPanel resultPanel = new JPanel();  // Panel pentru polinomul rezultat dupa efectuarea operatiilor
        JLabel outputLabel = new JLabel("                      Result  = ");
        resultPanel.add(outputLabel);
        resultPanel.add(polynomOutput);
        resultPanel.setLayout(new FlowLayout());

        JPanel operationPanel = new JPanel();   // Panel pentru butoanele de operatii (add, subtract, etc..)
        operationPanel.add(addBtn);
        operationPanel.add(subtractBtn);
        operationPanel.add(multiplyBtn);
        operationPanel.add(divideBtn);
        operationPanel.add(derivativeBtn);
        operationPanel.add(integralBtn);
        operationPanel.setLayout(new GridLayout(0,2));

        JPanel content = new JPanel();
        content.add(inputPanel1);
        content.add(inputPanel2);
        content.add(resultPanel);
        content.add(operationPanel);
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));

        this.setContentPane(content);
        this.setSize(500,350);

        this.setTitle("Polynomial Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    String getPolynomInput1() { return polynomInput1.getText(); }
    String getPolynomInput2() { return polynomInput2.getText(); }
    void setOutput(String output) { polynomOutput.setText(output); }
    void showError(String errorMessage) { JOptionPane.showMessageDialog(this,errorMessage); }
    void addAddListener(ActionListener aal) { addBtn.addActionListener(aal); }
    void addSubtractListener(ActionListener sal) { subtractBtn.addActionListener(sal); }
    void addMultiplyListener(ActionListener mal) { multiplyBtn.addActionListener(mal); }
    void addDerivativeListener(ActionListener dal) { derivativeBtn.addActionListener(dal); }

    void addIntegralListener(ActionListener ial) { integralBtn.addActionListener(ial); }

}
