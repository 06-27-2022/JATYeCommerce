import axios from 'axios';
import React, { useState } from 'react'
import { Link,useNavigate } from 'react-router-dom';
export function AddUser() {

    let navigate=useNavigate()

    // const cors = require('cors');
    // const corsOptions ={
    //     origin:'http://localhost:3000', 
    //     credentials:true,            //access-control-allow-credentials:true
    //     optionSuccessStatus:200
    // }
    // app.use(cors(corsOptions));

    // let app;
    




    // app.use(function (req, res, next) {

    //     // Website you wish to allow to connect
    //     res.setHeader('Access-Control-Allow-Origin', 'http://localhost:3000');
    
    //     // Request methods you wish to allow
    //     res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
    
    //     // Request headers you wish to allow
    //     res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');
    
    //     // Set to true if you need the website to include cookies in the requests sent
    //     // to the API (e.g. in case you use sessions)
    //     res.setHeader('Access-Control-Allow-Credentials', true);
    
    //     // Pass to next layer of middleware
    //     next();
    // });

    const [user, setUser]=useState({

          username:'',
          password:'',
        //   email:"",
          city:'',
          state:''

    });

    const{username,password,email,city, state}=user
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
                  </form>
                </div>
            </div>
        </div>
    )
}