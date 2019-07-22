package az.contasoft.xmies_xidmetler.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Xidmetler implements Serializable {


    private static final long serialVersionUID = 1L;

    private long idXidmetler;
    private String xidmetAdi;
    private String xidmetKodu;
    private BigDecimal qiymet;
    private int isDelete;

    public Xidmetler() {
    }

    public Xidmetler(long idXidmetler, String xidmetAdi, String xidmetKodu, BigDecimal qiymet, int isDelete) {
        this.idXidmetler = idXidmetler;
        this.xidmetAdi = xidmetAdi;
        this.xidmetKodu = xidmetKodu;
        this.qiymet = qiymet;
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Xidmetler{" +
                "idXidmetler=" + idXidmetler +
                ", xidmetAdi='" + xidmetAdi + '\'' +
                ", xidmetKodu='" + xidmetKodu + '\'' +
                ", qiymet=" + qiymet +
                ", isDelete=" + isDelete +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getIdXidmetler() {
        return idXidmetler;
    }

    public void setIdXidmetler(long idXidmetler) {
        this.idXidmetler = idXidmetler;
    }

    public String getXidmetAdi() {
        return xidmetAdi;
    }

    public void setXidmetAdi(String xidmetAdi) {
        this.xidmetAdi = xidmetAdi;
    }

    public String getXidmetKodu() {
        return xidmetKodu;
    }

    public void setXidmetKodu(String xidmetKodu) {
        this.xidmetKodu = xidmetKodu;
    }

    public BigDecimal getQiymet() {
        return qiymet;
    }

    public void setQiymet(BigDecimal qiymet) {
        this.qiymet = qiymet;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}