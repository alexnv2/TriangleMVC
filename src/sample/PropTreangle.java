package sample;

public class PropTreangle {
    private String propVert;
    private String propCoordX;
    private String propCoordY;
    private String propAngle;

    public PropTreangle(String propVert, String propCoordX,String propCoordY, String propAngle){
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

    public String getPropCoordX() {
        return propCoordX;
    }

    public void setPropCoordX(String propCoordX) {
        this.propCoordX = propCoordX;
    }

    public String getPropCoordY() {
        return propCoordY;
    }

    public void setPropCoordY(String propCoordY) {
        this.propCoordY = propCoordY;
    }

    public String getPropAngle() {
        return propAngle;
    }

    public void setPropAngle(String propAngle) {
        this.propAngle = propAngle;
    }
}
