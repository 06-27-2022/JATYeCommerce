import './../App.css';
import React from 'react';
import axios from 'axios';
import {Nav} from "../components/navbar"

const url= 'http://localhost:8080/account/new';

export function CreateAccount(){
    const [post, setPost] = React.useState(null);

    const handleSubmit = (e) => {
        e.preventDefault();
        createPost();
    };      

    React.useEffect(() => {
        axios.get(`${url}/`).then((response) => {
            setPost(response.data);
        });
    }, []);

    function createPost() {
        axios.post(url, {
            username: "name2",
            password: "pass",
            role: "default",
            city: "test city",
            state: "test state"
        })
        .then((response) => {
            setPost(response.data);
            console.log(response.data);
        });
    }
    return(
        <React.Fragment>
            <Nav/>
            <form onSubmit={(e)=>handleSubmit(e)}>
              <button type='submit'>Submit</button>
            </form>
            <p>{post}</p>
        </React.Fragment>
      );

}