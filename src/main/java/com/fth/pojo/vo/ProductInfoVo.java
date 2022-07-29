package com.fth.pojo.vo;

/**
 * @author FengTianhao
 * @date 2022/7/29 10:48
 */
public class ProductInfoVo {
    private String pName;
    private Integer typeId;
    private Integer lPrice;
    private Integer hPrice;
    private Integer page=1;

    public ProductInfoVo() {
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getlPrice() {
        return lPrice;
    }

    public void setlPrice(Integer lPrice) {
        this.lPrice = lPrice;
    }

    public Integer gethPrice() {
        return hPrice;
    }

    public void sethPrice(Integer hPrice) {
        this.hPrice = hPrice;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ProductInfoVo{" +
                "pName='" + pName + '\'' +
                ", typeId=" + typeId +
                ", lPrice=" + lPrice +
                ", hPrice=" + hPrice +
                '}';
    }
}
