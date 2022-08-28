import React, { useEffect,useState } from 'react'
import { Link,useNavigate } from 'react-router-dom';


export function EditItem() {
    let navigate=useNavigate()
    const [product, setProduct]=useState({
        id:0,
        name:'',
        description:'',
        stock:0,
        price:0,
        tags:[]
    });
    const{id, name, description, stock, price, tags}=product
    const onInputChange=(e)=>{
        setProduct({...product,[e.target.name]:e.target.value});
    };

    const [alltags,setAlltags]=React.useState([]);
    const [tagnames]=React.useState(new Set());
    //get all tags on startup
    useEffect(() => {
        var axios = require('axios');
        var data = '';
        var config = {
            method: 'get',
            url: 'http://localhost:8080/tag/search/all',
            headers: { },
            data : data
        };
        axios(config)
        .then(function (response) {
            setAlltags(response.data);
            console.log(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });
      }, []);



    //submiting the product
    const onSubmit=async(e)=>{ 
        e.preventDefault()
        console.log(product);
        createPost();
        navigate("/HomePage")
    };
    const createPost=()=>{
        var axios = require('axios');
        var data = product;
        var config = {
                method: 'post',
                url: 'http://localhost:8080/product/update',
                headers: { 
                        'Content-Type': 'application/json'
                },
                withCredentials:true,
                data : data
        };

        axios(config)
        .then(function (response) {
                console.log(JSON.stringify(response.data));
        })
        .catch(function (error) {
                console.log(error);
        });
    }


    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-5 shadow'>
                    <h2 className='text-center m-4'>List Product</h2>
                    <form onSubmit={(e)=>onSubmit(e)}>

                    <div className='mb-3'>
                        <label htmlFor='city' className='form-label'>
                            Product ID
                        </label>
                        <input
                            type={"number"}
                            className="form-control"
                            placeholder='Product ID'
                            name="id"
                            value={id}
                            // onChange={(e)=> onInputChange(e)}
                            onChange={(e)=>{setProduct({...product,[e.target.name]:Number(e.target.value)})}}               
                        />
                        
                    </div>


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
                            // onChange={(e)=> onInputChange(e)}
                            onChange={(e)=>{setProduct({...product,[e.target.name]:Number(e.target.value)})}}               
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
                            // onChange={(e)=> onInputChange(e)}
                            onChange={(e)=>{setProduct({...product,[e.target.name]:Number(e.target.value)})}}               
                        />
                        
                    </div>

                    <div className='mb-3'>
                        <p>Tag Names</p>
                        {alltags.map((t)=>(
                        <React.Fragment key={t.id}>
                            <input type="checkbox" id={t.tag} 
                            className="form-control"
                            name="tags"
                            value={tags}                            
                            onChange={(e)=>{
                                tagnames.has(t)?tagnames.delete(t):tagnames.add(t);
                                setProduct({...product,[e.target.name]:Array.from(tagnames)})
                            }}               
                            />
                            <label htmlFor={t.tag}> {t.tag}</label><br/>
                        </React.Fragment>
                        ))}
                    </div>
                    <br/>
                <button type='submit' className='btn btn-outline-primary mx-2'>Submit</button>
                <Link type='submit' className='btn btn-outline-danger mx-2' to="/HomePage">Cancel</Link>  
                 <div>
                    <h4>Click "Cancel" to return to the HomePage</h4>
                    <h4>Click "Submit" to begin selling your item</h4>
                 </div>
                  </form>
                </div>
            </div>
        </div>
    )
}