import './../App.css';
import React from 'react';
import { DisplayProduct } from './Display Product';

export function ViewProductsByTags(){
  const [post,setPost]=React.useState([]);
  const [tagnames]=React.useState(new Set());

  const apiurl='http://localhost:8080/product/search/tagnames?tagnames=';

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
        <form onSubmit={(e)=>handleSubmit(e)}>
        <p>Tag Names</p>
        <input type="checkbox" id="tag1" onChange={(e)=>{tagnames.has("tag1")?tagnames.delete("tag1"):tagnames.add("tag1")}}/>
        <label htmlFor="tag1"> tag1</label><br/>
        <input type="checkbox" id="tag6" onChange={(e)=>{tagnames.has("tag6")?tagnames.delete("tag6"):tagnames.add("tag6")}}/>
        <label htmlFor="tag6"> tag6</label><br/>
        <button type='submit'>Submit</button>
        </form>
        <DisplayProduct post={post} />
    </React.Fragment>
  );
}

// function View(){
//   const AllTags=()=>{
//     var axios = require('axios');
//     var config = {
//       method: 'get',
//       url: 'http://localhost:8080/tag/search/all',
//     };
//     axios(config)
//     .then(function (response) {
//       console.log(response.data);
//     })
//     .catch(function (error) {
//       console.log(error);
//     });
//   }
//   const SelectTags=()=>{
//     return(
//       <React.Fragment>
//       </React.Fragment>
//     );
//   }  
// }


