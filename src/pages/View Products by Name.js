import './../App.css';
import React from 'react';
import {Nav} from "../components/navbar"

export function ViewProductsByName(){
  const [post,setPost]=React.useState([]);
  const [productname,setProductname]=React.useState('');

  const apiurl='http://localhost:8080/product/search/productname?productname=';
  const bucketurl='https://tomh07bucket.s3.us-west-2.amazonaws.com/';
  const noimage='https://';

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
        <Nav/>
        <form onSubmit={(e)=>handleSubmit(e)}>
          <label htmlFor='productname'>Product Name</label>
          <input type="productname" onChange={(e)=>{setProductname(e.target.value)}}/>
          <button type='submit'>Submit</button>
        </form>
        <div>
            {post.map((p) => (
                <div key={p.id}>
                    <img src={p.picture!=null?bucketurl+p.picture:noimage} alt={p.name} width="100px" height = "100px"/>
                    <p>id: {p.id}</p>
                    <p>name: {p.name}</p>
                    <p>description: {p.description}</p>
                    <p>price: {p.price}</p>                    
                    <p>stock: {p.stock}</p>                    
                </div>
            ))}
        </div>
    </React.Fragment>
  );
}
