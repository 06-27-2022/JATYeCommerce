import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';


export function Login() {

  let navigate=useNavigate()

    const [username, setUsername]=useState({

          username:''
    });

    const [password, setPassword]=useState({

      password:''
    });
   

    const onInputChange=(e)=>{
        setUsername({...username,[e.target.name]:e.target.value});
        setPassword({...password,[e.target.password]:e.target.value});
       
    };

    const onSubmit=async(e)=>{ 
        e.preventDefault()
        await axios.post("http://localhost:8080/account/login")
        navigate("/")
    };

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-5 shadow'>
                    <h2 className='text-center m-4'>Register User</h2>
                    <form onSubmit={(e)=>onSubmit(e)}>
                    <div className='mb-3'>
                        <label htmlFor='username' className='form-label'>
                            Username
                        </label>
                        <input
                            type={"text"}
                            className="form-control"
                            placeholder='Enter Name'
                            name="username"
                            value={username}
                            onChange={(e)=> onInputChange(e)}
                        
                        />
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='password' className='form-label'>
                            Password
                        </label>
                        <input
                            type={"text"}
                            className="form-control"
                            placeholder='password'
                            name="password"
                            value={password}
                            onChange={(e)=> onInputChange(e)}
                        />
                        
                    </div>
                <button type='submit' className='btn btn-outline-primary mx-2'>Submit</button>
                <Link type='submit' className='btn btn-outline-danger mx-2' to="/">Cancel</Link>  
                  </form>
                </div>
            </div>
        </div>
    )
}



//   let navigate=useNavigate;

//   const [username, setUserName] = useState();
//   const [password, setPassword] = useState();

//     const onNameChange=(e)=>{
//         setUserName({...username,[e.target.name]:e.target.value});
       
//     };

//     const onPassChange=(f)=>{
//     setPassword({...password,[f.target.password]:f.target.value});
//     };
    

//     const onSubmitName=async(e)=>{ 
//         e.preventDefault()
//         await axios.get("http://localhost:8080/account/login")
//         navigate("/HomePage")
//     };

//     const onSubmitPass=async(f)=>{ 
//       f.preventDefault()
//       await axios.get("http://localhost:8080/account/login")
//       navigate("/HomePage")
//   };

//   return(
//     <div className="login-wrapper">
//       <h1>Please Log In</h1>
//       <form onSubmit={(e)=>onSubmitName(e)} onSubmit={(f)=>onSubmitPass(f)}>
//         <label>
//           <p>Username</p>
//           <input type="text" onChange={e => onNameChange(e.target.value)} />
//         </label>
//         <label>
//           <p>Password</p>
//           <input type="password" onChange={f => onPassChange(f.target.value)} />
//         </label>
//         <div>
//           <button type="submit">Submit</button>
//           <Link type='submit' className='btn btn-outline-danger mx-2' to="/HomePage">Cancel</Link> 
//         </div>
//         <div>
//           <button type="signup">Sign Up</button>
//         </div>
//       </form>
//     </div>
//   )
// }


