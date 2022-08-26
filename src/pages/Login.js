import './../App.css';
import React from 'react';
// import axios from 'axios';
import {Nav} from "./../components/navbar"

function createPost(name,pass){
    var axios = require('axios');
    var data = JSON.stringify({
      "username": "default",
      "password": "a password"
    });
    
    var config = {
      method: 'get',
      url: 'http://localhost:8080/account/login',
      headers: { 
        'Content-Type': 'application/json',
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

export function Login(){
    const [username,setUsername]=React.useState('');
    const [password,setPassword]=React.useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        createPost(username,password);
    };      

    return(
        <React.Fragment>
            <Nav/>
            <form onSubmit={(e)=>handleSubmit(e)}>
            <label htmlFor='username'>Username</label>
            <input type="text" onChange={(e)=>{setUsername(e.target.value)}}/>

            <label htmlFor='password'>Password</label>
            <input type="text" onChange={(e)=>{setPassword(e.target.value)}}/>

            <button type='submit'>Submit</button>
            </form>
        </React.Fragment>
    );
}