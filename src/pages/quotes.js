
import axios from 'axios';
import React from 'react';
// import { Sponsors } from '../App';
//Making HTTP Requests with React:
/**
 * When making HTTP requests with react, there are a couple different libraries
 * you can use to do so: axios, fetch, ajax.  We will be leveraging the Axios
 * library.
 * 
 * Axios is an HTTP client library based on promises (though you dont strictly have to 
 * use promises when using Axios). It makes sending asychronous requests to REST endpoints easier
 * and helps you perform CRUD operations. One of the reasons why you would use Axios is because
 * it works well with JSON - you often don't need to set any headers or perform the annoying task
 * of converting a reqest body to a JSON string.
 * 
 * 
 */

// const allPosts = 'https://jsonplaceholder.typicode.com/posts'; //endpoint for getting all data

// export function GetSponsors() {
//     const [post,setPost] = React.useState([]);

//     //hooks allow you to use state inside of function components
//     React.useEffect(() => {
//         axios.get(allPosts).then((response) => {
//             setPost(response.data);
//         });
//     }, []);

//     if(!post) return "no quotes";

//     return (
//         <div>
//             {post.map((p) => (
//            <div key={p.id}>
//            <p>Quote #: {p.id}</p>
//            <p>From sponsor#: {p.userId}</p>
//            <p>Title: {p.title}</p>
//            <p>Inspirational Quote: {p.body}</p>
//            </div>
//             ))}
//         </div>
//     );
// }


// export function AddPosts() {
//     const [post, setPost] = React.useState(null);
//     const [title, setTitle] = React.useState('');
//     const [body, setBody] = React.useState('');

//     const handleSubmit = (e) => {
//         e.preventDefault();
//         createPost(title, body);
//     }


//     React.useEffect(() => {
//         axios.get(`${allPosts}/`).then((response) => {
//             setPost(response.data);
//             setTitle('');
//             setBody('');
//         });
//     }, []);

//     function createPost() {
//         axios.post(allPosts, {
//             title: title,
//             body: body
//         })
//         .then((response) => {
//             setPost(response.data)
//             console.log(response.data);
//         });
//     }

//     if(!post) return "no post requested";

//     return (
//         <div>
//             <form onSubmit={(e) => {handleSubmit(e)}}>
//                 <div>
//                     <label htmlFor="title">Quote title: </label>
//                     <input id="title" name="title" onChange={(e) => setTitle(e.target.value)}/>
//                     <br/>
//                     <label htmlFor="body">Enter Quote: </label>
//                     <input id="body" name="body" onChange={(e) => setBody(e.target.value)}/>
//                 </div>
//                 <button type="submit">Add Quote</button>
//             </form>
//         </div>
//     );

// }

/**
 * Controlled and uncontrolled components: 
 * Controlled components: It is when data is handled directly by a React component,
 * which is why you usually hear this term associated with forms. There is a type of form
 * where the input element's values and mutations are driven by event handlers, and the value
 * of the element is inferred from the state.
 * 
 * Uncontrolled component: When form data is handled by the DOM itself, not through the React
 * component. For example, usually when we want to add a file as an input, in most cases you would
 * use an uncontrolled component.
 * 
 */




//                                                                                          // Hypothetical code

 const allPosts2 = 'https://localhost:8080/account/listall'; //endpoint for getting all data

 export function GetUser() {
     const [post,setPost] = React.useState([]);
 
     React.useEffect(() => {
         axios.get(allPosts2).then((response) => {
             setPost(response.data);
         });
     }, []);
 
     if(!post) return "no users";
 
     return (
         <div>
             {post.map((p) => (
            <div key={p.username}>
            <p>Username: {p.username}</p>
            <p>Password: {p.password}</p>
            </div>
             ))}
         </div>
     );
 }
 
 
 export function AddUsers() {
     const [post, setPost] = React.useState(null);
     const [username, setUsername] = React.useState('');
     const [password, setPassword] = React.useState('');
 
     const handleSubmit = (e) => {
         e.preventDefault();
         createUser(username, password);
     }
 
 
     React.useEffect(() => {
         axios.get(`${allPosts2}/`).then((response) => {
             setPost(response.data);
             setUsername('');
             setPassword('');
         });
     }, []);
 
     function createUser() {
         axios.post(allPosts2, {
             username: username,
             password: password
         })
         .then((response) => {
             setPost(response.data)
             console.log(response.data);
         });
     }
 
     if(!post) return "no post requested";
 
     return (
         <div>
             <form onSubmit={(e) => {handleSubmit(e)}}>
                 <div>
                     <label htmlFor="title">Username: </label>
                     <input id="title" name="title" onChange={(e) => setUsername(e.target.value)}/>
                     <br/>
                     <label htmlFor="body">Password: </label>
                     <input id="body" name="body" onChange={(e) => setPassword(e.target.value)}/>
                 </div>
                 <button type="submit">Sign Up</button>
             </form>
         </div>
     );
 
 }