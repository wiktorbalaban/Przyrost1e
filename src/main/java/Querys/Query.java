package Querys;

public class Query{
    public int getNr() {
        return nr;
    }

    public String getData() {
        return data;
    }

    private int nr;
    private String data;

    public Query (int nr, String data){
        this.nr=nr;
        this.data=data;
    }
}
