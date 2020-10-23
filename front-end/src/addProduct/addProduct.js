import React, { Component } from "react";
import "./addProduct.css"

class AddProduct extends Component {
  state = {
    name: "",
    price: 0,
    units: "",
    imageUrl: "",
  };

  render() {
    return (
      <div className="add-product">
        <form>
          <h2>添加商品</h2>
          <h6>
            <span>*</span>名称:
          </h6>
          <input
            type="text"
            className="form-control"
            placeholder="名称"
            value={this.state.name}
            onChange={(e) => this.handleChange("name", e)}
            id="name"
          />
          <h6>
            <span>*</span>价格:
          </h6>
          <input
            type="number"
            className="form-control"
            placeholder="价格"
            value={this.state.name}
            onChange={(e) => this.handleChange("price", e)}
            id="price"
          />
          <h6>
            <span>*</span>单位:
          </h6>
          <input
            type="text"
            className="form-control"
            placeholder="单位"
            value={this.state.name}
            onChange={(e) => this.handleChange("units", e)}
            id="units"
          />
          <h6>
            <span>*</span>图片:
          </h6>
          <input
            type="text"
            className="form-control"
            placeholder="图片"
            value={this.state.name}
            onChange={(e) => this.handleChange("imageUrl", e)}
            id="imageUrl"
          />
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
            Submit
          </button>
        </form>
      </div>
    );
  }
}

export default AddProduct;
