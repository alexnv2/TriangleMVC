package sample;

//Класс для заполнения таблицы
//@Setter и @Getter создаются расширением Lombok
//используя @Data из данной библиотеки

import lombok.Data;

@Data
public class PropTreangle {
    private String propVert;
    private double propCoordX ;
    private double propCoordY;
    private double propAngle;

    public PropTreangle(String propVert, double propCoordX,double propCoordY, double propAngle){
        this.propVert=propVert;
        this.propCoordX=propCoordX;
        this.propCoordY=propCoordY;
        this.propAngle=propAngle;
    }
}
