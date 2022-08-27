import React from "react";

export function DisplayProduct(props){
    const bucketurl='https://tomh07bucket.s3.us-west-2.amazonaws.com/';
    const noimage='https://no';  
    const post=props.post;

    return(
    <React.Fragment>
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
    </React.Fragment>);
}