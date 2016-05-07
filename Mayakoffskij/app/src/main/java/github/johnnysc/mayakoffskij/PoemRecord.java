package github.johnnysc.mayakoffskij;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class PoemRecord {
    private String name;
    private String poem;

    public PoemRecord(String name, String poem){
        this.name = name;
        this.poem = poem;
    }

    public String getName(){ return name;}
    public void setName(String name){this.name = name;}
    public String getPoem(){ return poem;}
    public void setPoem(String poem){ this.poem = poem;}
}
