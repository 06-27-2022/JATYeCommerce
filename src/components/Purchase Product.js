import React, { useState } from "react";

//in App.css
export function PurchaseProduct(props){
    const [status,setStatus]=useState('');
    const productid=props.productid;

    const createPost=()=>{
        var axios = require('axios');
        var config = {
                method: 'get',
                url: 'http://localhost:8080/product/'+productid+'/buy',
                headers: { 
                },        
                withCredentials:true,
        };

        axios(config)
        .then(function (response) {
                console.log(JSON.stringify(response.data));
                setStatus(response.data);
                props.notifyParent();
        })
        .catch(function (error) {
                console.log(error);
        });
    }
    return(
        <React.Fragment>
            <input type="button" value="Purchase" 
            onClick={createPost}
            />
            <p>{status}</p>
        </React.Fragment>
    );

}