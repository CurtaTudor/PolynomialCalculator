import javax.swing.*;
import java.awt.event.*;

public class PolynomialCalcController {
    private PolynomialCalcModel model;
    private PolynomialCalcView view;

    public PolynomialCalcController(PolynomialCalcModel m,PolynomialCalcView v)
    {
        this.model=m;
        this.view=v;
        v.addAddListener(new AddListener());
        v.addSubtractListener(new SubtractListener());
        v.addMultiplyListener(new MultiplyListener());
        v.addDerivativeListener(new DerivativeListener());
        v.addIntegralListener(new IntegralListener());
    }

    class AddListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            String polynomInput1="";
            String polynomInput2="";
            try {
                polynomInput1= view.getPolynomInput1();
                polynomInput2= view.getPolynomInput2();
                Polynomial p1 = new Polynomial(polynomInput1);
                Polynomial p2 = new Polynomial(polynomInput2);
                model.add(p1, p2);
                view.setOutput(model.getResult());
            }catch(WrongPolynomialException wpex){
                view.showError(wpex.getMessage());
            }
        }
    }
    class SubtractListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            String polynomInput1 = "";
            String polynomInput2 = "";
            try{
                polynomInput1 = view.getPolynomInput1();
                polynomInput2 = view.getPolynomInput2();
                Polynomial p1 = new Polynomial(polynomInput1);
                Polynomial p2 = new Polynomial(polynomInput2);
                model.subtract(p1, p2);
                view.setOutput(model.getResult());
            }catch(WrongPolynomialException wpex){
                view.showError(wpex.getMessage());
            }
        }
    }

    class MultiplyListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            String polynomInput1 = "";
            String polynomInput2 = "";
            try{
                polynomInput1 = view.getPolynomInput1();
                polynomInput2 = view.getPolynomInput2();
                Polynomial p1 = new Polynomial(polynomInput1);
                Polynomial p2 = new Polynomial(polynomInput2);
                model.multiply(p1,p2);
                view.setOutput(model.getResult());
            }catch(WrongPolynomialException wpex){
                view.showError(wpex.getMessage());
            }
        }
    }
    class DerivativeListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            String polynomInput = "";
            try{
                polynomInput = view.getPolynomInput1();
                Polynomial p = new Polynomial(polynomInput);
                model.derivative(p);
                view.setOutput(model.getResult());
            }catch(WrongPolynomialException wpex){
                view.showError(wpex.getMessage());
            }
        }
    }
    class IntegralListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            String polynomInput = "";
            try{
                polynomInput = view.getPolynomInput1();
                Polynomial p = new Polynomial(polynomInput);
                model.integral(p);
                view.setOutput(model.getResult() + " + C");
            }catch(WrongPolynomialException wpex){
                view.showError(wpex.getMessage());
            }
        }
    }
}
