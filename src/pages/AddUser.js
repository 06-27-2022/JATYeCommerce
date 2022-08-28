import axios from 'axios';
import React, { useState } from 'react'
import { Link,useNavigate } from 'react-router-dom';
export function AddUser() {

    let navigate=useNavigate()

    const [user, setUser]=useState({

          username:'',
          password:'',
        //   email:"",
          city:'',
          state:''

    });

    const{username,password,city, state}=user
    const onInputChange=(e)=>{
        setUser({...user,[e.target.name]:e.target.value});
       
    };

    const onSubmit=async(e)=>{ 
        e.preventDefault()
        await axios.post("http://localhost:8080/account/new",user)
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
                            Name
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
                    {/* <div className='mb-3'>
                        <label htmlFor='email' className='form-label'>
                            Email
                        </label>
                        <input
                            type={"text"}
                            className="form-control"
                            placeholder='email'
                            name="email"
                            value={email}
                            onChange={(e)=> onInputChange(e)}
                        />
                       
                    </div> */}

                    <div className='mb-3'>
                        <label htmlFor='city' className='form-label'>
                            City of Residence
                        </label>
                        <input
                            type={"text"}
                            className="form-control"
                            placeholder='City of Residence'
                            name="city"
                            value={city}
                            onChange={(e)=> onInputChange(e)}
                        />
                        
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='state' className='form-label'>
                            State of Residence
                        </label>
                        <input
                            type={"text"}
                            className="form-control"
                            placeholder='State of Residence'
                            name="state"
                            value={state}
                            onChange={(e)=> onInputChange(e)}
                        />
                        
                    </div>
                <button type='submit' className='btn btn-outline-primary mx-2'>Submit</button>
                <Link type='submit' className='btn btn-outline-danger mx-2' to="/">Cancel</Link>  
                  <div>
                    <h4>Click "Cancel" to return to Login</h4>
                    <h4>Click "Submit" to create your account</h4>
                  </div>
                  </form>
                </div>
            </div>
        </div>
    )
}