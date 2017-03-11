/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "PRODUCT_INFORMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId")
    , @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName")
    , @NamedQuery(name = "Product.findByCategoryId", query = "SELECT p FROM Product p WHERE p.categoryId = :categoryId")
    , @NamedQuery(name = "Product.findByWeightClass", query = "SELECT p FROM Product p WHERE p.weightClass = :weightClass")
    , @NamedQuery(name = "Product.findBySupplierId", query = "SELECT p FROM Product p WHERE p.supplierId = :supplierId")
    , @NamedQuery(name = "Product.findByProductStatus", query = "SELECT p FROM Product p WHERE p.productStatus = :productStatus")
    , @NamedQuery(name = "Product.findByListPrice", query = "SELECT p FROM Product p WHERE p.listPrice = :listPrice")
    , @NamedQuery(name = "Product.findByMinPrice", query = "SELECT p FROM Product p WHERE p.minPrice = :minPrice")
    , @NamedQuery(name = "Product.findByCatalogUrl", query = "SELECT p FROM Product p WHERE p.catalogUrl = :catalogUrl")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "CATEGORY_ID")
    private Short categoryId;
    @Column(name = "WEIGHT_CLASS")
    private Short weightClass;
    @Column(name = "SUPPLIER_ID")
    private Integer supplierId;
    @Column(name = "PRODUCT_STATUS")
    private String productStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LIST_PRICE")
    private BigDecimal listPrice;
    @Column(name = "MIN_PRICE")
    private BigDecimal minPrice;
    @Column(name = "CATALOG_URL")
    private String catalogUrl;

    public Product() {
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Short getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Short categoryId) {
        this.categoryId = categoryId;
    }

    public Short getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(Short weightClass) {
        this.weightClass = weightClass;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public String getCatalogUrl() {
        return catalogUrl;
    }

    public void setCatalogUrl(String catalogUrl) {
        this.catalogUrl = catalogUrl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Product[ productId=" + productId + " ]";
    }
    
}
