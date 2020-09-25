import React, { Component } from "react";
import { Link } from "react-router-dom";
import "./order.css";

class Order extends Component {
  render() {
    let orders = window.orders;
    if (orders === undefined || orders.length === 0) {
      console.log(1);
      return (
        <div className="no-order">
          <p>
            暂无订单，返回<Link to="/">商城页面</Link>继续购买
          </p>
        </div>
      );
    }
    console.log(2);
    return <div className="order">Order</div>;
  }
}

export default Order;
