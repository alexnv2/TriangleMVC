package sample;

//Класс перерасчета координат
public class WView {
    double Wt, Wb, Wl, Wr;//Мировые координаты
    double Vt, Vb, Vl, Vr;//Координаты окна просмотра
    double A, B, C, D;

    //Конструктор
    WView(double Wt, double Wb, double Wl, double Wr, double Vt, double Vb, double Vl, double Vr) {
        this.Wt=Wt;
        this.Wb=Wb;
        this.Wl=Wl;
        this.Wr=Wr;
        this.Vt=Vt;
        this.Vb=Vb;
        this.Vl=Vl;
        this.Vr=Vr;
        //Коэффициенты
        A=(Vr-Vl)/(Wr-Wl);
        C=Vl-A*Wl;
        B=(Vb-Vt)/(Wb-Wt);
        D=Vb-B*Wb;
    }
    //Перерерасчет координаты х
    double accessX(double x) {return Math.round(A*x+C);}
    //Перерасчет координаты y
    double accessY(double y) {
        return Math.round(B*y+D);
    }
    //Обратный перерасчет sx
    double revAccessX(double wx) {
        return Math.round((wx-C)/A);
    }
    //Обратный перерасчет sy
    double revAccessY(double wy) {
        return Math.round((wy-D)/B);
    }
}
