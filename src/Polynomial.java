import java.io.IOException;
import java.util.*;

public class Polynomial {
    private List<Monomial> monomialsList;

    public Polynomial()
    {
        this.monomialsList=new ArrayList<Monomial>();
    }

    public Polynomial(String s1) throws WrongPolynomialException
    {
        if(s1.equals("")){
            throw new WrongPolynomialException("Empty input");
        }
        this.monomialsList=new ArrayList<Monomial>();
        s1=s1.replaceAll("\\s","");  // elim toate spatiile eventual inserate de utilizator
        StringBuilder s=new StringBuilder(s1);

        char variable=getVariable(s);     // retin varibila inserata de utilizator
        while (s.indexOf("+") != -1 && s.indexOf("-") != -1) {   // cazul 1: cand avem si + si -

            int indPlus = s.indexOf("+");
            int indMinus = s.indexOf("-");
            String curentMonom = "";
            boolean ok = false;

            if (indMinus == 0) {               //  cazul in care monomul urmator are coeficient negativ
                String auxStr = s.substring(1);         // eliminam coeficientul negativ pt a gasii urmatorul minus
                int nouIndMinus = auxStr.indexOf('-');      // indexul urmatorului minus
                if (nouIndMinus == -1 || nouIndMinus > indPlus) {
                    curentMonom = s.substring(0, indPlus);      // extrag monomul dintre 0 si urmatorul plus
                } else {
                    curentMonom = s.substring(0, nouIndMinus + 1);   // extrag monomul dintre 0 si urmatorul minus
                    indMinus = nouIndMinus + 1;
                    ok = true;
                }
            } else {
                if (indMinus > indPlus) {
                    curentMonom = s.substring(0, indPlus);
                } else {
                    curentMonom = s.substring(0, indMinus);
                    ok = true;
                }
            }
            addMonom(curentMonom, variable);     // adaug monomul la polinom
            if (ok) {
                s.delete(0, indMinus);      // elimin din string monomul procesat in functie de semnul curent
            } else {
                s.delete(0, indPlus + 1);
            }
        }
        if(s.indexOf("-")==-1){         // raman 2 cazuri: doar plus sau doar minus
            while(s.indexOf("+")!=-1){          // cazul doar plus
                int indPlus=s.indexOf("+");
                String curentMonom=s.substring(0,indPlus);      // extrag monomul urmator
                addMonom(curentMonom,variable);
                s.delete(0,indPlus+1);
            }
            addMonom(s.toString(),variable);        // adaug la polinom ultimul monom
        }else{
            while(s.indexOf("-")!=-1){    // cazul doar minus
                String auxStr=s.substring(1);
                int nouIndMinus=auxStr.indexOf('-');
                if(nouIndMinus==-1){                    // cazul in care a ramas doar un singur monom
                    addMonom(s.toString(),variable);
                    s.delete(0,s.length());
                }else{
                    String curentMonom=s.substring(0,nouIndMinus+1);        // extrag din string urmatorul monom
                    addMonom(curentMonom,variable);
                    s.delete(0,nouIndMinus+1);
                }
            }
        }
    }
    public void addMonom(String curentMonom, char variable)
    {
        int indVar=curentMonom.indexOf(variable);

        int coefInt=1;
        int powerInt=1;

        if(indVar==-1){                         // caz special pentru constante
            powerInt=0;
            coefInt=Integer.parseInt(curentMonom);
        }else {
            StringBuilder auxCoef = new StringBuilder(curentMonom);  //  creez un string auxiliar pentru a extrage coeficientul
            auxCoef.delete(indVar, auxCoef.length());
            if (auxCoef.length() > 0) {
                if(auxCoef.charAt(0)=='-' && auxCoef.length()==1){      // caz special pentru coeficient = -1
                    coefInt=-1;
                }else {
                    coefInt = Integer.parseInt(auxCoef.toString());
                }
            }
            int indPower = curentMonom.indexOf("^");         // separ monomul a.i sa ramana doar puterea
            if (indPower != -1) {                               // daca indexOf("^") este -1 atunci puterea este 1
                String auxPower = curentMonom.substring(indPower + 1);
                powerInt = Integer.parseInt(auxPower);
            }
        }
        Monomial m = new Monomial(coefInt, variable, powerInt);
        this.addToPolynomial(m);
    }

    public char getVariable(StringBuilder s) throws WrongPolynomialException   // retuneaza variabila si testeaza pt exceptii
    {
        char variable='x';
        for(int i=0;i<s.length();i++){          // caut variabila
            if(s.charAt(i)>='A' && s.charAt(i)<='z' && s.charAt(i)!='^'){
                variable=s.charAt(i);
                if(i!=s.length()-1 && Character.isDigit(s.charAt(i + 1))){      // input-uri de tipul "x3"
                    throw new WrongPolynomialException("Invalid Polynomial");
                }
            }
            if(!Character.isLetter(s.charAt(i)) && !Character.isDigit(s.charAt(i)) && s.charAt(i)!='+' && s.charAt(i)!='-' && s.charAt(i)!='^'){
                throw new WrongPolynomialException("Invalid Polynomial");       // pt caractere ce nu pot fii admise intr-un polinom(?:<>...etc)
            }
            if(s.charAt(i)=='+' || s.charAt(i)=='-' || s.charAt(i)=='^'){
                if(s.charAt(i+1)=='+' || s.charAt(i+1)=='-' || s.charAt(i+1)=='^'){     // pt operatori succesivi "2x+-"
                    throw new WrongPolynomialException("Invalid Format");
                }
            }
        }
        return variable;
    }

    public List<Monomial> getMonomialsList() { return this.monomialsList; }
    void addToPolynomial(Monomial m)
    {
        monomialsList.add(m);
    }

    public String toString()
    {
        String rez="";
        Map<Integer,Integer> tm=new TreeMap<Integer,Integer>(Collections.reverseOrder());
        for(Monomial e:monomialsList){
            tm.put(e.getPower(),e.getCoeficient());
        }
        boolean testZero=true;
        for(Map.Entry<Integer,Integer> e: tm.entrySet()){
            if(e.getValue()!=0){
                testZero=false;
            }
        }
        if(!testZero) {
            for (Map.Entry<Integer, Integer> e : tm.entrySet()) {
                for (Monomial entry : monomialsList) {
                    if (entry.getPower() == e.getKey()) {
                        if (rez == "") {
                            rez = entry.toString();
                        } else {
                            if (entry.getCoeficient() > 0) {
                                rez = rez + " + " + entry.toString();
                            } else {
                                rez = rez + "" + entry.toString();
                            }
                        }
                    }
                }
            }
            return rez;
        }
        return "0";
    }

    public void clear()
    {
        monomialsList.clear();
    }
}
