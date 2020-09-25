import React, { Component } from "react";
import { Link } from "react-router-dom";
import "./order.css";

class Order extends Component {
  handleDelete = () => {};

  render() {
    let orders = window.orders;
    if (orders === undefined || orders.length === 0) {
      return (
        <div className="no-order">
          <p>
            暂无订单，返回<Link to="/">商城页面</Link>继续购买
          </p>
        </div>
      );
    }
    return (
      <div className="order">
        <div className="nothing"></div>
        <div className="title table">
          <p>名字</p>
          <p>单价</p>
          <p>数量</p>
          <p>单位</p>
          <p>操作</p>
        </div>
        {orders.map((order) => {
          return (
            <div key={order.name} className={`table ${order.name}`}>
              <p>{order.name}</p>
              <p>{order.price}</p>
              <p>{order.number}</p>
              <p>{order.units}</p>
              <div>
                <button onClick={() => this.handleDelete(order.name)}>
                  删除
                </button>
              </div>
            </div>
          );
        })}
      </div>
    );
  }
}

export default Order;
