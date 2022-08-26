import './../App.css';
import React from 'react';
import {Nav} from "../components/navbar"

export function ViewProductsByName(){
  const [post,setPost]=React.useState(null);
  const [productname,setProductname]=React.useState('');

  function createPost(apiurl){
    var axios = require('axios');
  
    var config = {
      method: 'get',
      url: apiurl,
      headers: { }
    };
    
    axios(config)
    .then(function (response) {
      setPost(JSON.stringify(response.data));
      console.log(post);
    })
    .catch(function (error) {
      console.log(error);
    });  
  }

  const handleSubmit = (e) => {
      e.preventDefault();
      createPost('http://localhost:8080/product/search/productname?productname='+productname);
  };      
  return(
    <React.Fragment>
        <Nav/>
        <form onSubmit={(e)=>handleSubmit(e)}>
          <label htmlFor='productname'>Product Name</label>
          <input type="productname" onChange={(e)=>{setProductname(e.target.value)}}/>
          <button type='submit'>Submit</button>
        </form>
        <p>{post}</p>
        </React.Fragment>
  );
}
