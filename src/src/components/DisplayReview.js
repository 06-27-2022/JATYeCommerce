import React from "react";

export function SeeReviews(props){
    const post=props.post;

    return(
    <React.Fragment>
      <table className="table border shadow">
        <thead>
          <tr>
            <th>Name of Reviewer</th>
            <th>Product Name</th>
            <th>Rating</th>
            <th>Review</th>
          </tr>
        </thead>
        <tbody>
          {post.map((p) => (
            <tr key={p.id}>
              <td>
                {p.accountId.username}
              </td>
              <td>{p.productId.name}</td>
              <td>{p.rating}</td>
              <td>{p.reviewBody}</td>               
            </tr>
          ))}
        </tbody>
      </table>
    </React.Fragment>);
}