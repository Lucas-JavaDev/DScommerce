package com.DevSuperior.Estudo.Entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_order_item")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {


    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    public OrderItem(Order order, Product product, Double price, Integer quantity) {
        id.setOrder(order); // Referencia table  Order
        id.setProduct(product); // Referencia table Product
        this.price = price;
        this.quantity = quantity;
    }


    public Order getOrder()  {
        return id.getOrder();
    }

    public Product getProduct()  {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }
}
