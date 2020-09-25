import React, { Component } from "react";
import "./product.css";
import { FiPlus } from "react-icons/fi";

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
        <button className="product-add">
          <FiPlus />
        </button>
      </div>
    );
  }
}

export default Product;
