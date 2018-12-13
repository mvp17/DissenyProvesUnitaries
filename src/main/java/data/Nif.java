package data;

final public class Nif {

    private final String idNum; //alphanumeric

    public Nif(String nif){
        this.idNum = nif;
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