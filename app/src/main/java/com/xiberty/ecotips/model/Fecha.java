package com.xiberty.ecotips.model;

/**
 * Created by growcallisaya on 14/3/17.
 */

public class Fecha {
    String fecha;

    public Fecha(String fecha) {
        this.fecha = fecha;
    }
    public String getFormatDate(){
        String day = this.fecha.substring(8,10);
        int pointer = Integer.parseInt(this.fecha.substring(5,7));
        String month = "";
        switch (pointer){
            case 1: month = "Enero";
                break;
            case 2: month = "Febrero";
                break;
            case 3: month = "Marzo";
                break;
            case 4: month = "Abril";
                break;
            case 5: month = "Mayo";
                break;
            case 6: month = "Junio";
                break;
            case 7: month = "Julio";
                break;
            case 8: month = "Agosto";
                break;
            case 9: month = "Septiembre";
                break;
            case 10: month = "Octubre";
                break;
            case 11: month = "Noviembre";
                break;
            case 12: month = "Diciembre";
                break;
            default: month = "Mes";
                break;

        }

        return day + " de "+ month;
    }
}
