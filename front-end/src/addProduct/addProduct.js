import React, { Component } from "react";
import "./addProduct.css";

class AddProduct extends Component {
  state = {
    name: "",
    price: null,
    units: "",
    imageUrl: "",
    isNameValid: false,
    isPriceValid: false,
    isUnitsValid: false,
    isImageUrlValid: false,
    isSubmit: false,
  };

  handleChange = (name, event) => {
    this.setState({
      [name]: event.target.value,
    });
  };

  handleSubmit = (event) => {
    event.preventDefault();
    this.setState({
      isSubmit: true,
    });
    const isValid = this.validProdect();
    if (isValid) {
      console.log(this.state);
    }
  };

  validProdect = () => {
    let isNameValid = false;
    let isPriceValid = false;
    let isUnitsValid = false;
    let isImageUrlValid = false;

    if (this.state.name.length <= 50 && this.state.name.length > 0) {
      isNameValid = true;
    }

    if (
      this.state.price &&
      this.state.price.length <= 100000000 &&
      this.state.price.length >= 0
    ) {
      isPriceValid = true;
    }

    if (this.state.units.length <= 50 && this.state.units.length > 0) {
      isUnitsValid = true;
    }

    if (this.state.imageUrl.length <= 200 && this.state.imageUrl.length > 0) {
      isImageUrlValid = true;
    }

    this.setState({
      isNameValid,
      isPriceValid,
      isUnitsValid,
      isImageUrlValid,
    });

    return isNameValid && isPriceValid && isUnitsValid && isImageUrlValid;
  };

  render() {
    return (
      <div className="add-product" onSubmit={this.handleSubmit}>
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
            {!this.state.isNameValid && this.state.isSubmit && (
              <p className="product-item-valid">名称长度不能超过50</p>
            )}
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
            {!this.state.isPriceValid && this.state.isSubmit && (
              <p className="product-item-valid">价格不能超过100,000,000</p>
            )}
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
            {!this.state.isUnitsValid && this.state.isSubmit && (
              <p className="product-item-valid">单位长度不能超过50</p>
            )}
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
            {!this.state.isImageUrlValid && this.state.isSubmit && (
              <p className="product-item-valid">URL长度不能超过200</p>
            )}
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
