package az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal;

import az.contasoft.xmies_paket_data.api.searchServices.internal.PaketData;
import az.contasoft.xmies_sigorta_muqavile.db.entity.SigortaMuqavile;
import az.contasoft.xmies_sigortaqurum.db.entity.SigortaQurum;
import az.contasoft.xmies_xidmetler_data.api.searchServices.internal.XidmetlerData;

import java.io.Serializable;

public class SigortaMuqavileData implements Serializable {


    private static final long serialVersionUID = 1L;

    private SigortaQurum sigortaQurum;
    private SigortaMuqavile sigortaMuqavile;
    private XidmetlerData xidmetlerData;
    private PaketData paketData;

    public SigortaMuqavileData(SigortaQurum sigortaQurum, SigortaMuqavile sigortaMuqavile, XidmetlerData xidmetlerData, PaketData paketData) {
        this.sigortaQurum = sigortaQurum;
        this.sigortaMuqavile = sigortaMuqavile;
        this.xidmetlerData = xidmetlerData;
        this.paketData = paketData;
    }

    public SigortaMuqavileData() {
    }

    @Override
    public String toString() {
        return "SigortaMuqavileData{" +
                "sigortaQurum=" + sigortaQurum +
                ", sigortaMuqavile=" + sigortaMuqavile +
                ", xidmetlerData=" + xidmetlerData +
                ", paketData=" + paketData +
                '}';
    }

    public SigortaQurum getSigortaQurum() {
        return sigortaQurum;
    }

    public void setSigortaQurum(SigortaQurum sigortaQurum) {
        this.sigortaQurum = sigortaQurum;
    }

    public SigortaMuqavile getSigortaMuqavile() {
        return sigortaMuqavile;
    }

    public void setSigortaMuqavile(SigortaMuqavile sigortaMuqavile) {
        this.sigortaMuqavile = sigortaMuqavile;
    }

    public XidmetlerData getXidmetlerData() {
        return xidmetlerData;
    }

    public void setXidmetlerData(XidmetlerData xidmetlerData) {
        this.xidmetlerData = xidmetlerData;
    }

    public PaketData getPaketData() {
        return paketData;
    }

    public void setPaketData(PaketData paketData) {
        this.paketData = paketData;
    }
}
