import React, { Component } from "react";

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
      <div className="store">store</div>
    );
  }
}

export default Store;
