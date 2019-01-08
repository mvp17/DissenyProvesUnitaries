package data;

import services.NullException;

final public class Nif {

    private final String idNum; //alphanumeric

    public Nif(String nif){
        if(!isCorrect(nif))
            throw new NullException();

        this.idNum = nif;
    }

    private boolean isCorrect(String nif){

        if(nif == null)
            return false;
        if(nif.length() > 9)
            return false;
        for(int i = 0; i < nif.length(); i++) {
            if (Character.isDigit(nif.charAt(i)) && i == nif.length() - 1) //last character is letter
                return false;
        }
        for(int i = 0; i < nif.length(); i++) {
            if((Character.isLowerCase(nif.charAt(i)) && i==nif.length()-1) || (Character.isLetter(nif.charAt(i)) && i < nif.length()-1))
                //there are more than one letter in Nif or the last character is lower case letter
                return false;
        }

        return true;
    }

    public String getNif(){
        return idNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Nif nif1 = (Nif) o;
        return idNum.equals(nif1.idNum);
    }

    @Override
    public int hashCode() {
        return idNum.hashCode();
    }

    @Override
    public String toString() {
        return "Nif{" + "idNum='" + idNum + '\'' + '}';
    }
}