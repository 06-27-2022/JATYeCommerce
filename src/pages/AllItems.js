import './../App.css';
import React from 'react';
import { useEffect } from 'react';
import { DisplayProduct } from './../components/Display Product';

export function ViewAllProducts(){
  const [post,setPost]=React.useState([]);

  useEffect(() => {
    createPost();
    }, []);

  function createPost(){
    var axios = require('axios');

    var config = {
            method: 'get',
            url: 'http://localhost:8080/product/search/productname?productname=',
            headers: { }
    };
    
    axios(config)
    .then(function (response) {
            console.log(JSON.stringify(response.data));
            setPost(response.data);
    })
    .catch(function (error) {
            console.log(error);
    });
    }
  return(
    <React.Fragment>
        <DisplayProduct post={post} />
    </React.Fragment>
  );
}
