import React from "react";
import { PurchaseProduct } from "./Purchase Product";

//in App.css
//table, th, td {border: 1px solid;}
export function DisplayProduct(props){
    const bucketurl='https://tomh07bucket.s3.us-west-2.amazonaws.com/';
    const noimage='https://no';  
    const post=props.post;

    return(
    <React.Fragment>
      <table className="table border shadow">
        <thead>
          <tr>
            <th>Picture</th>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Seller</th>
            <th>price</th>
            <th>stock</th>
            <th>purchase</th>
          </tr>
        </thead>
        <tbody>
          {post.map((p) => (
            <tr key={p.id}>
              <td>
                <img src={p.picture!=null?bucketurl+p.picture:noimage} alt="None Provided" width="100" height = "100"/>
              </td>
              <td>{p.id}</td>
              <td>{p.name}</td>
              <td>{p.description}</td>
              <td>{p.accountId.username}</td>
              <td>{p.price}</td>                    
              <td>{p.stock}</td>                    
              <td><PurchaseProduct productid={p.id} notifyParent={() => this.forceUpdate()}/></td>                    
            </tr>
          ))}
        </tbody>
      </table>
    </React.Fragment>);
}