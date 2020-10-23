import React, { Component } from "react";
import "./addProduct.css";

class AddProduct extends Component {
  state = {
    name: "",
    price: null,
    units: "",
    imageUrl: "",
  };

  handleChange = (name, event) => {
    this.setState({
      [name]: event.target.value,
    });
  };

  render() {
    return (
      <div className="add-product">
        <form>
          <h2>添加商品</h2>
          <div className="product-item">
            <h6>
              <span>*</span> 名称:
            </h6>
            <input
              type="text"
              className="form-control"
              placeholder="名称"
              value={this.state.name}
              onChange={(e) => this.handleChange("name", e)}
              id="name"
            />
          </div>
          <div className="product-item">
            <h6>
              <span>*</span> 价格:
            </h6>
            <input
              type="number"
              className="form-control"
              placeholder="价格"
              value={this.state.price}
              onChange={(e) => this.handleChange("price", e)}
              id="price"
            />
          </div>
          <div className="product-item">
            <h6>
              <span>*</span> 单位:
            </h6>
            <input
              type="text"
              className="form-control"
              placeholder="单位"
              value={this.state.units}
              onChange={(e) => this.handleChange("units", e)}
              id="units"
            />
          </div>
          <div className="product-item">
            <h6>
              <span>*</span> 图片:
            </h6>
            <input
              type="text"
              className="form-control"
              placeholder="URL"
              value={this.state.imageUrl}
              onChange={(e) => this.handleChange("imageUrl", e)}
              id="imageUrl"
            />
          </div>
          <div className="product-submit">
            <button
              type="submit"
              disabled={
                !this.state.name ||
                !this.state.price ||
                !this.state.units ||
                !this.state.imageUrl
              }
              className="btn btn-primary"
              id="submit-button"
            >
              提交
            </button>
          </div>
        </form>
      </div>
    );
  }
}

export default AddProduct;
