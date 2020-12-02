package sample;

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
