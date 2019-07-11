package az.contasoft.xmies_xidmetler_data.api.searchServices.internal;

import az.contasoft.xmies_xidmetler_data.api.searchServices.internal.entity.Personal;
import az.contasoft.xmies_xidmetler_data.api.searchServices.internal.entity.Xidmetler;

import java.io.Serializable;

public class XidmetlerData implements Serializable {


    private static final long serialVersionUID = 1L;

    private Xidmetler xidmetler;
    private Personal personal;


    public XidmetlerData(Xidmetler xidmetler, Personal personal) {
        this.xidmetler = xidmetler;
        this.personal = personal;
    }

    public XidmetlerData() {
    }

    @Override
    public String toString() {
        return "XidmetlerData{" +
                "xidmetler=" + xidmetler +
                ", personal=" + personal +
                '}';
    }


    public Xidmetler getXidmetler() {
        return xidmetler;
    }

    public void setXidmetler(Xidmetler xidmetler) {
        this.xidmetler = xidmetler;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
}
