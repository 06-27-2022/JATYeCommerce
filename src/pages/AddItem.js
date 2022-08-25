import axios from 'axios';
import React, { useState } from 'react'
import { Link,useNavigate } from 'react-router-dom';
export function AddItem() {

    let navigate=useNavigate()

    const [item, setItem]=useState({

        id:'',
        name:'',
        description:'',
        stock:'',
        price:'',
        //tags:''

    });

    const{name, description, stock, price}=item
    const onInputChange=(e)=>{
        setItem({...item,[e.target.name]:e.target.value});
       
    };

    const onSubmit=async(e)=>{ 
        e.preventDefault()
        await axios.post("http://localhost:8080/product/create",item)
        navigate("/")
    };

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-5 shadow'>
                    <h2 className='text-center m-4'>List Product</h2>
                    <form onSubmit={(e)=>onSubmit(e)}>
                    <div className='mb-3'>
                        <label htmlFor='name' className='form-label'>
                            Name of Product
                        </label>
                        <input
                            type={"text"}
                            className="form-control"
                            placeholder='Name of Product'
                            name="name"
                            value={name}
                            onChange={(e)=> onInputChange(e)}
                        
                        />
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='description' className='form-label'>
                            Description of Item
                        </label>
                        <input
                            type={"text"}
                            className="form-control"
                            placeholder='Description of Item'
                            name="description"
                            value={description}
                            onChange={(e)=> onInputChange(e)}
                        />
                        
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='city' className='form-label'>
                            Amount Being Sold
                        </label>
                        <input
                            type={"number"}
                            className="form-control"
                            placeholder='Amount Being Sold'
                            name="stock"
                            value={stock}
                            onChange={(e)=> onInputChange(e)}
                        />
                        
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='price' className='form-label'>
                            Price of Product
                        </label>
                        <input
                            type={"number"}
                            className="form-control"
                            placeholder='Price of Product (In USD)'
                            name="price"
                            value={price}
                            onChange={(e)=> onInputChange(e)}
                        />
                        
                    </div>

                       {/* <div className='mb-3'>
                        <label htmlFor='tags' className='form-label'>
                            Tags
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
                <button type='submit' className='btn btn-outline-primary mx-2'>Submit</button>
                <Link type='submit' className='btn btn-outline-danger mx-2' to="/HomePage">Cancel</Link>  
                  </form>
                </div>
            </div>
        </div>
    )
}