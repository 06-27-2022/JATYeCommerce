import './../App.css';
import React from 'react';
import {Nav} from "../components/navbar"

export function ViewProductsByTags(){
  const [post,setPost]=React.useState([]);
  const [tagnames]=React.useState(new Set());

  const apiurl='http://localhost:8080/product/search/tagnames?tagnames=';
  const bucketurl='https://tomh07bucket.s3.us-west-2.amazonaws.com/';
  const noimage='https://';

  function createPost(apiurl){
    var axios = require('axios');
    var config = {
      method: 'get',
      url: apiurl,
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
    let tags="";
    for (const t of tagnames.values()){tags+=t+",";}
    tags=tags.slice(0, -1);
    createPost(apiurl+tags);
  }; 
  
  return(
    <React.Fragment>
        <Nav/>
        <form onSubmit={(e)=>handleSubmit(e)}>
        <h1>Tag Names</h1>
        <input type="checkbox" id="tag1" onChange={(e)=>{tagnames.has("tag1")?tagnames.delete("tag1"):tagnames.add("tag1")}}/>
        <label htmlFor="tag1"> tag1</label><br/>
        <input type="checkbox" id="tag6" onChange={(e)=>{tagnames.has("tag6")?tagnames.delete("tag6"):tagnames.add("tag6")}}/>
        <label htmlFor="tag6"> tag6</label><br/>
        <button type='submit'>Submit</button>
        </form>
        <div>
          {post.map((p) => (
            <div key={p.id}>
              <img src={p.picture!=null?bucketurl+p.picture:noimage} alt={p.name} width="100" height = "100"/>
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

function View(){
  const AllTags=()=>{
    var axios = require('axios');
    var config = {
      method: 'get',
      url: 'http://localhost:8080/tag/search/all',
    };
    axios(config)
    .then(function (response) {
      console.log(response.data);
    })
    .catch(function (error) {
      console.log(error);
    });
  }
  const SelectTags=()=>{
    return(
      <React.Fragment>
      </React.Fragment>
    );
  }  
}


