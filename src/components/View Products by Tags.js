import './../App.css';
import React from 'react';
import { useEffect } from 'react';
import { DisplayProduct } from './Display Product';

export function ViewProductsByTags(){
  const [post,setPost]=React.useState([]);
  const [tagnames]=React.useState(new Set());
  const [alltags,setAlltags]=React.useState([]);

  //get all tags on startup
  useEffect(() => {
      var axios = require('axios');
      var data = '';
  
      var config = {
          method: 'get',
          url: 'http://localhost:8080/tag/search/all',
          headers: { },
          data : data
      };
      axios(config)
      .then(function (response) {
          setAlltags(response.data);
          console.log(response.data);
      })
      .catch(function (error) {
          console.log(error);
      });
    }, []);

  //post request for searching products by tags
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
        {alltags.map((t)=>(
          <React.Fragment key={t.id}>
            <input type="checkbox" id={t.tag} onChange={(e)=>{tagnames.has(t.tag)?tagnames.delete(t.tag):tagnames.add(t.tag)}}/>
            <label htmlFor={t.tag}> {t.tag}</label><br/>
          </React.Fragment>
        ))}
        <button type='submit'>Submit</button>
        </form>
        <DisplayProduct post={post} />
    </React.Fragment>
  );
}



