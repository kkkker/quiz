import React, { Component } from "react";
import "./App.css";
import { Route, BrowserRouter } from "react-router-dom";
import Nav from "./nav/nav";
import Store from "./store/store";
import Order from "./order/order";
import AddProducts from "./add-products/add_products";
import Footer from "./footer/footer";

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div className="app">
          <Nav className="nav-bar" />
          <Route exact path="/" component={Store} />
          <Route exact path="/calculator" component={Order} />
          <Route exact path="/timer" component={AddProducts} />
          <Footer className="footer" />
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
