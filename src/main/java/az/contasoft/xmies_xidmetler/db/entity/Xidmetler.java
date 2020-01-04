package az.contasoft.xmies_xidmetler.db.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class Xidmetler implements Serializable {
    private static final long serialVersionUID = 1L;

    private long idXidmetler;
    private String xidmetAdi;
    private String xidmetKodu;
    private BigDecimal qiymet;
    private String anesteziya_novu;
    private String xaric;
    private String reanimasiyda_yatis;
    private String stasionarda_yatis;
    private int isDelete;
}