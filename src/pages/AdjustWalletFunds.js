import React from 'react';
import axios from 'axios';

export function ChangeBalance(){
    const [input, setInput]=React.useState('');
    const [post, setPost]=React.useState(null);

    const posturi="http://localhost:8080/wallet/account/adjustfunds?input="+input

    const handleSubmit = (e)=>{
        e.preventDefault();
        postReview(posturi);
    }
    console.log(posturi);
    function postReview(){
        axios.post(posturi,{},
            {headers:{'Content-Type':'application/json'},withCredentials:true})
        .then((response)=>{
            setPost(response.data);
            console.log(response.data);
            window.location.href="/UserProfile";
        })
    }
    return(
        <div>
            <h2>Adjust Account Balance</h2>
            <form onSubmit={(e)=>{handleSubmit(e)}}>
                <div>
                    <label htmlFor="input">ID of Product for Review</label>
                    <input id="input" name="input" onChange={(e)=>setInput(e.target.value)}/>
                </div>
                <button type="submit">Submit Review</button>
            </form>
        </div>
    )
}