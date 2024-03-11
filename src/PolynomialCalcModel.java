import java.math.*;
import java.util.*;

public class PolynomialCalcModel {
    private Polynomial result;
    private Map<Integer,Integer> tm;

    public PolynomialCalcModel()
    {
        result=new Polynomial();
        tm=new TreeMap<Integer,Integer>(Collections.reverseOrder());
        reset();
    }
    public void reset()
    {
        result.clear();
        tm.clear();
    }
    public String getResult(){ return this.result.toString(); }

    public void add(Polynomial p1,Polynomial p2)
    {
        reset();
        char variable=0;
        for(Monomial e: p1.getMonomialsList()){         // pun in map primul polinom
            tm.put(e.getPower(),e.getCoeficient());
            variable=e.getVariable();
        }
        for(Monomial e: p2.getMonomialsList()){         // iterez prin al doilea polinom
            int power=e.getPower();
            int coef=e.getCoeficient();
            if(variable==0) {
                variable = e.getVariable();
            }
            if(tm.containsKey(e.getPower())){               // verific daca polinoamele au aceeasi putere pentru monomul curent
                for(Map.Entry<Integer,Integer> entry: tm.entrySet()){
                    if(entry.getKey()==e.getPower()){
                        coef+=entry.getValue();
                    }
                }
            }
            tm.put(power,coef);                 // actualizez map-ul
        }
        for(Map.Entry<Integer,Integer> entry:tm.entrySet()){
            int power=entry.getKey();
            int coef=entry.getValue();
            Monomial mon=new Monomial(coef,variable,power);
            result.addToPolynomial(mon);
        }
    }

    public void subtract(Polynomial p1,Polynomial p2)
    {
        reset();
        char variable=0;
        for(Monomial e: p1.getMonomialsList()){
            tm.put(e.getPower(),e.getCoeficient());
            variable=e.getVariable();
        }
        for(Monomial e: p2.getMonomialsList()){
            int power=e.getPower();
            int coef=e.getCoeficient();
            if(variable==0) {
                variable = e.getVariable();
            }
            if(tm.containsKey(e.getPower())){
                for(Map.Entry<Integer,Integer> entry: tm.entrySet()){
                    if(entry.getKey()==e.getPower()){
                        coef-=entry.getValue();
                    }
                }
            }
            tm.put(power,(-1)*coef);
        }
        for(Map.Entry<Integer,Integer> entry:tm.entrySet()){
            int power=entry.getKey();
            int coef=entry.getValue();
            Monomial mon=new Monomial(coef,variable,power);
            result.addToPolynomial(mon);
        }
    }

    public void multiply(Polynomial p1,Polynomial p2)
    {
        reset();
        char variable=0;
        for(Monomial e:p1.getMonomialsList()){
            variable=e.getVariable();
            for(Monomial ent:p2.getMonomialsList()){
                if(variable==0){
                    variable=ent.getVariable();
                }
                int newPower=e.getPower()+ent.getPower();
                int newCoef=e.getCoeficient()*ent.getCoeficient();
                if(tm.containsKey(newPower)){
                    for(Map.Entry<Integer,Integer>entry:tm.entrySet()){
                        if(entry.getKey()==newPower){
                            newCoef+= entry.getValue();
                        }
                    }
                }
                tm.put(newPower,newCoef);
            }
        }
        for(Map.Entry<Integer,Integer> entry:tm.entrySet()){
            int power=entry.getKey();
            int coef=entry.getValue();
            Monomial mon=new Monomial(coef,variable,power);
            result.addToPolynomial(mon);
        }
    }

    public void derivative(Polynomial p)
    {
        reset();
        char variable='x';
        for(Monomial e:p.getMonomialsList()){
            int power=e.getPower();
            int coef=e.getCoeficient();
            if(tm.containsKey(power)){
                for(Map.Entry<Integer,Integer>entry:tm.entrySet()){
                    if(entry.getKey()==power){
                        coef+=entry.getValue();
                    }
                }
            }
            tm.put(power,coef);
            variable=e.getVariable();
        }
        for(Map.Entry<Integer,Integer>entry:tm.entrySet()){
            int power=entry.getKey();
            int coef= entry.getValue();
            if(power==0){
                coef=0;
            }else{
                coef=coef*power;
            }
            power--;
            Monomial mon=new Monomial(coef,variable,power);
            result.addToPolynomial(mon);
        }
    }
    public void integral(Polynomial p)
    {
        reset();
        char variable='x';
        for(Monomial e:p.getMonomialsList()){
            int power=e.getPower();
            int coef=e.getCoeficient();
            if(tm.containsKey(power)){
                for(Map.Entry<Integer,Integer>entry:tm.entrySet()){
                    if(entry.getKey()==power){
                        coef+=entry.getValue();
                    }
                }
            }
            tm.put(power,coef);
            variable=e.getVariable();
        }
        for(Map.Entry<Integer,Integer>entry:tm.entrySet()){
            int power=entry.getKey();
            int coef= entry.getValue();
            power++;
            coef=coef/power;
            Monomial mon=new Monomial(coef,variable,power);
            result.addToPolynomial(mon);
        }
    }
}
