package az.contasoft.xmies_sigortaqurum.db.entity;

import java.io.Serializable;

public class SigortaQurum implements Serializable {


    private static final long serialVersionUID = 1L;

    private long idSigortaQurum;
    private String qurumAdi;
    private long qurumType;
    private  int isActive;
    private int isDelete;

    public SigortaQurum(long idSigortaQurum, String qurumAdi, long qurumType, int isActive, int isDelete) {
        this.idSigortaQurum = idSigortaQurum;
        this.qurumAdi = qurumAdi;
        this.qurumType = qurumType;
        this.isActive = isActive;
        this.isDelete = isDelete;
    }

    public SigortaQurum() {
    }

    @Override
    public String toString() {
        return "SigortaQurum{" +
                "idSigortaQurum=" + idSigortaQurum +
                ", qurumAdi='" + qurumAdi + '\'' +
                ", qurumType=" + qurumType +
                ", isActive=" + isActive +
                ", isDelete=" + isDelete +
                '}';
    }

    public long getIdSigortaQurum() {
        return idSigortaQurum;
    }

    public void setIdSigortaQurum(long idSigortaQurum) {
        this.idSigortaQurum = idSigortaQurum;
    }

    public String getQurumAdi() {
        return qurumAdi;
    }

    public void setQurumAdi(String qurumAdi) {
        this.qurumAdi = qurumAdi;
    }

    public long getQurumType() {
        return qurumType;
    }

    public void setQurumType(int qurumType) {
        this.qurumType = qurumType;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
