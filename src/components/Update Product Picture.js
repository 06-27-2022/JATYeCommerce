import './../App.css';
import React from 'react';
import {useRef} from 'react';
import { Link } from 'react-router-dom';

// https://stackoverflow.com/questions/41453224/uploading-a-file-with-reactjs-and-dealing-with-c-fakepath-file
// thank you Shafie Mukhre
// by Tommy Hai

export function PictureUpdate(props){    
  const [file,setFile]=React.useState(null);
  const [response,setResponse]=React.useState('');
  const inputRef = useRef()

  const [Productid,setProductid]=React.useState(0);
  const productid=props.productid;

  let apiurl = 'http://localhost:8080/product/update/picture?id='+Productid;
 
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(file);
    createPost(file);
  }

  function createPost(upload){
    var axios = require('axios');
    var data = upload;
    
    var config = {
      method: 'post',
      url: apiurl,
      headers: { 
        'Content-Type': 'image/png' 
      },
      withCredentials:true,
      data : data
    };
    
    axios(config)
    .then(function (response) {
      console.log(JSON.stringify(response.data));
      setResponse(response.data);
    })
    .catch(function (error) {
      console.log(error);
    });
  }

  return(
    <React.Fragment>
      {/* {<Nav/>} */}
      <h1>Upload Picture</h1>
      <form onSubmit={(e)=>{handleSubmit(e)}} method="POST" encType="multipart/form-data">
          <label htmlFor="productid">productid</label>
          <input type="number" id="productid" name="productid" value={productid?0:productid} onChange={(e)=>{setProductid(e.target.value)}}/>
          <br/>
          <label htmlFor="file">Select file</label>
          <input type="file" id="file" accept="image/png" onChange={() => setFile(inputRef.current.files[0])} ref={inputRef}/>
          <br/>
          <input type="submit" value="Submit File"/>
          <Link type='submit' className='btn btn-outline-danger mx-2' to="/HomePage">Cancel</Link>  
          <br/>
      </form>
      <p>{response}</p>
    </React.Fragment>
  );
}



