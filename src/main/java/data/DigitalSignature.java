package data;

final public class DigitalSignature {

    private final byte[] sign;
    //TODO some exceptions more????????????

    public DigitalSignature(byte[] sign) throws IllegalArgumentException {
        if(sign == null)
            throw new IllegalArgumentException();
        else
            this.sign = sign;
    }

    public byte[] getDigitalSignature(){
        return sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        DigitalSignature sign1 = (DigitalSignature) o;
        return sign.equals(sign1.sign);
    }

    @Override
    public int hashCode() {
        return sign.hashCode();
    }

    @Override
    public String toString() {
        return "DigitalSignature{" + "sign='" + sign + '\'' + '}';
    }
}