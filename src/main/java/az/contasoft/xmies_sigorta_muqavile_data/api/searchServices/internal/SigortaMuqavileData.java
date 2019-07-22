package az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal;

import az.contasoft.xmies_paket.db.entity.Paket;
import az.contasoft.xmies_sigorta_muqavile.db.entity.SigortaMuqavile;
import az.contasoft.xmies_sigortaqurum.db.entity.SigortaQurum;
import az.contasoft.xmies_xidmetler.db.entity.Xidmetler;

import java.io.Serializable;

public class SigortaMuqavileData implements Serializable {


    private static final long serialVersionUID = 1L;

    private SigortaQurum sigortaQurum;
    private SigortaMuqavile sigortaMuqavile;
    private Xidmetler xidmetler;
    private Paket paket;

    public SigortaMuqavileData(SigortaQurum sigortaQurum, SigortaMuqavile sigortaMuqavile, Xidmetler xidmetler, Paket paket) {
        this.sigortaQurum = sigortaQurum;
        this.sigortaMuqavile = sigortaMuqavile;
        this.xidmetler = xidmetler;
        this.paket = paket;
    }

    public SigortaMuqavileData() {
    }

    @Override
    public String toString() {
        return "SigortaMuqavileData{" +
                "sigortaQurum=" + sigortaQurum +
                ", sigortaMuqavile=" + sigortaMuqavile +
                ", xidmetler=" + xidmetler +
                ", paket=" + paket +
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

    public Xidmetler getXidmetler() {
        return xidmetler;
    }

    public void setXidmetler(Xidmetler xidmetler) {
        this.xidmetler = xidmetler;
    }

    public Paket getPaket() {
        return paket;
    }

    public void setPaket(Paket paket) {
        this.paket = paket;
    }
}