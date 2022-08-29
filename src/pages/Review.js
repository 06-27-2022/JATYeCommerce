import axios from "axios";
import React from "react";
import { Nav } from "../components/nav";
import { SeeReviews } from "../components/DisplayReview";

export function ReviewProduct(){
    const[reviewBody, setReviewBody]=React.useState('');
    const[rating, setRating]=React.useState('');
    const[sent, setSent]=React.useState(null);
    const [prodid,setProdid]=React.useState('');
    var posturi= "http://localhost:8080/review/"+prodid+"/add"

    const handleSubmit = (e)=>{
        e.preventDefault();
        postReview(reviewBody,rating);
    }
    console.log(posturi);
    function postReview(){
        axios.post(posturi,
            {reviewBody:reviewBody,rating:rating},
            {headers:{'Content-Type':'application/json'},withCredentials:true})
        .then((response)=>{
            setSent(response.data);
            console.log(response.data);
            window.location.href="/HomePage";
        })
    }
    return(
        <div>
            <Nav/>
            <div>
                <h2>Review Product</h2>
                <form onSubmit={(e)=>{handleSubmit(e)}}>
                    <div>
                        <label htmlFor="prodid">ID of Product for Review</label>
                        <input id="prodid" name="prodid" onChange={(e)=>setProdid(e.target.value)}/>
                        <br/>
                        <label htmlFor="reviewBody">Review</label>
                        <input id="reviewBody" name="reviewBody" onChange={(e)=>setReviewBody(e.target.value)}/>
                        <br/>
                        <label htmlFor="rating">Rating</label>
                        <input name="rating" onChange={(e)=>setRating(e.target.value)}/>
                    </div>
                    <button type="submit">Submit Review</button>
                </form>
            </div>
        </div>
    );
}
export function GetReviews(){
    const [post,setPost]=React.useState([]);
    const [prodid,setProdid]=React.useState('');

    const apiurl="http://localhost:8080/review/"+prodid+"/seeall";

    function createPost(url){
    var axios = require('axios');

    var config = {
        method: 'get',
        url: url,
        headers: { },
        withCredentials:true
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
        createPost(apiurl);
        console.log(apiurl);
    };      
    return(
    <div>
    <Nav/>
    <h2>View Reviews for Product</h2>
    <div>
        <form onSubmit={(e)=>handleSubmit(e)}>
            <label htmlFor='prodid'>Product ID </label>
            <input type="prodid" onChange={(e)=>{setProdid(e.target.value)}}/>
            <button type='submit'>Submit</button>
        </form>
        <SeeReviews post={post} />
    </div>
    </div>
    );
}