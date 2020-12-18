package sample;
import lombok.Data;

@Data
public class PropLineTreangle {
    private String propLine;
    private  double propRazmer;

    public  PropLineTreangle(String pl, double pr){
        this.propLine=pl;
        this.propRazmer=pr;
    }
}
