public class Demo {
    public static void main(String[] args)
    {
        PolynomialCalcModel model=new PolynomialCalcModel();
        PolynomialCalcView view =new PolynomialCalcView(model);
        PolynomialCalcController controller = new PolynomialCalcController(model,view);
        view.setVisible(true);
    }
}
