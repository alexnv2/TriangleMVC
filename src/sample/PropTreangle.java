package sample;

public class PropTreangle {
    private String propVert;
    private double propCoordX;
    private double propCoordY;
    private double propAngle;

    public PropTreangle(String propVert, double propCoordX,double propCoordY, double propAngle){
        this.propVert=propVert;
        this.propCoordX=propCoordX;
        this.propCoordY=propCoordY;
        this.propAngle=propAngle;
    }
    public PropTreangle(){
    }
    //Переменные
    public String getPropVert() {
        return propVert;
    }
    public void setPropVert(String propVert) {
        this.propVert = propVert;
    }
    public double getPropCoordX() {
        return propCoordX;
    }
    public void setPropCoordX(double propCoordX) {
        this.propCoordX = propCoordX;
    }
    public double getPropCoordY() {
        return propCoordY;
    }
    public void setPropCoordY(double propCoordY) {
        this.propCoordY = propCoordY;
    }
    public double getPropAngle() {
        return propAngle;
    }
    public void setPropAngle(double propAngle) {
        this.propAngle = propAngle;
    }
}
