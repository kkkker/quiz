import React, { Component } from "react";
import "./product.css";

class Product extends Component {
  render() {
    const product = this.props.product;
    return (
      <div className="product">
        <div className="product-image">
          <img src={product.imageUrl} alt={product.name} />
        </div>
        <div className="product-message">
          <h4>{product.name}</h4>
          <p>
            单价:{product.price}元/{product.units}
          </p>
        </div>
      </div>
    );
  }
}

export default Product;
