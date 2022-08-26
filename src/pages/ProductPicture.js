import './../App.css';
import React from 'react';
import {Nav} from "../components/navbar"

export function PictureUpdate(){    
  const [file,setFile]=React.useState(null);
  const [accountid,setAccountid]=React.useState(0);
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(file);
    createPost(file);
  };  

  function createPost(file){
    var axios = require('axios');
    var data = file;
    
    var config = {
      method: 'post',
      url: 'http://localhost:8080/product/update/picture?id=3',
      headers: { 
        'Content-Type': 'image/png'
      },
      data : data
    };
    
    axios(config)
    .then(function (response) {
      console.log(JSON.stringify(response.data));
    })
    .catch(function (error) {
      console.log(error);
    });  
  }

  return(
    <React.Fragment>
      {<Nav/>}
      <h1>test 3</h1>
      <p>this is test3</p>
      <form onSubmit={(e)=>{handleSubmit(e)}} method="POST" encType="multipart/form-data">
          <label htmlFor="accountid">accountid</label>
          <input type="text" id="accountid" name="accountid" onChange={(e)=>{setFile(e.target.value)}}/>
          <br/>
          <label htmlFor="file">Select file</label>
          <input type="file" id="file" name="file" accept="image/png" onChange={(e)=>{setFile(e.target.value)}}/>
          <br/>
          <input type="submit" value="Submit File"/>
          <br/>
      </form>
    </React.Fragment>
  );
}



