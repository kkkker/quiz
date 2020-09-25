import React, { Component } from "react";
import { Link } from "react-router-dom";

class Order extends Component {
  render() {
    let orders = window.orders;
    if (orders.length === 0) {
      return (
        <div className="order">
          <p>
            暂无订单，返回<Link>商城页面</Link>继续购买
          </p>
        </div>
      );
    }
    return <div className="order">Order</div>;
  }
}

export default Order;
