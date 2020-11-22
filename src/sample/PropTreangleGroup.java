package sample;

public class PropTreangleGroup extends PropTreangle {
    private String propGroup;

    public String getPropGroup() {
        return propGroup;
    }

    public void setPropGroup(String propGroup) {
        this.propGroup = propGroup;
    }

    public PropTreangleGroup(String propGroup){
        super("","","","");
        this.propGroup=propGroup;
    }

    public PropTreangleGroup(String propVert, String propCooprdX, String propCoordY, String propAngle){
        super(propVert,propCooprdX,propCoordY,propAngle);
        this.propGroup="";
    }
}
