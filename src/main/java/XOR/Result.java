package XOR;

public class Result implements  Comparable<Result>{
    public String s;
    public double score;
    char c;

    public Result(String s, double score){
        this.s = s;
        this.score = score;
    }
    public Result(String s){
        this(s, XOR.score(s));
    }
    public Result setChar(char c) {
        this.c=c;
        return this;
    }
    @Override
    public int compareTo(Result o) {
        return o.score-this.score>0?1:(int)(o.score-this.score);
    }

}
