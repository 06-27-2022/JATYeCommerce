import { useRef, useState, useEffect, useContext } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

import AuthContext from '../context/AuthProvider';




const Login = () => {
    const { setAuth } = useContext(AuthContext);
    const userRef = useRef();
    const errRef = useRef();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [username, password])

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/account/login',
                JSON.stringify({ username, password }),
                {
                    headers: { 'Content-Type': 'application/json' },
                    withCredentials: false
                }
            );
            console.log(JSON.stringify(response?.data));
            //console.log(JSON.stringify(response));
            const accessToken = response?.data?.accessToken;
            const roles = response?.data?.roles;
            setAuth({ username, password, roles, accessToken });
            setUsername('');
            setPassword('');
            setSuccess(true);
        } catch (err) {
            if (!err?.response) {
                setErrMsg('check console');
            } else if (err.response?.status === 400) {
                setErrMsg('Missing Username or Password');
            } else if (err.response?.status === 401) {
                setErrMsg('Unauthorized');
            } else {
                setErrMsg('Login Failed');
            } 
            errRef.current.focus();
        }
    }

    return (
        <>
            {success ? (
                <section>
                    <h1>You are logged in!</h1>
                    <br />
                    <p>
                        <a href="#">Go to Home</a>
                    </p>
                </section>
            ) : (
                <section>
                    <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                    <h1>Sign In</h1>
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="username">Username:</label>
                        <input
                            type="text"
                            id="username"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setUsername(e.target.value)}
                            value={username}
                            required
                        />

                        <label htmlFor="password">Password:</label>
                        <input
                            type="password"
                            id="password"
                            onChange={(e) => setPassword(e.target.value)}
                            value={password}
                            required
                        />
                        <button>Sign In</button>
                    </form>
                    <p>
                        Need an Account?<br />
                        <span className="line">
                        <Link type='submit' className='btn btn-outline-danger mx-2' to="/SignUp">Sign Up</Link>
                            
                        </span>
                    </p>
                </section>
            )}
        </>
    )
}

export default Login




















// import React, { useState } from 'react';
// import {  useNavigate } from 'react-router-dom';
// import axios from 'axios';


// export function Login() {

//   let navigate=useNavigate()

//     const [user, setUsername]=useState({

//           username:'',
//           password:''
//     });


   
//     const {username,password}=user
//     const onInputChange=(e)=>{
//         setUsername({...username,[e.target.name]:e.target.value});
       
//     };

//     const onSubmit=async(e)=>{ 
//         e.preventDefault()
//         await axios.post("http://localhost:8080/account/login")
//         navigate("/")
//     };

//     return (
//         <div className='container'>
//             <div className='row'>
//                 <div className='col-md-6 offset-md-3 border rounded p-4 mt-5 shadow'>
//                     <h2 className='text-center m-4'>Log In</h2>
//                     <form onSubmit={(e)=>onSubmit(e)}>
//                     <div className='mb-3'>
//                         <label htmlFor='username' className='form-label'>
//                             Username
//                         </label>
//                         <input
//                             type={"text"}
//                             className="form-control"
//                             placeholder='Enter Name'
//                             name="username"
//                             value={username}
//                             onChange={(e)=> onInputChange(e)}
                        
//                         />
//                     </div>
//                     <div className='mb-3'>
//                         <label htmlFor='password' className='form-label'>
//                             Password
//                         </label>
//                         <input
//                             type={"text"}
//                             className="form-control"
//                             placeholder='password'
//                             name="password"
//                             value={password}
//                             onChange={(e)=> onInputChange(e)}
//                         />
                        
//                     </div>
//                 <button type='submit' className='btn btn-outline-primary mx-2'>Submit</button> 
//                   </form>
//                 </div>
//             </div>
//         </div>
//     )
// }



// //   let navigate=useNavigate;

// //   const [username, setUserName] = useState();
// //   const [password, setPassword] = useState();

// //     const onNameChange=(e)=>{
// //         setUserName({...username,[e.target.name]:e.target.value});
       
// //     };

// //     const onPassChange=(f)=>{
// //     setPassword({...password,[f.target.password]:f.target.value});
// //     };
    

// //     const onSubmitName=async(e)=>{ 
// //         e.preventDefault()
// //         await axios.get("http://localhost:8080/account/login")
// //         navigate("/HomePage")
// //     };

// //     const onSubmitPass=async(f)=>{ 
// //       f.preventDefault()
// //       await axios.get("http://localhost:8080/account/login")
// //       navigate("/HomePage")
// //   };

// //   return(
// //     <div className="login-wrapper">
// //       <h1>Please Log In</h1>
// //       <form onSubmit={(e)=>onSubmitName(e)} onSubmit={(f)=>onSubmitPass(f)}>
// //         <label>
// //           <p>Username</p>
// //           <input type="text" onChange={e => onNameChange(e.target.value)} />
// //         </label>
// //         <label>
// //           <p>Password</p>
// //           <input type="password" onChange={f => onPassChange(f.target.value)} />
// //         </label>
// //         <div>
// //           <button type="submit">Submit</button>
// //           <Link type='submit' className='btn btn-outline-danger mx-2' to="/HomePage">Cancel</Link> 
// //         </div>
// //         <div>
// //           <button type="signup">Sign Up</button>
// //         </div>
// //       </form>
// //     </div>
// //   )
// // }


