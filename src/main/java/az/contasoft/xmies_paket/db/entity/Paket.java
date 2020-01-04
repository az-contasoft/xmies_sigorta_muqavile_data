package az.contasoft.xmies_paket.db.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class Paket  implements Serializable {

    private static final long serialVersionUID = 1L;

    private long idPaket;
    private String paketAdi;
    private String paketKodu;
    private BigDecimal paketQiymeti;
    private int isDelete;
    private long parentId;


}