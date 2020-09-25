import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import { FiHome, FiShoppingCart, FiPlus } from "react-icons/fi";
import "./nav.css";

class Nav extends Component {
  render() {
    return (
      <nav className="nav">
        <div className="link">
          <NavLink
            exact
            activeStyle={{
              backgroundColor: "#1690FF",
            }}
            to="/"
          >
            <div>
              <FiHome className="store-icon icon" />
              <p>商城</p>
            </div>
          </NavLink>
        </div>
        <div className="link">
          <NavLink
            exact
            activeStyle={{
              backgroundColor: "#1690FF",
            }}
            to="/order"
          >
            <div>
              <FiShoppingCart className="store-icon icon" />
              <p>订单</p>
            </div>
          </NavLink>
        </div>
        <div className="link">
          <NavLink
            exact
            activeStyle={{
              backgroundColor: "#1690FF",
            }}
            to="/addProduct"
          >
            <div>
              <FiPlus className="store-icon icon" />
              <p>添加商品</p>
            </div>
          </NavLink>
        </div>
      </nav>
    );
  }
}

export default Nav;
