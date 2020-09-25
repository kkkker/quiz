import React, { Component } from "react";
import "./store.css";
import Product from "./product/product";

class Store extends Component {

  state = {
    products: [],
  }

  componentDidMount() {
    const url = "http://localhost:8080/products";
    fetch(url).then(response => response.json())
    .then(data => {
      this.setState({
        products: data
      })
    })
  }

  render() {
    console.log(this.state.products);
    return (
    <div className="store">{this.state.products.map((product) => {
      return <Product key={product.name} product={product} />;
    })}</div>
    );
  }
}

export default Store;
