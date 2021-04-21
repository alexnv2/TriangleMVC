package sample;
import lombok.Data;
//Класс для заполнения таблиц длин отрезков
@Data
public class PropLineTreangle {
    private String propLine;
    private  double propRazmer;

    public  PropLineTreangle(String pl, double pr){
        this.propLine=pl;
        this.propRazmer=pr;
    }
}
