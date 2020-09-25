import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import { FiHome, FiShoppingCart, FiPlus } from "react-icons/fi";

class Nav extends Component {
  render() {
    return (
      <nav>
        <NavLink to="/">
          <div>
            <FiHome className="store-icon icon" />
            <p>商城</p>
          </div>
        </NavLink>
        <NavLink to="/order">
          <div>
            <FiShoppingCart className="store-icon icon" />
            <p>订单</p>
          </div>
        </NavLink>
        <NavLink to="/order">
          <div>
            <FiPlus className="store-icon icon" />
            <p>添加商品</p>
          </div>
        </NavLink>
      </nav>
    );
  }
}

export default Nav;
