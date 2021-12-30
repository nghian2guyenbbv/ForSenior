
package eni.magento.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class EniResponseModel {

    private List<EniOrder> orders = null;
    private Integer total;
    private Integer page;
    private Integer pages;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<EniOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<EniOrder> orders) {
        this.orders = orders;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
