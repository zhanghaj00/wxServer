/**
 * Create time
 */
package shop.haj.entity;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
public class CustomerCount {

    private Customer customer;
    private String _id;

    private Double totalPrice;

    private Double times;

    private String time;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTimes() {
        return times;
    }

    public void setTimes(Double times) {
        this.times = times;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
