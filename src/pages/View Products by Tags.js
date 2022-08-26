import './../App.css';
import React from 'react';
import {Nav} from "../components/navbar"

function createPost(apiurl){
  var axios = require('axios');

  var config = {
    method: 'get',
    url: apiurl,
    headers: { }
  };
  
  axios(config)
  .then(function (response) {
    console.log(JSON.stringify(response.data));
  })
  .catch(function (error) {
    console.log(error);
  });  
}
export function ViewProductsByTags(){
  const [tagnames,setTagnames]=React.useState('');
  const handleSubmit = (e) => {
      e.preventDefault();
      createPost('http://localhost:8080/product/search/tagnames?tagnames='+tagnames);
  };      
  return(
      <React.Fragment>
          <Nav/>
          <form onSubmit={(e)=>handleSubmit(e)}>
          <label htmlFor='tagnames'>Tag Names</label>
          <input type="tagnames" onChange={(e)=>{setTagnames(e.target.value)}}/>
          <button type='submit'>Submit</button>
          </form>
      </React.Fragment>
  );
}