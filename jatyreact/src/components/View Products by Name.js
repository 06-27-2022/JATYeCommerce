import './../App.css';
import React from 'react';
import { DisplayProduct } from './Display Product';

export function ViewProductsByName(){
  const [post,setPost]=React.useState([]);
  const [productname,setProductname]=React.useState('');

  const apiurl='http://localhost:8080/product/search/productname?productname=';

  function createPost(url){
    var axios = require('axios');
  
    var config = {
      method: 'get',
      url: url,
      headers: { }
    };
    
    axios(config)
    .then(function (response) {
      setPost(response.data);
      console.log(response.data);
    })
    .catch(function (error) {
      console.log(error);
    });  
  }

  const handleSubmit = (e) => {
      e.preventDefault();
      createPost(apiurl+productname);
  };      
  return(
    <React.Fragment>
        <form onSubmit={(e)=>handleSubmit(e)}>
          <label htmlFor='productname'>Product Name </label>
          <input type="productname" onChange={(e)=>{setProductname(e.target.value)}}/>
          <button type='submit'>Submit</button>
        </form>
        <DisplayProduct post={post} />
    </React.Fragment>
  );
}
