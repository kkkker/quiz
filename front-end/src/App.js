import React, { Component } from "react";
import "./App.css";
import { Route, BrowserRouter } from "react-router-dom";
import Nav from "./nav/nav";
import Store from "./store/store";
import Order from "./order/order";
import AddProducts from "./add-products/add_products";

class App extends Component {
  render() {
    return (
      <div className="app">
        <BrowserRouter>
          <Nav className="nav-bar" />
          <Route exact path="/" component={Store} />
          <Route exact path="/calculator" component={Order} />
          <Route exact path="/timer" component={AddProducts} />
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
