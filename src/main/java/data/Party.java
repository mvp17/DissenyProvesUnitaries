package data;

final public class Party {

    private final String name;
    private int votes;

    public Party(String name) throws IllegalArgumentException {
        if(name == null)
            throw new IllegalArgumentException();
        else
            this.name = name;
    }
    public String getName() {
        return name;
    }

    public int getVotes(){ return votes; } //NEW!!!!!!!!

    public void addVote(){
        votes+=1;
    } //NEW!!!!!!!


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Party party1 = (Party) o;
        return name.equals(party1.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Party{" + "name='" + name + '\'' + '}';
    }
}