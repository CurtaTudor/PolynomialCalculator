public class Monomial {
    private int power;
    private int coeficient;
    private char variable;

    public Monomial(int coeficient,char variable,int power)
    {
        this.power=power;
        this.coeficient=coeficient;
        this.variable=variable;
    }

    int getPower() { return this.power; }
    int getCoeficient() { return this.coeficient; }
    char getVariable() { return this.variable; }
    public String toString(){
        if(this.coeficient==0){
            return "";
        }else{
            if(this.power==0){
                return this.coeficient + "";
            }
            if(this.coeficient==1 && this.power==1){
                return this.variable + "";
            }else {
                if (this.coeficient == -1 && this.power == 1) {
                    return " - " + this.variable;
                } else {
                    if(this.coeficient==-1 && this.power>1){
                        return " - " + this.variable + "^" + this.power;
                    }else {
                        if (this.coeficient == 1 && this.power > 1) {
                            return this.variable + "^" + this.power;
                        } else {
                            if (this.coeficient >0 && this.power == 1) {
                                return this.coeficient + "" + this.variable;
                            } else {
                                if(this.coeficient<0 && this.power==1){
                                    return " - " +(-1)*this.coeficient + "" + this.variable;
                                }else {
                                    if (this.coeficient < 0) {
                                        return " - " + (-1) * this.coeficient + "" + this.variable + "^" + this.power;
                                    } else {
                                        return this.coeficient + "" + this.variable + "^" + this.power;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
