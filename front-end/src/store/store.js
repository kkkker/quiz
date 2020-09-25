import React, { Component } from "react";
import "./store.css";
import Product from "./product/product";

class Store extends Component {
  state = {
    products: [],
    clickAble: true, 
  };

  handleAddProduct = (name, event) => {
    this.setState({
      clickAble: false, 
    });
    const product = this.state.products.filter((product) => {
      return product.name === name;
    })[0];
    let orders = window.orders;
    if (orders === null || orders === undefined) {
      product.number = 1;
      orders = [product];
    } else {
      let tempProducts = orders.filter((product) => {
        return product.name === name;
      });
      if (tempProducts.length === 0) {
        product.number = 1;
        orders.push(product);
      } else {
        orders = orders.map((product) => {
          if (product.name === name) {
            product.number = product.number + 1;
          }
          return product;
        });
      }
    }
    window.orders = orders;
    this.setState({
      clickAble: true 
    });
  };

  componentDidMount() {
    const url = "http://localhost:8080/products";
    fetch(url)
      .then((response) => response.json())
      .then((data) => {
        this.setState({
          products: data,
        });
      });
  }

  render() {
    return (
      <div className="store">
        {this.state.products.map((product) => {
          return (
            <Product
              key={product.name}
              product={product}
              clickAble={this.state.clickAble}
              onAddProducts={this.handleAddProduct}
            />
          );
        })}
      </div>
    );
  }
}

export default Store;
