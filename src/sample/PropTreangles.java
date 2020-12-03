package sample;

//Класс для заполнения таблицы
//@Setter и @Getter создаются расширением Lombok
//используя @Data из данной библиотеки

import lombok.Data;

@Data
public class PropTreangles {
    private String propVertex;
    private double propCoordinatesX;
    private double propCoordinatesY;
    private double propAngle;

    public PropTreangles(String propVertex, double propCoordinatesX, double propCoordinatesY, double propAngle){
        this.propVertex=propVertex;
        this.propCoordinatesX = propCoordinatesX;
        this.propCoordinatesY = propCoordinatesY;
        this.propAngle=propAngle;
    }
}
